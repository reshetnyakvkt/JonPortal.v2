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
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 27.08.14
 */
@Unit(testName = "B2NumberTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P1NumberTestTest extends BaseTest {
    public class Number {
        public int get() {
            return 0;
        }
        public Number add(Number num) {
            return new Number();
        }
        public Number sub(Number num) {
            return new Number();
        }
        public Number mul(Number num) {
            return new Number();
        }
        public Number div(Number num) {
            return new Number();
        }
        public Number pow(Number num) {
            return new Number();
        }
        public Number mod(Number num) {
            return new Number();
        }
        public Number fact() {
            return new Number();
        }
    }

    private static final String UNIT_NAME = "Number";
    private static final String TEST_NAME = "NumberTest";
    private static final String GET_METHOD_NAME = "get";
    private static final String ADD_METHOD_NAME = "add";
    private static final String SUB_METHOD_NAME = "sub";
    private static final String MUL_METHOD_NAME = "mul";
    private static final String DIV_METHOD_NAME = "div";
    private static final String POW_METHOD_NAME = "pow";
    private static final String FACT_METHOD_NAME = "fact";
    private static final String MOD_METHOD_NAME = "mod";

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
        assertTrue("В задании должно быть не более 2х классов, а не " + unitClasses.length, unitClasses.length <= 2);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        CodeValidator.checkCode(unitClass.getName());
        StyleChecker.checkStyle(codes, troubles);

//        instance = instanciate(unitClasses[0]);
        ReflectionUtil.checkMethod(unitClass, GET_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, ADD_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
        ReflectionUtil.checkMethod(unitClass, SUB_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
        ReflectionUtil.checkMethod(unitClass, MUL_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
        ReflectionUtil.checkMethod(unitClass, DIV_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
        ReflectionUtil.checkMethod(unitClass, POW_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
        ReflectionUtil.checkMethod(unitClass, FACT_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, MOD_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
    }

    @Test(timeout = 1100)
    public void testTest() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, TEST_NAME);
        assertNotNull("В задании не найден класс теста" + TEST_NAME, unitClass);
    }
}
