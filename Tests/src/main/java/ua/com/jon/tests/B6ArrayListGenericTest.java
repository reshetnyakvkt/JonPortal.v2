package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Написать собственную реализацию динамического массива MyArrayList.
 Сделать параметризированную релизацию списка, параметр E.
 Реализовать следующие методы
 - void add(E value)
 - int get(int index)
 - boolean set(int index, E value)
 - boolean add(int index, E value)
 - int indexOf(E value)
 - int size()
 - E remove(int index)
 */
@Unit(testName = "B6ArrayListGenericTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B6ArrayListGenericTest extends P0ArrayListGenericTest {

    private static final String UNIT_NAME = "MyArrayList";

    @UnitCode
    protected static Map<String, String> codes;
    @UnitClass
    protected static Class[] unitClasses;
    @UnitName
    protected static String[] unitNames;
    @Unit
    protected static String unitJarClasspath;
    @Troubles
    private static List<String> troubles;

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

//    private static Object instance;
//    private static Method method;

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должено быть не более 2х классов", unitClasses.length <= 2);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCode(codes.get(unitClass.getName()));
        StyleChecker.checkStyle(codes, troubles);
        ReflectionUtil.checkDefaultConstructor(unitClass);
//        instance = instanciate(unitClass);
        ReflectionUtil.checkHasGeneric(unitClass, "E");
    }

/*
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

*/
    public void testIterator() throws Throwable {

    }
}
