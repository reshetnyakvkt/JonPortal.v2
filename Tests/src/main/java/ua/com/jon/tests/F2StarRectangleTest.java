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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Написать метод рисующий закрашенный прямоугольник из звездочек, высота и ширина передаются методу в качестве параметра
В случае, если какой то параметр имеет неправильное значение, выводить сообщение, например: Неверное значение ширины
Метод: void drawRectangle(int height, int width)
Пример:
 drawRectangle(3, 4)
****
****
****
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 10.06.14
 */
@Unit(testName = "F2StarRectangleTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F2StarRectangleTest extends BaseTest {
    public void drawRectangle(int height, int width) {
        if (height <= 0) {
            System.out.println("Неверное значение высоты");
            return;
        }
        if (width <= 0) {
            System.out.println("Неверное значение ширины");
            return;
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }

    private static final String UNIT_NAME = "StarRectangle";
    private static final String UNIT_METHOD_NAME = "drawRectangle";

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
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class, int.class);
    }

    @Test(timeout = 1000)
    public void testNormalDraw() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = generateInt(1, 5);
        int width = generateInt(1, 5);
        String expectedRectangle = generateRectangle(height, width);
        ReflectionUtil.invokeMethod(instance, unitMethod, height, width);
        String actualRectangle = getIn().toString().trim();

        assertTrue("В задании должен выполняться вывод текста ", !actualRectangle.isEmpty());
        assertTrue("\n--- Проверка правильности вывода прямоугольника---\n" +
                "Метод должен выводить прямоугольник размером " + height + " на " + width + ", а не "
                + actualRectangle, expectedRectangle.equals(actualRectangle));
    }

    @Test(timeout = 1000)
    public void testHeightIncorrect() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = generateInt(-10, -1);
        int width = generateInt(1, 5);
        String expectedMessage = "Неверное значение высоты";
        ReflectionUtil.invokeMethod(instance, unitMethod, height, width);
        String actualMessage = getIn().toString().trim();

        assertTrue("\n--- Проверка некорректного значения высоты ---\n" +
                "При неправильном значении высоты должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }

    @Test(timeout = 1000)
    public void testWidthIncorrect() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = generateInt(1, 5);
        int width = generateInt(-10, -1);
        String expectedMessage = "Неверное значение ширины";
        ReflectionUtil.invokeMethod(instance, unitMethod, height, width);
        String actualMessage = getIn().toString().trim();

        assertTrue("\n--- Проверка некорректного значения ширины ---\n" +
                "При неправильном значении ширины должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }

    @Test(timeout = 1000)
    public void testZeroHeight() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = 0;
        int width = generateInt(5, 10);
        String expectedMessage = "Неверное значение высоты";
        ReflectionUtil.invokeMethod(instance, unitMethod, height, width);
        String actualMessage = getIn().toString().trim();

        assertTrue("\n--- Проверка нулевого значения высоты ---\n" +
                "При неправильном значении высоты ["+ 0 +"] должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }

    @Test(timeout = 1000)
    public void testZeroWidth() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = generateInt(5, 10);
        int width = 0;
        String expectedMessage = "Неверное значение ширины";
        ReflectionUtil.invokeMethod(instance, unitMethod, height, width);
        String actualMessage = getIn().toString().trim();

        assertTrue("\n--- Проверка нулевого значения ширины ---\n" +
                "При неправильном значении ширины ["+ 0 +"] должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }

    private String generateRectangle(int height, int width) {
        StringBuilder sb = new StringBuilder(height * width + height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append('*');
            }
            sb.append('\n');
        }
        return sb.toString().trim();
    }


    private int generateInt(int from, int to) {
        int range = to - from;
        return (int) (Math.random() * range + from);
    }

}
