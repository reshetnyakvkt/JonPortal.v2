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
 Написать чат, в котором можно отправлять и принимать сообщения в любом порядке.
 public void process()

 Класс задания hw3.chat.AsyncChat
 Класс теста hw3.chat.AsyncChatTest
 */
@Unit(testName = "P2AsyncChatTest", value = {"hw3.chat.AsyncChat", "hw3.chat.AsyncChatTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P2AsyncChatTest extends BaseTest {
    private static final String UNIT_NAME = "AsyncChat";
    private static final String TEST_NAME = "AsyncChatTest";
    private static final String PROCESS_METHOD_NAME = "process";

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
        assertTrue("В задании должено быть не более 4х классов", unitClasses.length <= 4);
//        validateCodeFileThreadNet(codes.entrySet().iterator().next().getValue());

        Class unitClass = getUnitClass(unitClasses, TEST_NAME);
        assertNotNull("В задании не найден класс " + TEST_NAME, unitClass);

        unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCodeFileThreadNet(codes.get(unitClass.getName()));
        StyleChecker.checkStyle(codes, troubles);
        ReflectionUtil.checkDefaultConstructor(unitClass);

//        instance = instanciate(unitClass);
        ReflectionUtil.checkMethod(unitClass, PROCESS_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
