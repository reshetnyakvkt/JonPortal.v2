package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 31.05.14
 */
@Unit(testName = "TwoDigitSubTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F1TwoDigitSubTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int number = scan.nextInt();
        int firstDig = (number) % 10;
        int secondDig = (number / 10) % 10;
        System.out.println(firstDig + secondDig);
    }

    private static final String UNIT_NAME = "TwoDigitSub";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;
    @Troubles
    private static List<String> troubles;

    private static Object instance;
    private static Method unitMethod;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void test() throws Throwable {
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        instance = instanciate(unitClasses[0]);
        unitMethod = ReflectionUtil.checkMainMethod(unitClasses[0]);
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        final String PREFIX = "\n--- Проверка корректности результата ---\n";

        int twoDigits = generateNumber(10, 99);
        getOut().println(twoDigits);
        ReflectionUtil.invokeMain(instance);

        String actualString = getIn().toString().trim();
        int actualSum = 0;
        try {
            actualSum = Integer.parseInt(actualString);
        } catch (NumberFormatException e) {
            fail(PREFIX + "Должно быть выведено число, а не " + actualString);
        }

        int expectedSum = calcTwoDigitsSum(twoDigits);

        assertTrue(PREFIX + "Сумма цифр числ " + twoDigits + " должна быть равна " + expectedSum + " а не " + actualSum,
                expectedSum == actualSum);
    }

    private int calcTwoDigitsSum(int number) {
        int firstDig = (number) % 10;
        int secondDig = (number / 10) % 10;
        return firstDig + secondDig;
    }

    private int generateNumber(int from, int to) {
        int range = to - from;
        return (int)(Math.random() * range + from);
    }
}
