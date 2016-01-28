package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.Assert.*;

/**
 Пользователь вводит своё имя с клавиатуры, поприветствовать (Hello ) пользователя по имени.
 Пример:
 Введите ваше имя:
 William Nelson Joy
 Hello William Nelson Joy

 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.09.13
 */
@Unit(testName = "GreetingTest", value = "session1.task3.Greeting")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GreetingTest extends BaseTest {
    public static void main(String[] args) {
        System.out.println("Hello ");
        java.util.Scanner scan = new java.util.Scanner(System.in);
        String name = scan.nextLine();
        System.out.println("Hello " + name);
    }

    private static final String UNIT_NAME = "Greeting";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method unitMethod;

    private String[] names = {"James Gosling", "Linus Torvalds", "Sergey Brin", "Doug Lee",
            "Martin Odersky", "Lawrence Ellison", "Guy Lewis Steele"};

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void testCheckMainMethod() throws Throwable {
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());
        instance = instanciate(unitClasses[0]);
        unitMethod = ReflectionUtil.checkMainMethod(unitClasses[0]);
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        String name = names[rnd.nextInt(names.length)];
        getOut().print(name);
/*        if(unitNames.length > 1) {
            fail("В задании должен быть только один класс");
        }*/
        ReflectionUtil.invokeMain(instance);
        String expectedString = "Hello " + name;
        String actualString = getLastStringFromOut();
        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("Ожидается другой вывод\nвместо [" + actualString + " должно быть [" + expectedString,
                expectedString.equals(actualString));

    }
}
