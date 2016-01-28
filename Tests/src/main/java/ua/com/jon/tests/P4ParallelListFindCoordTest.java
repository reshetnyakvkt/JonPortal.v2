package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Map;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Реализовать в классе MyArrayList метод
 public int parallelIndexOf(E e), выполняющий линейный многопоточный поиск в списке.
 Использовать механизм wait()/notify() для координации потоков.
 В тестах проверить поиск:
 - существующего элемента
 - не существующего элемента
 - не существующего элемента в пустом массиве
 - не существующего элемента в массиве с одним элементом
 - существующего элемента в массиве с одним элементом
 - элемента со значением null

 Класс задания:
 hw4.parallel.MyArrayList

 Класс теста:
 hw4.parallel.MyArrayListTest
 */
@Unit(testName = "P4ParallelListFindCoordTest", value = "hw4.parallel.MyArrayList")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P4ParallelListFindCoordTest extends P0ArrayListGenericTest {
    private static final String UNIT_NAME = "MyArrayList";
    private static final String TEST_NAME = "MyArrayListTest";
    private static final String PARALLEL_INDEX_OF_METHOD_NAME = "parallelIndexOf";

    @UnitCode
    protected static Map<String, String> codes;
    @UnitClass
    protected static Class[] unitClasses;
    @UnitName
    protected static String[] unitNames;
    @Unit
    protected static String unitJarClasspath;

    @Before
    public void setUp() {
        super.setUp();
        P0ArrayListGenericTest.codes = codes;
        P0ArrayListGenericTest.unitClasses = unitClasses;
        P0ArrayListGenericTest.unitNames = unitNames;
        P0ArrayListGenericTest.unitJarClasspath = unitJarClasspath;
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    private static Object instance;
    private static Method method;

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должено быть не более 3х классов", unitClasses.length <= 3);
        Class testClass = getUnitClass(unitClasses, TEST_NAME);
        assertNotNull("В задании не найден класс " + TEST_NAME, testClass);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCodeFileThread(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        instance = instanciate(unitClass);
        method = ReflectionUtil.checkMethod(unitClass, PARALLEL_INDEX_OF_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
    }

    @Test(timeout = 1100)
    public void testParallelIndexOf() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        instance = instanciate(unitClass);
        if (instance == null || method == null) {
            fail();
        }
        int expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        int actualIndex = (Integer) ReflectionUtil.invokeMethod(instance, method, expectedElement);
        assertTrue("Метод indexOf работает не верно, при поиске первого элемента " + expectedElement + ", индекс найденного элемента " +
                actualIndex, 0 == actualIndex);

        int newExpected = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        while (newExpected == expectedElement) {
            newExpected = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        }
        ReflectionUtil.invokeMethod(instance, addMethod, newExpected);
        actualIndex = (Integer) ReflectionUtil.invokeMethod(instance, method, newExpected);

        assertTrue("Метод indexOf работает не верно, при поиске второго элемента " + expectedElement + ", индекс найденного элемента " +
                actualIndex, 1 == actualIndex);
    }

    @Test(timeout = 1100)
    public void testAdd() throws Throwable {
        super.testAdd();
    }

    @Test(timeout = 1100)
    public void testAdd2() throws Throwable {
        super.testAdd2();
    }

    @Test(timeout = 1100)
    public void testGet() throws Throwable {
        super.testGet();
    }

    @Test(timeout = 1100)
    public void testSet() throws Throwable {
        super.testSet();
    }

    @Test(timeout = 1100)
    public void testIndexOf() throws Throwable {
        super.testIndexOf();
    }

    @Test(timeout = 1100)
    public void testSize() throws Throwable {
        super.testSize();
    }

    @Test(timeout = 1100)
    public void testRemove() throws Throwable {
        super.testRemove();
    }

    @Test(timeout = 1100)
    public void testIterator() throws Throwable {
        super.testIterator();
    }
}
