package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.Reader;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Написать собственную реализацию класса Scanner, класс MyScanner, работающую с символьными потоками.
 Создать два конструктора в один передается объект по ссылке Reader, во второй String.

 Реализовать методы:

 String next() - чтение одного слова в строке
 int nextInt() - чтение целого числа из строки или InputMismatchException
 String nextLine() - чтение всей строки
 boolean hasNext() - определение наличия в потоке данных
 boolean hasNextInt() - определение наличия в потоке целого int числа
 useDelimiter(String) - использует разделитель
 close() - закрытие сканнера, теперь нельзя использовать и NoSuchElementException
 */
@Unit(testName = "P0ScannerTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P0ScannerTest extends BaseTest {
    // import java.io.Reader;
    public class MyScanner {
        public MyScanner() {

        }
        public MyScanner(Reader reader) {

        }
        public MyScanner(String string) {

        }
        public int get() {
            return 0;
        }
        public String next() {
            return "";
        }
        public int nextInt() {
            return 1;
        }
        public String nextLine() {
            return "";
        }
        public boolean hasNext() {
            return false;
        }
        public boolean hasNextInt() {
            return false;
        }
        public void useDelimiter(String delim) {}
        public void close() {}
    }

    private static final String UNIT_NAME = "MyScanner";
    private static final String NEXT_METHOD_NAME = "next";
    private static final String NEXT_INT_METHOD_NAME = "nextInt";
    private static final String NEXT_LINE_METHOD_NAME = "nextLine";
    private static final String HAS_NEXT_METHOD_NAME = "hasNext";
    private static final String HAS_NEXT_INT_METHOD_NAME = "hasNextInt";
    private static final String USE_DELIMITER_METHOD_NAME = "useDelimiter";
    private static final String CLOSE_METHOD_NAME = "close";

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
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        CodeValidator.checkCodeFile(codes.entrySet().iterator().next().getValue());

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        ReflectionUtil.checkConstructor(unitClass, Reader.class);
        ReflectionUtil.checkConstructor(unitClass, String.class);

//        instance = instanciate(unitClass);
        ReflectionUtil.checkMethod(unitClass, NEXT_METHOD_NAME, String.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, NEXT_INT_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, NEXT_LINE_METHOD_NAME, String.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, HAS_NEXT_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, HAS_NEXT_INT_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, USE_DELIMITER_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        ReflectionUtil.checkMethod(unitClass, CLOSE_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
