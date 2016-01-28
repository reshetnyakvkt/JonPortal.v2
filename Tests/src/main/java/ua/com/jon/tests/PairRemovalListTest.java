package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Julia
 * Date: 13.10.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
@Unit(testName = "PairRemovalList", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PairRemovalListTest extends BaseTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String listStr = scan.nextLine();

        List<Integer> list = new ArrayList<Integer>();
        String[] elements = listStr.split(" ");
        for(int i=0; i<elements.length; i++){
            Integer number = 0;
            try {
                number = Integer.parseInt(elements[i]);
                list.add(number);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        for(int i=0; i<list.size(); i++){
            if(list.get(i)%2 == 0){
                list.remove(i--);
            }
        }

        StringBuilder builder = new StringBuilder();
        for(Integer el : list){
            builder.append(el);
            builder.append(' ');
        }

        System.out.println(builder.toString());
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
        instance = instanciate(unitClass);
        int listSize = rnd.nextInt(10) + 10;
        List<Integer> expactedNumbers = new ArrayList<Integer>(listSize);
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<listSize; i++){
            Integer element = rnd.nextInt(100);
            expactedNumbers.add(element);
            builder.append(element);
            builder.append(' ');
        }
        getOut().println(builder.toString());
        removePairs(expactedNumbers);

        invokeMainAsProcess(unitClass, "unitName", getOut().toString());

        String listString = getIn().toString();
        List<Integer> actualNumbers = parsNumberString(listString);

        Assert.assertEquals("Ожидаемый результат " + expactedNumbers + ", но выведено " + actualNumbers,
                expactedNumbers, actualNumbers);
    }

    private void removePairs(List<Integer> list){
        for(int i=0; i<list.size(); i++){
            if(list.get(i)%2 == 0){
                list.remove(i--);
            }
        }
     }

    private List<Integer> parsNumberString(String result){
        List<Integer> list = new ArrayList<Integer>();
        String[] parseNumbers = result.split(" ");
        for(String numberStr : parseNumbers){
            numberStr = numberStr.trim();
            if(!numberStr.isEmpty()){
                Integer number = 0;
                try {
                    number = Integer.parseInt(numberStr);
                    list.add(number);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
