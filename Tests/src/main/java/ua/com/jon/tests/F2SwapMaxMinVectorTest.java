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
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Написать метод, меняющий местами первые найденные наибольший и наименьший элементы вектора.
В случае, если размер вектора некорректный, выводить сообщение "Неверный размер вектора"
Название метода: swapMaxMinVector
Пример:
 swapMaxMinVector(int[] vector); // [4,4,3,3,1,1]
 [1,4,3,3,4,1]
 */
@Unit(testName = "F2SwapMaxMinVectorTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F2SwapMaxMinVectorTest extends BaseTest {
//    import java.util.Arrays;
    public class A {
        public void swapMaxMinVector(int[] vector) {
            if (vector.length == 0) {
                System.out.println("Неверный размер вектора");
                return;
            }
            int maxIdx = 0;
            int minIdx = 0;
            for (int i = 0; i < vector.length; i++) {
                if (vector[i] > vector[maxIdx]) {
                    maxIdx = i;
                }
                if (vector[i] < vector[minIdx]) {
                    minIdx = i;
                }
            }
            int tmp = vector[maxIdx];
            vector[maxIdx] = vector[minIdx];
            vector[minIdx] = tmp;

            System.out.println(Arrays.toString(vector));
        }
    }

    private static final String UNIT_NAME = "RevertVector";
    private static final String UNIT_METHOD_NAME = "swapMaxMinVector";

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
        unitMethod = ReflectionUtil.checkMethod(unitClasses[0], UNIT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[].class);
    }

    @Test(timeout = 1000)
    public void testNormalDraw() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int from = generateInt(0, 2);
        int to = generateInt(7, 10);
        int[] expectedVector = generateVector(from, to);
        ReflectionUtil.invokeMethod(instance, unitMethod, expectedVector.clone());
        int[] reverted = expectedVector.clone();
        swapMaxMinVector(reverted);
        String actualVector = getIn().toString().trim();

        assertTrue("В задании должен выполняться вывод текста ", !actualVector.isEmpty());
        assertTrue("\n--- Проверка правильности вывода массива "+Arrays.toString(expectedVector)+" ---\n" +
                "Метод должен выводить массив с обмененными наибольшим и наименьшим элементами " + Arrays.toString(reverted) + ", а не "
                + actualVector, Arrays.toString(reverted).equals(actualVector));
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

        assertTrue("\n--- Проверка нулевого размера вектора ---\n" +
                "При неправильном размере вектора должно быть выведено сообщение " + expectedMessage + ", а не ["
                + actualMessage + "]", expectedMessage.equals(actualMessage));
    }

    @Test(timeout = 1000)
    public void testLengthOne() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int[] expectedVector = new int[]{generateInt(3, 10)};
        ReflectionUtil.invokeMethod(instance, unitMethod, expectedVector.clone());
        String actualVector = getIn().toString().trim();

        assertTrue("\n--- Проверка единичного массива ---\n" +
                "В задании должен выполняться вывод текста ", !actualVector.isEmpty());
        assertTrue("\n--- Проверка правильности вывода массива "+Arrays.toString(expectedVector)+" ---\n" +
                "Метод должен выводить массив c обмененными наибольшим и наименьшим элементами " + Arrays.toString(expectedVector) + ", а не "
                + actualVector, Arrays.toString(expectedVector).equals(actualVector));
    }

    private int[] generateVector(int from, int to) {
        int length = (int)(Math.random() * 7 + 3);
//        Set<Integer> vector = new HashSet<>();
        int[] vector = new int[length];
        int range = to - from;
        for (int i = 0; i < vector.length; i++) {
            vector[i] =(int) (Math.random() * range + from);
        }
//        while (vector.size() < length) {
//            vector.add((int) (Math.random() * range + from));
//        }
//        return vector.stream().mapToInt(Integer::intValue).toArray();
        return vector;
    }

    private int generateInt(int from, int to) {
        int range = to - from;
        return (int) (Math.random() * range + from);
    }

    private void swapMaxMinVector(int[] vector) {
        int maxIdx = 0;
        int minIdx = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] > vector[maxIdx]) {
                maxIdx = i;
            }
            if (vector[i] < vector[minIdx]) {
                minIdx = i;
            }
        }
        int tmp = vector[maxIdx];
        vector[maxIdx] = vector[minIdx];
        vector[minIdx] = tmp;
    }
}
