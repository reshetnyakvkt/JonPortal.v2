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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 11.05.14
 */
@Unit(testName = "P1HashTable", value = "hw2.hash.HashTable")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P1HashTable extends BaseTest {

    private static final String UNIT_NAME = "MyHashMap";
    private static final String TEST_NAME = "MyHashMapTest";
    private static final String PUT_METHOD_NAME = "put";
    private static final String ITERATOR_METHOD_NAME = "iterator";

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

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void testCheckUnitPresent() throws Throwable {
        assertTrue("В задании должно быть не более 4х классов, а не " + unitClasses.length, unitClasses.length <= 5);
        CodeValidator.checkCodeFile(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        Method methodPut = ReflectionUtil.checkMethod(unitClass, PUT_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "int", "User");
        Method methodIterator = ReflectionUtil.checkMethod(unitClass, ITERATOR_METHOD_NAME, Iterator.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
//        assertTrue("В задании не найден метод " + UNIT_NAME, UNIT_NAME.equals(unitClass.getSimpleName()));
    }

    @Test(timeout = 1000)
    public void testCheckTestPresent() throws Throwable {
        Class unitClass;
        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, TEST_NAME);
            assertNotNull("В задании не найден класс теста " + TEST_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
        assertTrue("В задании не найден класс теста " + TEST_NAME, TEST_NAME.equals(unitClass.getSimpleName()));
    }
}
