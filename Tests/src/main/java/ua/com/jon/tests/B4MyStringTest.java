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
 Написать класс строка (MyString) - последовательность символов. Реализовать следующие методы.
 - создание на основе массива символов (конструктор)
 - вывод на экран, метод void print()
 - конкатенация, метод void concat(MyString str)
 - понижение регистра, метод void toLower()
 - повышение регистра, метод void toUpper()
 - поиск подстроки, метод int indexOf(MyString str)
 - выделение подстроки, метод MyString substring(int first, int last)
 - удаление пробелов, метод void trim()
 */
@Unit(testName = "B4MyStringTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B4MyStringTest extends BaseTest {
    public class MyString {
        public MyString() {}
        public MyString(char[] chars) {}
        public void print() {}
        public MyString concat(MyString str) {
            return new MyString();
        }
        public void toLower() {}
        public void toUpper() {}
        public int indexOf(MyString str) {return 0;}
        public MyString substring(int first, int last) {return new MyString();}
    }

    private static final String UNIT_NAME = "MyString";
    private static final String PRINT_METHOD_NAME = "print";
    private static final String CONCAT_METHOD_NAME = "concat";
    private static final String LOWER_METHOD_NAME = "toLower";
    private static final String UPPER_METHOD_NAME = "toUpper";
    private static final String INDEX_OF_METHOD_NAME = "indexOf";
    private static final String SUBSTRING_METHOD_NAME = "substring";
    private static final String TRIM_METHOD_NAME = "trim";

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
//        ReflectionUtil.checkDefaultConstructor(unitClass, "");

//        instance = instanciate(unitClasses[0]);
        addMethod = ReflectionUtil.checkMethod(unitClass, CONCAT_METHOD_NAME, "MyString",
                new MethodModifier[]{MethodModifier.PUBLIC}, "MyString");
        addMethod = ReflectionUtil.checkMethod(unitClass, LOWER_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, UPPER_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, PRINT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, INDEX_OF_METHOD_NAME, "int",
                new MethodModifier[]{MethodModifier.PUBLIC}, "MyString");
        addMethod = ReflectionUtil.checkMethod(unitClass, SUBSTRING_METHOD_NAME, "MyString",
                new MethodModifier[]{MethodModifier.PUBLIC}, "int", "int");
        addMethod = ReflectionUtil.checkMethod(unitClass, TRIM_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
