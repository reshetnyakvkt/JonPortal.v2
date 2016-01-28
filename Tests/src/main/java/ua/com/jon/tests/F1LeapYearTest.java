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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Пользователь вводит год, определить является ли он високосным
(год является високосным в двух случаях: либо он кратен 4, но при этом не кратен 100, либо кратен 400)
Пример:
2014
Невисокосный
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 31.05.14
 */
@Unit(testName = "F1LeapYearTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(Parameterized.class)
public class F1LeapYearTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int year = scan.nextInt();
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            System.out.println("Високосный");
        } else {
            System.out.println("Невисокосный");
        }
    }

//    private int year;
//    private String yearType;

/*
    public F1LeapYearTest(int year, String yearType) {
        this.year = year;
        this.yearType = yearType;
    }
*/

/*    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][]{
                {400, "Високосный"},
                {1600, "Високосный"},
                {2016, "Високосный"},
                {1500, "Невисокосный"},
                {1800, "Невисокосный"},
                {2015, "Невисокосный"},
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
    public void testLeap1() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int year = 400;
        getOut().println(year);
//        String expectedRes = calcYearType(year);//yearType;

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();

        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка на високосный год ---\nПри введенном году " + year +
                        ", должно быть выведено [Високосный], а не [" + actualString + "]",
                "Високосный".equals(actualString)
        );
    }
    @Test(timeout = 1000)
    public void testLeap2() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }

        int year = 1600;
        getOut().println(year);
//        String expectedRes = calcYearType(year);//yearType;

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();

//        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка на високосный год ---\nПри введенном году " + year +
                        ", должно быть выведено [Високосный], а не [" + actualString + "]",
                "Високосный".equals(actualString)
        );
    }
    @Test(timeout = 1000)
    public void testLeap3() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }

        int year = 2016;
        getOut().println(year);
//        expectedRes = calcYearType(year);//yearType;

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();

//        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка на високосный год ---\nПри введенном году " + year +
                        ", должно быть выведено [Високосный], а не [" + actualString + "]",
                "Високосный".equals(actualString));
    }

    @Test(timeout = 1000)
    public void testNotLeap1() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int year = 1500;
        getOut().println(year);
//        String expectedRes = calcYearType(year);//yearType;

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();

//        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка на невисокосный год ---\nПри введенном году " + year +
                        ", должно быть выведено [Невисокосный], а не [" + actualString + "]",
                "Невисокосный".equals(actualString)
        );
    }
    @Test(timeout = 1000)
    public void testNotLeap2() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }

        int year = 1800;
        getOut().println(year);
//        String expectedRes = calcYearType(year);//yearType;

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();

//        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка на не високосный год ---\nПри введенном году " + year +
                        ", должно быть выведено [Невисокосный], а не [" + actualString + "]",
                "Невисокосный".equals(actualString)
        );
    }
    @Test(timeout = 1000)
    public void testNotLeap3() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }

        int year = 2015;
        getOut().println(year);
//        String expectedRes = calcYearType(year);//yearType;

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();

//        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка на невисокосный год ---\nПри введенном году " + year +
                        ", должно быть выведено [Невисокосный], а не [" + actualString + "]",
                "Невисокосный".equals(actualString));
    }

    private String calcYearType(int year) {

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return "Високосный";
        } else {
            return "Невисокосный";
        }

    }
}
