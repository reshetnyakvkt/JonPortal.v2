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
Написать метод меняющий местами половины массива, если размер нечетный - центральный элемент не учитывается
В случае, если размер вектора некорректный, выводить сообщение "Неверный размер вектора", иначе выводить массив
в красивом формате [1, 2, 3]
Метод: void swapHalves(int[] vector)
Пример:
 swapHalves(int[] vector); // [1234567]
[5, 6, 7, 4, 1, 2, 3]

 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 10.06.14
 */
@Unit(testName = "F2VectorHalfSwapTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F2VectorHalfSwapperTest extends BaseTest {
    public void swapHalves(int[] vector) {
        if (vector == null || vector.length <= 0) {
            System.out.println("Неверный размер вектора");
            return;
        }
        if (vector.length == 1) {
            System.out.println(Arrays.toString(vector));
            return;
        }
        int[] newVector = new int[vector.length];
        for (int i = 0, j = vector.length / 2; i < vector.length / 2; i++, j++) {
            newVector[j] = vector[i];
        }
        System.out.println(Arrays.toString(newVector));
    }

    private static final String UNIT_NAME = "StarStair";
    private static final String UNIT_METHOD_NAME = "swapHalves";

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
        int from = generateInt(3, 6);
        int to = generateInt(7, 10);
        int[] expectedVector = generateVector(from, to);
        ReflectionUtil.invokeMethod(instance, unitMethod, expectedVector.clone());
        swapHalvesSup(expectedVector);
        String actualVector = getIn().toString().trim();

        assertTrue("В задании должен выполняться вывод текста ", !actualVector.isEmpty());
        assertTrue("\n--- Проверка правильности вывода массива ---\n" +
                "Метод должен выводить массив с обмененными половинами " + Arrays.toString(expectedVector) + ", а не "
                + actualVector, Arrays.toString(expectedVector).equals(actualVector));
    }

    @Test(timeout = 1100)
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

        assertTrue("\n--- Проверка некорректного значения размера 0 ---\n" +
                "При неправильном размере вектора должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }

/*    @Test(timeout = 1000)
    public void testLengthNull() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        String expectedMessage = "Неверный размер вектора";
        ReflectionUtil.invokeMethod(instance, unitMethod, null);
        String actualMessage = getIn().toString().trim();

        assertTrue("\n--- Проверка некорректного значения размера ---\n" +
                "При неправильном размере вектора должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }*/

    @Test(timeout = 1100)
    public void testLengthOne() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int[] expectedVector = new int[]{generateInt(3, 10)};
        ReflectionUtil.invokeMethod(instance, unitMethod, expectedVector.clone());
        String actualVector = getIn().toString().trim();

        assertTrue("\n--- Проверка единичного массива ---\n" +
                "В задании должен выполняться вывод текста ", !actualVector.isEmpty());
        assertTrue("\n--- Проверка единичного массива ---\n" +
                "Метод должен выводить массив с обмененными половинами " + Arrays.toString(expectedVector) + ", а не "
                + actualVector, Arrays.toString(expectedVector).equals(actualVector));
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

    private int[] swapHalvesSup(int[] vector) {
//        int[] newVector = new int[vector.length];
        for (int i = 0, j = vector.length / 2; i < vector.length / 2; i++, j++) {
            int tmp = vector[j];
            vector[j] = vector[i];
            vector[i] = tmp;
        }
        return vector;
    }
}
