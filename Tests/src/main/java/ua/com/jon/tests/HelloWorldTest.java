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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/8/13
 */
@Unit(testName = "HelloWorldTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelloWorldTest extends BaseTest {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }

    private static final String UNIT_NAME = "HelloWorld";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;
    private static Class unitClass;
    private static Object instance;
    private static Method unitMethod;

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
    public void testClassMainMessage() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        try {
//            getOut().println("\n");
            ReflectionUtil.invokeMain(instance);
            assertTrue("Метод main должен выводить в консоль сообщение \'Hello world\'", "Hello world".equals(getIn().toString().trim()));
        } catch (Exception e) {
        }
        assertTrue("Метод main должен выводить в консоль сообщение \'Hello world\'", "Hello world".equals(getIn().toString().trim()));

    }
}
