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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Написать метод рисующий лесенку из звездочек, высота равна ширине, и передается в метод в качестве параметра. например:
В случае, если значение высоты имеет неправильное значение, выводить сообщение, например: Неверное значение высоты
Метод: void drawStair(int height)
Пример:
 drawStair(4)
*
**
***
****
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 10.06.14
 */
@Unit(testName = "F2StarStairTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F2StarStairTest extends BaseTest {
    public void drawStair(int height) {
        if (height <= 0) {
            System.out.println("Неверное значение высоты");
            return;
        }
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }

    private static final String UNIT_NAME = "StarStair";
    private static final String UNIT_METHOD_NAME = "drawStair";

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
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
    }

    @Test(timeout = 1000)
    public void testNormalDraw() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = generateInt(1, 5);
        String expectedStair = generateStair(height);
        ReflectionUtil.invokeMethod(instance, unitMethod, height);
        String actualStair = getIn().toString().trim();

        assertTrue("В задании должен выполняться вывод текста ", !actualStair.isEmpty());
        assertTrue("\n--- Проверка правильности вывода лесенки ---\n" +
                "Метод должен выводить лесенку высотой " + height + ", а не "
                + actualStair, expectedStair.equals(actualStair));
    }

    @Test(timeout = 1000)
    public void testHeightIncorrect() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = generateInt(-5, -1);
        String expectedMessage = "Неверное значение высоты";
        ReflectionUtil.invokeMethod(instance, unitMethod, height);
        String actualMessage = getIn().toString().trim();

        assertTrue("\n--- Проверка некорректного значения высоты ---\n" +
                "При неправильном значении высоты должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }

    @Test(timeout = 1000)
    public void testZeroHeight() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = 0;
        String expectedMessage = "Неверное значение высоты";
        ReflectionUtil.invokeMethod(instance, unitMethod, height);
        String actualMessage = getIn().toString().trim();

        assertTrue("\n--- Проверка нулевого значения высоты ---\n" +
                "При неправильном значении высоты ["+ 0 +"] должно быть выведено сообщение " + expectedMessage + ", а не "
                + actualMessage, expectedMessage.equals(actualMessage));
    }

    private String generateStair(int height) {
        StringBuilder sb = new StringBuilder(height * height + height);
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
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
