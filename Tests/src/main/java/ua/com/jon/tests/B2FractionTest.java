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
 Написать класс дробь (hw2.Fraction), выполняющий следующие действия:
 - сложение дробей (метод Fraction add(Fraction frac))
 - вычитание (метод Fraction sub(Fraction frac))
 - умножение (метод Fraction mul(Fraction frac))
 - деление (метод Fraction div(Fraction frac))
 - приведение к строке(метод String toString())
 - вывод (метод void print())
 */
@Unit(testName = "B2FractionTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B2FractionTest extends BaseTest {
    public class Fraction {
        public int get() {
            return 0;
        }
        public Fraction add(Fraction num) {
            return new Fraction();
        }
        public Fraction sub(Fraction num) {
            return new Fraction();
        }
        public Fraction mul(Fraction num) {
            return new Fraction();
        }
        public Fraction div(Fraction num) {
            return new Fraction();
        }
        public String toString() {
            return "";
        }
        public void print() {
        }
    }

    private static final String UNIT_NAME = "Fraction";
    private static final String ADD_METHOD_NAME = "add";
    private static final String SUB_METHOD_NAME = "sub";
    private static final String MUL_METHOD_NAME = "mul";
    private static final String DIV_METHOD_NAME = "div";
    private static final String TO_STRING_METHOD_NAME = "toString";
    private static final String PRINT_METHOD_NAME = "print";

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

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCode(unitClass.getName());
        StyleChecker.checkStyle(codes, troubles);
//        instance = instanciate(unitClasses[0]);
        addMethod = ReflectionUtil.checkMethod(unitClasses[0], ADD_METHOD_NAME, "Fraction",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Fraction");
        addMethod = ReflectionUtil.checkMethod(unitClasses[0], SUB_METHOD_NAME, "Fraction",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Fraction");
        addMethod = ReflectionUtil.checkMethod(unitClasses[0], MUL_METHOD_NAME, "Fraction",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Fraction");
        addMethod = ReflectionUtil.checkMethod(unitClasses[0], DIV_METHOD_NAME, "Fraction",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Fraction");
        addMethod = ReflectionUtil.checkMethod(unitClasses[0], TO_STRING_METHOD_NAME, String.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClasses[0], PRINT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
