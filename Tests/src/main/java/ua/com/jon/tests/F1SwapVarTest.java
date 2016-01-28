package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.ReflectionUtil;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Объявить две переменные типа byte и float, вывести на экран (сначала byte, затем float), поменять их значения местами,
не используя дополнительной переменной и вывести на экран в том же порядке
Пример:
20 4
4 20
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 31.05.14
 */
@Unit(testName = "F1SwapVarTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F1SwapVarTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        byte bVal = 10;
        float fVal = 20.2f;
        System.out.println(bVal + " " + fVal);
        byte tmp = (byte)fVal;
        fVal = bVal;
        bVal = tmp;
        System.out.println(bVal + " " + fVal);
    }

    private static final String UNIT_NAME = "SwapVar";

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
        instance = instanciate(unitClasses[0]);
        unitMethod = ReflectionUtil.checkMainMethod(unitClasses[0]);
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
            StyleChecker.checkStyle(codes, troubles);
        }
        final String PREFIX = "\n--- Проверка корректности результата ---\n";

        ReflectionUtil.invokeMain(instance);

        String actualString =  getIn().toString().trim();
        String[] strings = actualString.split("\n");
        assertTrue(PREFIX + "Должно быть выведено две строки, сначала до обмена, затем после, а не " + actualString, strings.length == 2);

        String[] before = strings[0].split(" ");
        String[] after = strings[1].split(" ");
        assertTrue(PREFIX + "Затем должны быть выведены значения переменных после обмена а не " + strings[1], after.length == 2);
        assertTrue(PREFIX + "Сначала должны быть выведены значения переменных до обмена а не " + strings[0], before.length == 2);

        byte beforeByte = 0;
        try {
            beforeByte = Byte.parseByte(before[0]);
        } catch (NumberFormatException nfe) {
            fail(PREFIX + "Результат должен быть числом типа byte, но выведено [" + actualString + "]");
        }
        float beforeFloat = 0;
        try {
            beforeFloat = Float.parseFloat(before[1]);
        } catch (NumberFormatException nfe) {
            fail(PREFIX + "Результат должен быть числом типа float, но выведено [" + actualString + "]");
        }

        float afterFloat = 0;
        try {
            afterFloat = Float.parseFloat(after[0]);
        } catch (NumberFormatException nfe) {
            fail(PREFIX + "Результат должен быть числом типа float, но выведено [" + actualString + "]");
        }
        byte afterByte = 0;
        try {
            afterByte = (byte)Float.parseFloat(after[1]);
        } catch (NumberFormatException nfe) {
            fail(PREFIX + "Результат должен быть числом типа byte, но выведено [" + actualString + "]");
        }

        assertTrue(PREFIX + "Первое число до обмена " + beforeFloat + " должно быть равно второму числу после обмена "
                        + afterFloat, (byte)beforeFloat == (byte)afterFloat);
        assertTrue(PREFIX + "Второе число до обмена " + beforeByte + " должно быть равно первому числу после обмена "
                        + afterByte, beforeByte == afterByte);
    }
}
