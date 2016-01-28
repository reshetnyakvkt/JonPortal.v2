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

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 Написать метод, выполняющий двоичный поиск (не рекурсивный) элемента в массиве.
 */
@Unit(testName = "B7BinarySearchTest", value = "hw8.bsearch.BinarySearcher")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B7BinarySearchTest extends BaseTest {
    class BinarySearcher {
        public void binarySearch(int[] vector) {
        }
    }
    private static final String UNIT_NAME = "BinarySearcher";
    private static final String SEARCH_METHOD_NAME = "binarySearch";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method method;
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

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        CodeValidator.checkCode(codes.get(unitClass.getName()));
        StyleChecker.checkStyle(codes, troubles);
        ReflectionUtil.checkDefaultConstructor(unitClass);

        method = ReflectionUtil.checkMethod(unitClass, SEARCH_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[].class, int.class);
        instance = instanciate(unitClasses[0]);
    }

    @Test(timeout = 1000)
    public void testElemPresent() throws Throwable {
        if (instance == null || method == null) {
            fail();
        }
        int elem = (int)(Math.random() * 10 + 10);
        int[] actualVector = generateSortedArray(3, 10, elem);
        int actualResult = (int)ReflectionUtil.invokeMethod(instance, method, actualVector, elem);

        assertTrue("В массиве " + Arrays.toString(actualVector) + " находится искомый элемент " + elem + ", но метод " + method.getName() +
                " вернул " + actualResult, actualResult > 0);
    }

    @Test(timeout = 1000)
    public void testElemAbsent() throws Throwable {
        if (instance == null || method == null) {
            fail();
        }
        int elem = (int)(Math.random() * 10 + 20);
        int[] actualVector = generateSortedArray(3, 10, 0);
        int actualResult = (int)ReflectionUtil.invokeMethod(instance, method, actualVector, elem);

        assertTrue("В массиве " + Arrays.toString(actualVector) + " отсутствует искомый элемент " + elem + ", но метод " + method.getName() +
                " вернул " + actualResult, actualResult < 0);
    }

    @Test(timeout = 1000)
    public void testElemPresentSingle() throws Throwable {
        if (instance == null || method == null) {
            fail();
        }
        int elem = (int)(Math.random() * 10 + 20);
        int[] actualVector = {elem};
        int actualResult = (int)ReflectionUtil.invokeMethod(instance, method, actualVector, elem);

        assertTrue("В массиве " + Arrays.toString(actualVector) + " находится искомый элемент " + elem + ", но метод " + method.getName() +
                " вернул " + actualResult, actualResult == elem);
    }

    private int[] generateSortedArray(int from, int to, int elem) {
        int length = (int)(Math.random() * 10 + 10) / 2 + 1;
        int[] vector = new int[length];
        int range = to - from;

        for (int i = 0; i < vector.length; i++) {
            int rnd = (int) (Math.random() * range + from);
            vector[i] = rnd;
        }
        int elemIdx =  (int) (Math.random() * length - 1);
        vector[elemIdx] = elem;
        Arrays.sort(vector);
        return vector;
    }
}
