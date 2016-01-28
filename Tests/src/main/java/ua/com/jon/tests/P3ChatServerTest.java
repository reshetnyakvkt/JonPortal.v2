package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 26.05.14
 */
@Unit(testName = "P3ChatServerTest", value = {"hw4.parallel.ChatServer", "hw4.parallel.ChatServerTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P3ChatServerTest extends BaseTest {
    private static final String UNIT_NAME = "ChatServer";
    private static final String TEST_NAME = "ChatServerTest";
    private static final String LISTEN_METHOD_NAME = "listenConnections";

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

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1100)
    public void testCheckUnitPresent() throws Throwable {
//        assertTrue("В задании должено быть не более 3х классов", unitClasses.length <= 3);
        Class unitClass = getUnitClass(unitClasses, TEST_NAME);
        assertNotNull("В задании не найден класс " + TEST_NAME, unitClass);


        unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCodeFileThreadNet(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

//        instance = instanciate(unitClass);
        method = ReflectionUtil.checkMethod(unitClass, LISTEN_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
    }

}
