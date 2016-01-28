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

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Создать параметризированный связанный список и реализовать РЕКУРСИВНЫЕ методы:
 1. Замена значения элемента по индексу, void setRec(int index, E element)
 2. Удаление элемента по индексу, E removeRec(int index)
 3. Поиск элемента по значению (сравнение через equals), int indexOfRec(E element)
 4. Поменять местами первый и последний элементы, void swapFirstLastRec()
 */
@Unit(testName = "B7List1RecTest", value = "hw8.reclist.LinkList")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B7List1RecTest extends BaseTest {
    class BinarySearcher {
        public void binarySearch(int[] vector) {
        }
    }
    private static final String UNIT_NAME = "LinkList";
    private static final String SET_METHOD_NAME = "setRec";
    private static final String REMOVE_METHOD_NAME = "removeRec";
    private static final String INDEXOF_METHOD_NAME = "indexOfRec";
    private static final String SWAP_METHOD_NAME = "swapFirstLastRec";

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
        assertTrue("В задании должно быть не более 2х классов, а не " + unitClasses.length, unitClasses.length <= 2);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        CodeValidator.checkCode(codes.get(unitClass.getName()));
        StyleChecker.checkStyle(codes, troubles);
        ReflectionUtil.checkDefaultConstructor(unitClass);

        ReflectionUtil.checkMethod(unitClass, SET_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class, Object.class);
        ReflectionUtil.checkMethod(unitClass, REMOVE_METHOD_NAME, Object.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        ReflectionUtil.checkMethod(unitClass, INDEXOF_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        ReflectionUtil.checkMethod(unitClass, SWAP_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
