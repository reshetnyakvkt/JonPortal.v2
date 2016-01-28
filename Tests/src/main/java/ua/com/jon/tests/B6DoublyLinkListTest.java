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
 Написать класс DoublyLinkList, реалзизующем двусвязанный список с классом элемента DNode.
 Для класса DoublyLinkList написать методы:

 1. Вывести элементы списка в обратном порядке, метод void printRevert()
 2. Добавление элемента в начало, метод void addFirst(int element)
 3. Добавление элемента в конец, метод void addLast(int element)
 4. Перестроить элементы в списке в обратном порядке, метод void revert()
 5. Частично упорядочить элементы списка, в случае если реализован интерфейс Comparable, метод void sortPartial(int index)
 */
@Unit(testName = "B6DoublyLinkListTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B6DoublyLinkListTest extends BaseTest {
    private static final String LIST_NAME = "DoublyLinkList";
    private static final String NODE_NAME = "DNode";
    private static final String PRINT_REVERT_METHOD_NAME = "printRevert";
    private static final String ADD_FIRST_METHOD_NAME = "addFirst";
    private static final String ADD_LAST_METHOD_NAME = "addLast";
    private static final String REVERT_METHOD_NAME = "revert";
    private static final String SORT_P_METHOD_NAME = "sortPartial";

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
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    private static Object instance;
    private static Method method;

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должено быть не более 2х классов", unitClasses.length <= 2);

        Class unitClass = getUnitClass(unitClasses, LIST_NAME);
        assertNotNull("В задании не найден класс " + LIST_NAME, unitClass);
        CodeValidator.checkCode(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);
        StyleChecker.checkStyle(codes, troubles);
//        instance = instanciate(unitClass);
        method = ReflectionUtil.checkMethod(unitClass, PRINT_REVERT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        method = ReflectionUtil.checkMethod(unitClass, ADD_FIRST_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        method = ReflectionUtil.checkMethod(unitClass, ADD_LAST_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        method = ReflectionUtil.checkMethod(unitClass, REVERT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        method = ReflectionUtil.checkMethod(unitClass, SORT_P_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);

        Class nodeClass = getUnitClass(unitClasses, NODE_NAME);
        assertNotNull("В задании не найден класс " + NODE_NAME, nodeClass);
        CodeValidator.checkCode(codes.get(nodeClass.getName()));
//        ReflectionUtil.checkDefaultConstructor(nodeClass);
    }
}
