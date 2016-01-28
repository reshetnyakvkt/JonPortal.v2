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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 Написать метод определяющий, является ли массив палиндромом (одинаково читается и справа и слева)
 Пирмер палиндрома: 1234321
 Имя метода: boolean isVectorPalindrom(int[] vector)

 */
@Unit(testName = "F2VectorPalindromTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F2VectorPalindromTest extends BaseTest {
    public boolean isVectorPalindrom(int[] vector) {
        boolean res = true;
        for (int i = 0, j = vector.length - 1; i < vector.length / 2; i++, j--) {
            if (vector[i] != vector[j]) {
                res = false;
            }
        }
        return res;
    }

    private static final String UNIT_NAME = "VectorRandomFill";
    private static final String UNIT_METHOD_NAME = "isVectorPalindrom";

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
        unitMethod = ReflectionUtil.checkMethod(unitClasses[0], UNIT_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[].class);
    }


    @Test(timeout = 1000)
    public void testNonPalindom() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int[] actualVector = generateVector(0, 10);
        boolean actualResult = (Boolean)ReflectionUtil.invokeMethod(instance, unitMethod, actualVector);

        assertFalse("Переданный в метод массив не является палиндромом, но ваш метод вернул " + actualResult, actualResult);
    }

    @Test(timeout = 1000)
    public void testPairPalindom() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int[] actualVector = generatePairPalindrom(0, 10);
        boolean actualResult = (Boolean)ReflectionUtil.invokeMethod(instance, unitMethod, actualVector);

        assertTrue("Переданный в метод массив "+Arrays.toString(actualVector)+" является палиндромом, но ваш метод вернул " +
                actualResult, actualResult);
    }

    @Test(timeout = 1000)
    public void testNonPairPalindom() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int[] actualVector = generateNonPairPalindrom(0, 10);
        boolean actualResult = (Boolean)ReflectionUtil.invokeMethod(instance, unitMethod, actualVector);

        assertTrue("Переданный в метод массив " + Arrays.toString(actualVector) + " является палиндромом, но ваш метод вернул " +
                actualResult, actualResult);
    }

    private int[] generateVector(int from, int to) {
        int length = (int)(Math.random() * 10 + 10);
        int[] vector = new int[length];
        int range = to - from;
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (int)(Math.random() * range + from);
        }
        return vector;
    }

    private int[] generatePairPalindrom(int from, int to) {
        int length = (int)(Math.random() * 10 + 10) / 2;
        int[] vector = new int[length];
        int range = to - from;
        for (int i = 0, j = vector.length - 1; i < vector.length / 2; i++, j--) {
            int rnd = (int) (Math.random() * range + from);
            vector[i] = rnd;
            vector[j] = rnd;
        }
        return vector;
    }

    private int[] generateNonPairPalindrom(int from, int to) {
        int length = (int)(Math.random() * 10 + 10) / 2 + 1;
        int[] vector = new int[length];
        int range = to - from;
        for (int i = 0, j = vector.length - 1; i < vector.length / 2; i++, j--) {
            int rnd = (int) (Math.random() * range + from);
            vector[i] = rnd;
            vector[j] = rnd;
        }
        return vector;
    }
}
