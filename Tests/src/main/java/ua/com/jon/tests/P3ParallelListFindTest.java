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

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Реализовать в классе MyArrayList метод
 public int parallelIndexOf(E e), выполняющий линейный многопоточный поиск в списке.
 В тестах проверить поиск:
 - существующего элемента
 - не существующего элемента
 - не существующего элемента в пустом массиве
 - не существующего элемента в массиве с одним элементом
 - существующего элемента в массиве с одним элементом
 - элемента со значением null

 Класс задания:
 hw3.parallel.MyArrayList

 Класс теста:
 hw3.parallel.MyArrayListTest
 */
@Unit(testName = "P3ParallelListFindTest", value = {"hw4.parallel.MyArrayList", "hw4.parallel.MyArrayListTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P3ParallelListFindTest extends BaseTest {
    private static final String UNIT_NAME = "MyArrayList";
    private static final String TEST_NAME = "MyArrayListTest";
    private static final String PARALLEL_INDEX_OF_METHOD_NAME = "parallelIndexOf";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method addMethod;

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
        assertTrue("В задании должено быть не более 3х классов", unitClasses.length <= 3);
//        validateCodeFileThread(codes.entrySet().iterator().next().getValue());

        Class unitClass = getUnitClass(unitClasses, TEST_NAME);
        assertNotNull("В задании не найден класс " + TEST_NAME, unitClass);

        unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCodeFileThread(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

//        instance = instanciate(unitClass);
        addMethod = ReflectionUtil.checkMethod(unitClass, PARALLEL_INDEX_OF_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
    }
}
