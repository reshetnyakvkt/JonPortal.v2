package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.reflect.JavaProcessBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

@Unit(testName = "ArrayToArrayOccurrence", value = "weekend1.task1")
public class ArrayToArrayOccurrenceTest  extends BaseTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String firstLine = scan.nextLine();
        String secondLine = scan.nextLine();

        String[] first = firstLine.split(" ");
        String[] second = secondLine.split(" ");
        int numberEquals = 0;

        for(int i=0; i<second.length; i++){
            for(int j=0; j<first.length; j++){
                if(second[i].equals(first[j])){
                    numberEquals += 1;
                    break;
                }
            }
        }

        if(numberEquals == second.length){
            System.out.println("да");
        }else{
            System.out.println("нет");
        }
    }

    @Unit
    private static Class unitClass;
    private Object instance;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
//        instance = instanciate(unitClass);
        int listSize = rnd.nextInt(10) + 10;
        Integer[] firstList = new Integer[listSize];
        listSize = rnd.nextInt(10) + 10;
        Integer[] secondList = new Integer[listSize];
        String firstLine = null;
        String secondLine = null;
        for(int i=0; i<firstList.length; i++){
            firstList[i] = rnd.nextInt(10);
            firstLine += firstList[i] + " ";
        }
        for(int i=0; i<secondList.length; i++){
            secondList[i] = rnd.nextInt(10);
            secondLine += secondList[i] + " ";
        }

        int numberEquals = 0;
        for(int i=0; i<secondList.length; i++){
            for(int j=0; j<firstList.length; j++){
                if(secondList[i].equals(firstList[j])){
                    numberEquals += 1;
                    break;
                }
            }
        }

        String expectedAnswer = "нет" + lineSeparator;
        if (numberEquals == secondList.length){
            expectedAnswer = "да" + lineSeparator;
        }
        expectedAnswer.trim();

        getOut().println(firstLine);
        getOut().println(secondLine);

        JavaProcessBuilder.buildProcessAndInvokeMethod(unitClass.getSimpleName(), "main", "/forbid.policy", null,
                getOut().toString(), (Object) new String[0]);

        invokeMainAsProcess(unitClass, "unitName", getOut().toString());

        String actualAnswer = getIn().toString();
        actualAnswer.trim();

        Assert.assertEquals("Ожидаемый результат " + expectedAnswer + ", но выведено " + actualAnswer,
                expectedAnswer, actualAnswer);
    }
}
