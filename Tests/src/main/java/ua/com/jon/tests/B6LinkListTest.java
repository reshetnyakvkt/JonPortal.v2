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
 Написать класс LinkList, реалзизующем связанный список с классом элемента Node.
 Для класса LinkList написать методы:

 1. Замена значения элемента по индексу, метод void set(int index, int element)
 2. Удаление элемента по индексу, метод void remove(int index)
 3. Поиск элемента по значению (сравнение через equals), метод int indexOf(int element)
 4. Поменять местами первый и последний элементы, метод void swapFirstLast()
 5. Перестроить элементы в списке в обратном порядке, метод void revert()
 6. Поменять местами наибольший и наименьший элементы, в случае если реализован интерфейс Comparable, метод void swapMaxMin()
 7. Частично упорядочить элементы списка, в случае если реализован интерфейс Comparable, метод void sortPartial(int index)
 8. Сравнение двух списков, метод boolean equals(LinkList list)
 9. Сложение двух списков, метод LinkList concat(LinkList list)
 10*. Отсортировать список, в случае если реализован интерфейс Comparable, метод void sort()
 11*. Удалить повторяющиеся элементы из списка, в случае если реализован интерфейс Comparable, метод boolean removeDuplicates()
 */
@Unit(testName = "B6LinkListTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B6LinkListTest extends BaseTest {
    private static final String LIST_NAME = "LinkList";
    private static final String NODE_NAME = "Node";
    private static final String SET_METHOD_NAME = "set";
    private static final String REMOVE_METHOD_NAME = "remove";
    private static final String INDEX_OF_METHOD_NAME = "indexOf";
    private static final String SWAP_FL_METHOD_NAME = "swapFirstLast";
    private static final String REVERT_METHOD_NAME = "revert";
    private static final String SWAP_MM_METHOD_NAME = "swapMaxMin";
    private static final String SORT_P_METHOD_NAME = "sortPartial";
    private static final String EQUALS_METHOD_NAME = "equals";
    private static final String CONCAT_METHOD_NAME = "concat";
    private static final String SORT_METHOD_NAME = "sort";
    private static final String REMOVE_D_METHOD_NAME = "removeDuplicates";

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
        method = ReflectionUtil.checkMethod(unitClass, SET_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class, int.class);
        method = ReflectionUtil.checkMethod(unitClass, REMOVE_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        method = ReflectionUtil.checkMethod(unitClass, INDEX_OF_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        method = ReflectionUtil.checkMethod(unitClass, SWAP_FL_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        method = ReflectionUtil.checkMethod(unitClass, REVERT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        method = ReflectionUtil.checkMethod(unitClass, SORT_P_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        method = ReflectionUtil.checkMethod(unitClass, EQUALS_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "LinkList");
        method = ReflectionUtil.checkMethod(unitClass, CONCAT_METHOD_NAME, "LinkList",
                new MethodModifier[]{MethodModifier.PUBLIC}, "LinkList");
        method = ReflectionUtil.checkMethod(unitClass, SWAP_MM_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
/*        method = ReflectionUtil.checkMethod(unitClass, SORT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});*/
/*        method = ReflectionUtil.checkMethod(unitClass, REMOVE_D_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});*/

        Class nodeClass = getUnitClass(unitClasses, NODE_NAME);
        assertNotNull("В задании не найден класс " + NODE_NAME, nodeClass);
        CodeValidator.checkCode(codes.get(nodeClass.getName()));
//        ReflectionUtil.checkDefaultConstructor(nodeClass);
    }
}
