package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Пользователь вводит координаты точки, определить в какой она находится четверти (декартова система координат)
Если точка находится между четвертями, то выводить 0
Пример:
-2 2
2
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 31.05.14
 */
@Unit(testName = "F1QuarterTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(Parameterized.class)
public class F1QuarterTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int x = scan.nextInt();
        int y = scan.nextInt();
        if (x == 0 || y == 0) {
            System.out.println(0);
        } else if (x > 0 && y > 0) {
            System.out.println(1);
        } else if (x < 0 && y > 0) {
            System.out.println(2);
        } else if (x < 0 && y < 0) {
            System.out.println(3);
        } else {
            System.out.println(4);
        }
    }

/*
    private int xFrom;
    private int yFrom;
    private int xTo;
    private int yTo;
    private int quarterNumber;

    public F1QuarterTest(int xFrom, int xTo, int yFrom, int yTo, int quarterNumber) {
        this.xFrom = xFrom;
        this.xTo = xTo;
        this.yFrom = yFrom;
        this.yTo = yTo;
        this.quarterNumber = quarterNumber;
    }
*/

/*    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][]{
                {1, 10, 1, 10, 1},
                {-10, 1, 1, 10, 2},
                {-10, 1, -10, 1, 3},
                {1, 10, -10, 1, 4},
                {0, 0, 0, 10, 0},
        });
    }*/

    private static final String UNIT_NAME = "DigitsAvg";

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
    public void testFirstQuarter() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int x = generateNumber(1, 10);
        int y = generateNumber(1, 10);
        getOut().println(x);
        getOut().println(y);
        int expectedRes = 1;//calcQuarter(x, y);

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();
        double actualRes = 0.0;
        try {
            actualRes = Double.parseDouble(actualString);
        } catch (NumberFormatException e) {
            fail("\n--- Проверка корректности результата ---\nРезультатом должно быть числом, а не " + actualString);
        }

        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка корректности результата---\nПри введенных x=" + x + " и y= "+ y + ", четверть должна быть [" + expectedRes + "], а не [" + actualString + "]",
                expectedRes == actualRes);
    }
    @Test(timeout = 1000)
    public void testSecondQuarter() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int x = generateNumber(-10, -1);
        int y = generateNumber(1, 10);
        getOut().println(x);
        getOut().println(y);
        int expectedRes = 2;//calcQuarter(x, y);

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();
        double actualRes = 0.0;
        try {
            actualRes = Double.parseDouble(actualString);
        } catch (NumberFormatException e) {
            fail("\n--- Проверка корректности результата ---\nРезультатом должно быть числом, а не " + actualString);
        }

        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка корректности результата---\nПри введенных x=" + x + " и y= "+ y + ", четверть должна быть [" + expectedRes + "], а не [" + actualString + "]",
                expectedRes == actualRes);
    }
    @Test(timeout = 1000)
    public void testThirdQuarter() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int x = generateNumber(-10, -1);
        int y = generateNumber(-10, -1);
        getOut().println(x);
        getOut().println(y);
        int expectedRes = 3;//calcQuarter(x, y);

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();
        double actualRes = 0.0;
        try {
            actualRes = Double.parseDouble(actualString);
        } catch (NumberFormatException e) {
            fail("\n--- Проверка корректности результата ---\nРезультатом должно быть числом, а не " + actualString);
        }

        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка корректности результата---\nПри введенных x=" + x + " и y= "+ y + ", четверть должна быть [" + expectedRes + "], а не [" + actualString + "]",
                expectedRes == actualRes);
    }

    @Test(timeout = 1000)
    public void testForthQuarter() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int x = generateNumber(1, 10);
        int y = generateNumber(-10, -1);
        getOut().println(x);
        getOut().println(y);
        int expectedRes = 4;//calcQuarter(x, y);

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();
        double actualRes = 0.0;
        try {
            actualRes = Double.parseDouble(actualString);
        } catch (NumberFormatException e) {
            fail("\n--- Проверка корректности результата ---\nРезультатом должно быть числом, а не " + actualString);
        }

        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка корректности результата---\nПри введенных x=" + x + " и y= "+ y + ", четверть должна быть [" + expectedRes + "], а не [" + actualString + "]",
                expectedRes == actualRes);
    }

    @Test(timeout = 1000)
    public void testBetweenQuarter() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int x = generateNumber(0, 0);
        int y = generateNumber(-10, 0);
        getOut().println(x);
        getOut().println(y);
        int expectedRes = 0;//calcQuarter(x, y);

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();
        double actualRes = 0.0;
        try {
            actualRes = Double.parseDouble(actualString);
        } catch (NumberFormatException e) {
            fail("\n--- Проверка корректности результата ---\nРезультатом должно быть числом, а не " + actualString);
        }

        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка корректности результата---\nПри введенных x=" + x + " и y= "+ y + ", четверть должна быть [" + expectedRes + "], а не [" + actualString + "]",
                expectedRes == actualRes);
    }

    private int calcQuarter(int x, int y) {
        if (x > 0 && y > 0) {
            return 1;
        } else if (x < 0 && y > 0) {
            return 2;
        } else if (x < 0 && y < 0) {
            return 3;
        } else {
            return 4;
        }
    }

    private int generateNumber(int from, int to) {
        int range = to - from;
        return (int)(Math.random() * range + from);
    }
}
