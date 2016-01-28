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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Написать метод возвращающий количество нечетных элементов массива
В случае, если размер вектора некорректный, выводить сообщение "Неверный размер вектора"
Метод: int calcNonPairCount(int[] vector)
Пример:
 calcNonPairCount(int[] vector); // [1234567]
4

 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 13.06.14
 */
@Unit(testName = "F2NonPairVectorTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F2NonPairVectorTest extends BaseTest {
    public int calcNonPairCount(int[] vector) {
        int sum = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] % 2 != 0) {
                sum++;
            }
        }
        return sum;
    }

    private static final String UNIT_NAME = "NonPairVector";
    private static final String UNIT_METHOD_NAME = "calcNonPairCount";

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
        unitMethod = ReflectionUtil.checkMethod(unitClasses[0], UNIT_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[].class);
    }

    @Test(timeout = 1000)
    public void testNormalDraw() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int from = generateInt(0, 3);
        int to = generateInt(8, 10);
        int[] expectedVector = generateVector(from, to);
        int actualRes = (Integer) ReflectionUtil.invokeMethod(instance, unitMethod, expectedVector.clone());
        int expectedRes = calcNonPairs(expectedVector);

        assertTrue("\n--- Проверка правильности вычисление нечетных " + Arrays.toString(expectedVector) + " ---\n" +
                "Метод должен возвращать количество нечетных " + expectedRes + ", а не "
                + actualRes, expectedRes == actualRes);
    }

    @Test(timeout = 1000)
    public void testLengthZero() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int length = 0;
        int[] expectedVector = new int[length];
        String expectedMessage = "Неверный размер вектора";
        try {
            ReflectionUtil.invokeMethodWithExceptions(instance, unitMethod, expectedVector);
        } catch (Throwable e) {
            if (e.getCause() != null && e.getCause().getClass() == ArithmeticException.class) {
                fail("\n--- Проверка некорректного размера вектора 0 ---\n" +
                        "было выброшено исключение " + e.getCause().getClass().getSimpleName() +
                        ":" + e.getCause().getMessage());
            }
            e.printStackTrace();
        }

        String actualMessage = getIn().toString().trim();

        assertTrue("\n--- Проверка некорректного размера вектора 0 ---\n" +
                "При неправильном размере вектора должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }

/*
    @Test(timeout = 1000)
    public void testLengthNull() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        String expectedMessage = "Неверный размер вектора";
        try {
            ReflectionUtil.invokeMethodWithExceptions(instance, unitMethod, null);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        String actualMessage = getIn().toString().trim();

        assertTrue("\n--- Проверка null вектора ---\n" +
                "При неправильном векторе - null, должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }
*/

    @Test(timeout = 1000)
    public void testLengthOneNonPair() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int[] expectedVector = new int[]{generateInt(3, 10) / 2 + 1};
        int actualRes = (Integer) ReflectionUtil.invokeMethod(instance, unitMethod, expectedVector.clone());
        int expectedRes = calcNonPairs(expectedVector);

        assertTrue("\n--- Проверка правильности подсчета количества нечетных " + Arrays.toString(expectedVector) + " ---\n" +
                "Метод должен возвращать количество нечетных " + expectedRes + ", а не "
                + actualRes, expectedRes == actualRes);
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

    private int generateInt(int from, int to) {
        int range = to - from;
        return (int) (Math.random() * range + from);
    }

    private int calcNonPairs(int[] vector) {
        int sum = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] % 2 != 0) {
                sum++;
            }
        }
        return sum;
    }
}
