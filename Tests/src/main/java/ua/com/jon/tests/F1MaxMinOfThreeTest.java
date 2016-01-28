package ua.com.jon.tests;

import com.jon.tron.service.junit.*;

import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/24/13
 */
@Unit(testName = "F1MaxMinOfThreeTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F1MaxMinOfThreeTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int first = scan.nextInt();
        int second = scan.nextInt();
        int third = scan.nextInt();
        if(first > second) {
            if(first > third) {
                System.out.print(first);
            } else {
                System.out.print(third);
            }
        } else {
            if(second > third) {
                System.out.print(second);
            } else {
                System.out.print(third);
            }
        }
        System.out.print(" ");
        if(first > second) {
            if(second < third) {
                System.out.print(first);
            } else {
                System.out.print(third);
            }
        } else {
            if(first < third) {
                System.out.print(first);
            } else {
                System.out.print(third);
            }
        }
    }

    private static final String UNIT_NAME = "MaxMinOfThree";

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
        final int MAX_NUMBER = 10;
        int first = rnd.nextInt(MAX_NUMBER);
        int second = rnd.nextInt(MAX_NUMBER);
        int third = rnd.nextInt(MAX_NUMBER);

        int expectedMax = maxOfThree(first, second, third);
        int expectedMin = minOfThree(first, second, third);

        getOut().println(first);
        getOut().println(second);
        getOut().println(third);

        ReflectionUtil.invokeMain(instance);

        String actualMaxString = getLastStringFromOut();
        assertTrue("В задании должен выполняться вывод текста " + actualMaxString, !actualMaxString.isEmpty());
        int actualMax = 0;
        int actualMin = 0;
        String[] numbers = actualMaxString.split(" ");
        assertTrue("Должно быть выведено два значения через пробел, а не [" + actualMaxString + "]", numbers.length == 2);

        Scanner scan = new Scanner(actualMaxString);
        if(scan.hasNextInt()) {
            actualMax = scan.nextInt();
        } else {
            fail("\n--- Проверка наибольшего значения ---\nПервым должно быть выведено наибольшее число, но выведено [" + (scan.hasNext()?scan.next():"") + "]");
        }
        if(scan.hasNextInt()) {
            actualMin = scan.nextInt();
        } else {
            fail("\n--- Проверка наименьшего значения ---\nВторым должно быть выведено наименьшее число, но выведено [" + (scan.hasNext()?scan.next():"") + "]");
        }

        assertTrue("\n--- Проверка наибольшего значения ---\nНе верное наибольшее значение " + expectedMax + ", должно быть " + actualMax,
                expectedMax == actualMax);

        assertTrue("\n--- Проверка наименьшего значения ---\nНе верное наименьшее значение " + expectedMin + ", должно быть " + actualMin,
                expectedMin == actualMin);
    }

    private int minOfThree(int first, int second, int third) {
        if(first < second) {
            if(first < third) {
                return first;
            } else {
                return third;
            }
        } else {
            if(second < third) {
                return second;
            } else {
                return third;
            }
        }
    }

    private int maxOfThree(int first, int second, int third) {
        if(first > second) {
            if(first > third) {
                return first;
            } else {
                return third;
            }
        } else {
            if(second > third) {
               return second;
            } else {
                return third;
            }
        }
    }
}
