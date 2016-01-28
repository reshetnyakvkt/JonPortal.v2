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
 Написать класс массив (Array). Реализовать методы:
 - нахождение минимального, метод int min()
 - нахождение максимального, метод int max()
 - заполнение случайными числами в диапазоне, метод void fillRandom(int left, int right)
 - красивый вывод на экран {2 4 5 6}, метод void print()
 -* частичное упорядочивание, метод void sortPartly(int index)
 -* увеличить размер на указанное кол-во элементов, метод void resize(int newSize) */
@Unit(testName = "B4ArrayTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B4ArrayTest extends BaseTest {
    public class Array {
        public int min() {
            return 0;
        }
        public int max() {
            return 0;
        }
        public void fillRandom(int left, int right) {
        }
        public void print() {
        }
        public void sortPartly(int index) {
        }
        public void resize(int newSize) {
        }
    }

    private static final String UNIT_NAME = "Array";
    private static final String MIN_METHOD_NAME = "min";
    private static final String MAX_METHOD_NAME = "max";
    private static final String FILL_METHOD_NAME = "fillRandom";
    private static final String PRINT_METHOD_NAME = "print";
    private static final String SORT_METHOD_NAME = "sortPartly";
    private static final String RESIZE_METHOD_NAME = "resize";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method getMethod;
    private static Method addMethod;
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
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        ReflectionUtil.checkDefaultConstructor(unitClass);

//        instance = instanciate(unitClasses[0]);
        addMethod = ReflectionUtil.checkMethod(unitClass, MIN_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, MAX_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, FILL_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class,  int.class);
        addMethod = ReflectionUtil.checkMethod(unitClass, PRINT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, SORT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        addMethod = ReflectionUtil.checkMethod(unitClass, RESIZE_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
    }
}
