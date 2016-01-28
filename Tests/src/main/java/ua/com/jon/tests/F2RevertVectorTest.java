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
 Написать метод размещающий элементы массива в обратном порядке(именно размещающий, а не выводящий на экран)
 В случае, если размер вектора некорректный, выводить сообщение "Неверный размер вектора"
 Метод: void revertVector(int[] vector)
 Пример:
  revertVector(int[] vector); // [1,2,3,4,5,6,7]
  [7,6,5,4,3,2,1]

 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 12.06.14
 */
@Unit(testName = "F2RevertVectorTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F2RevertVectorTest extends BaseTest {
    public void revertVector(int[] vector) {
        if (vector == null || vector.length <= 0) {
            System.out.println("Неверный размер вектора");
            return;
        }

        int tmp;
        for (int i = 0; i < vector.length / 2; i++) {
            tmp = vector[vector.length - 1 - i];
            vector[vector.length - 1 - i] = vector[i];
            vector[i] = tmp;
        }

        System.out.println(Arrays.toString(vector));
    }

    private static final String UNIT_NAME = "RevertVector";
    private static final String UNIT_METHOD_NAME = "revertVector";

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
        int[] reverted = expectedVector.clone();
        revertVectorSup(reverted);
        String actualVector = getIn().toString().trim();

        assertTrue("В задании должен выполняться вывод текста ", !actualVector.isEmpty());
        assertTrue("\n--- Проверка правильности вывода массива "+Arrays.toString(expectedVector)+" ---\n" +
                "Метод должен выводить массив в обратном порядке " + Arrays.toString(reverted) + ", а не "
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
                "При неправильном размере вектора должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }

/*    @Test(timeout = 1000)
    public void testLengthNull() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        String expectedMessage = "Неверный размер вектора";
        try {
            ReflectionUtil.invokeMethod(instance, unitMethod, null);
        } catch (Throwable t) {  }

        String actualMessage = getIn().toString().trim();

        assertTrue("\n--- Проверка null вектора ---\n" +
                "При неправильном векторе - null, должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }*/

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

    private void revertVectorSup(int[] vector) {
        int tmp;
        for (int i = 0; i < vector.length / 2; i++) {
            tmp = vector[vector.length - 1 - i];
            vector[vector.length - 1 - i] = vector[i];
            vector[i] = tmp;
        }
    }
}
