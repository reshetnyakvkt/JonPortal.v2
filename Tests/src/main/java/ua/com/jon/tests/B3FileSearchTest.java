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
 Пользователь водит имя файла, заполненого строками и вводит с клавиатуры слово.
 Написать метод, выполняющий поиск слова в файле и выводящий на экран индекс первого символа найденной строки, либо -1
 */
@Unit(testName = "B3FileSearchTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B3FileSearchTest extends BaseTest {
    private static final String METHOD_NAME = "find";
    private static final String CAT_NAME = "FileFinder";

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

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должно быть не более 1 класса, а не " + unitClasses.length, unitClasses.length < 2);

        Class unitClass = getUnitClass(unitClasses, CAT_NAME);
        assertNotNull("В задании не найден класс " + CAT_NAME, unitClass);
        CodeValidator.checkCode(unitClass.getName());
        StyleChecker.checkStyle(codes, troubles);
        ReflectionUtil.checkMethod(unitClass, METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
    }
}