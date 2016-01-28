package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/27/13
 */
@Unit(testName = "FactorialIter", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FactorialIterTest extends BaseTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        if(!scan.hasNextInt()) {
            System.out.println("Неправильный ввод");
            return;
        }
        int number = scan.nextInt();
        int fib = 1;
        for(int i=1; i<=number; i++) {
            fib *= i;
        }
        System.out.println(fib);
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
    public void test1IllegalInput() throws Throwable {
        instance = instanciate(unitClass);
        String expectedString = "Неправильный ввод\n";
        getOut().println("q");

        invokeMainAsProcess(unitClass, "unitName", getOut().toString());

        String actualString = getIn().toString();

        Assert.assertEquals("Неудачная проверка на неправильный ввод.\nОжидаемый результат [" + expectedString + "], " +
                "но выведено [" + actualString + "]",
                expectedString, actualString);
    }

    @Test(timeout = 1000)
    public void test2Success() throws Throwable {
        instance = instanciate(unitClass);
        int number = rnd.nextInt(10);
        int fib = 1;
        for(int i=1; i<=number; i++) {
            fib *= i;
        }
        String expectedLine = fib + "\n";
        getOut().println(number);

        invokeMainAsProcess(unitClass, "unitName", getOut().toString());

        String actualLine = getIn().toString();

        Assert.assertEquals("Ожидаемый результат [" + expectedLine + "], но выведено [" + actualLine + "]",
                expectedLine, actualLine);
    }


}
