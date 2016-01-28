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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 Написать класс число (Number), выполняющий следующие операции:
 - получение примитива (метод int get())
 - сложение (метод Number add(Number num))
 - вычитание (метод Number sub(Number num))
 - умножение (метод Number mul(Number num))
 - деление (метод Number div(Number num))
 - возведение в степень (метод Number pow(Number exponent))
 - вычисление факториала (метод Number fact())
 - вычисление остатка от деления (метод Number mod(Number num))
 */
@Unit(testName = "B2NumberTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B2NumberTest extends BaseTest {
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
    public void testCheckMainMethod() throws Throwable {
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

//        instance = instanciate(unitClasses[0]);
        addMethod = ReflectionUtil.checkMethod(unitClass, GET_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, ADD_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
        addMethod = ReflectionUtil.checkMethod(unitClass, SUB_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
        addMethod = ReflectionUtil.checkMethod(unitClass, MUL_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
        addMethod = ReflectionUtil.checkMethod(unitClass, DIV_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
        addMethod = ReflectionUtil.checkMethod(unitClass, POW_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
        addMethod = ReflectionUtil.checkMethod(unitClass, FACT_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, MOD_METHOD_NAME, "Number",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Number");
    }
}
