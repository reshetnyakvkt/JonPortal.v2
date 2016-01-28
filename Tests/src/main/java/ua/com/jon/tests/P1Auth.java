package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.JavaProcessBuilder;
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
 * Date: 11.05.14
 */
@Unit(testName = "P1Auth", value = "hw2.authentication.Auth")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P1Auth extends BaseTest {
    private static final String UNIT_NAME = "Auth";
    private static final String TEST_NAME = "AuthTest";
    private static final String AUTH_METHOD_NAME = "authenticate";

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

    @Test(timeout = 1000)
    public void testCheckUnitPresent() throws Throwable {
        assertTrue("В задании должно быть не более 2х классов, а не " + unitClasses.length, unitClasses.length <= 3);
        CodeValidator.checkCodeFile(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

        Method methodAuthenticate = ReflectionUtil.checkMethod(unitClass, AUTH_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, String.class);
    }

    @Test(timeout = 1000)
    public void testCheckTestPresent() throws Throwable {
        Class unitClass;
        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, TEST_NAME);
            assertNotNull("В задании не найден класс теста " + TEST_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
        assertTrue("В задании не найден класс теста " + TEST_NAME, TEST_NAME.equals(unitClass.getSimpleName()));
    }
}
