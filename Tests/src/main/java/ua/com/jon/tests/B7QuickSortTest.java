package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import hw8.sort.Student;
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
 Написать метод быстрой сортировки для массива студентов по оценкам, от отличников к двоечникам.
 void sort(Student[] students)

 Студент (имя, фамилия, отчество, оценки по викендам).
 Оценки по викендам представляют собой массив целых чисел.

 Классы задания: hw8.sort.QuickSorter, hw8.sort.Student
 */
@Unit(testName = "B7QuickSortTest", value = "hw8.sort.QuickSorter, hw8.sort.Student")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B7QuickSortTest extends BaseTest {
    private static final String UNIT_NAME = "QuickSorter";
    private static final String SORT_METHOD_NAME = "sort";

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
//        assertTrue("В задании должно быть не более 2х классов", unitClasses.length <= 2);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCode(codes.get(unitClass.getName()));
        StyleChecker.checkStyle(codes, troubles);
        ReflectionUtil.checkDefaultConstructor(unitClass);

        ReflectionUtil.checkMethod(unitClass, SORT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Student[].class);
    }
}
