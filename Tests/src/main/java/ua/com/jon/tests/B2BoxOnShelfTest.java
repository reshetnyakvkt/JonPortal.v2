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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 Написать метод определения поместится ли коробка полностью на полку?
 Метод принимает 4- аргумента длины и ширины полки и коробки.
 public boolean isContains(int boxWidth, int boxHeight, int shelfWidth, int shelfHeight)
 Пример:
 isContains(3, 4, 5, 3)
 true
 */
@Unit(testName = "B2BoxOnTableTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B2BoxOnShelfTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int first = scan.nextInt();
        int second = scan.nextInt();
        System.out.println(first + second);
    }
    private static final String UNIT_METHOD_NAME = "isContains";

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
        unitMethod = ReflectionUtil.checkMethod(unitClasses[0], UNIT_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class, int.class, int.class, int.class);
    }

    @Test(timeout = 1100)
    public void testSuccess() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int bWidth = rnd.nextInt(100);
        int bHeight = rnd.nextInt(100);
        int sWidth = rnd.nextInt(100);
        int sHeight = rnd.nextInt(100);

        boolean expectedRes = isContains(bWidth, bHeight, sWidth, sHeight);
        boolean actualRes = (boolean)ReflectionUtil.invokeMethod(instance, unitMethod, bWidth, bHeight, sWidth, sHeight);

        assertEquals("Ожидаемый результат " + expectedRes + " при введенных параметрах (" +bWidth+", "+bHeight+", "+sWidth+", "+sHeight+ "), но выведено " + actualRes,
                expectedRes, actualRes);
    }

    private boolean isContains(int bWidth, int bHeight, int sWidth, int sHeight) {
        return (sWidth >= bWidth && sWidth >= bHeight) || (sHeight >= bWidth && sHeight >= bHeight);
    }

}
