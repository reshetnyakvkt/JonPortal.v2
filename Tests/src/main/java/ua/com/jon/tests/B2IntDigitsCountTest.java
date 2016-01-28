package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 Пользователь вводит число. Считать ввиде числа. Определить сумму цифр числа.
 Считывание строки выполнить в методе main.
 Написать метод определения суммы и результат вывести на экран.
 public boolean calcDigitsCount(int number)
 Пример:
 calcDigitsCount(133)
 7
 */
@Unit(testName = "B2IntDigitsCountTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B2IntDigitsCountTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int first = scan.nextInt();
        int second = scan.nextInt();
        System.out.println(first + second);
    }
    public static int calcDigitsCount(int number) {
        String strNumber = String.valueOf(number);
        int sum = 0;
        for (int i = 0; i < strNumber.length(); i++) {
            int anInt = Integer.parseInt(String.valueOf(strNumber.charAt(i)));
            sum += anInt;
        }
        return sum;
    }
    private static final int MAX_SIZE = 10000;
    private static final int MIN_SIZE = 100;
    private static final String UNIT_METHOD_NAME = "calcDigitsCount";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;
    private static Object instance;
    private static Method unitMethod;
    @Troubles
    private static List<String> troubles;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        instance = instanciate(unitClasses[0]);
        unitMethod = ReflectionUtil.checkMethod(unitClasses[0], UNIT_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
    }

    @Test(timeout = 1100)
    public void testSuccess() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int number = rnd.nextInt(MAX_SIZE) + MIN_SIZE;
        String strNumber = String.valueOf(number);
        int sum = 0;
        for (int i = 0; i < strNumber.length(); i++) {
            int anInt = Integer.parseInt(String.valueOf(strNumber.charAt(i)));
            sum += anInt;
        }

        int actualSum = (int)ReflectionUtil.invokeMethod(instance, unitMethod, strNumber);

        assertEquals("Ожидаемый результат " + sum + " при введенных параметрах (" + number + "), но выведено " + actualSum,
                sum, actualSum);
    }
}
