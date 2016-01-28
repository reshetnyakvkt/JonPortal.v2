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
 Написать класс телефон (hw2.Phone), выполняющий следующие действия:
 - позвонить на указанный телефон (метод void call (String number, int mins))
 - отправить смс на указанный телефон (метод boolean sendSms (String number, String text))
 - пополнить счет на указанную сумму (метод boolean charge (int copeiki))
 - проверять существование номера телефона (метод boolean isNumberExists (String number))
 За 1 минуту звонка снимается 10 копеек, за 1 смс 5 копеек. Список абонентов сети и баланс счёта храниться в телефоне.
 */
@Unit(testName = "B2PhoneTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B2PhoneTest extends BaseTest {
    public class Phone {
        public void call (String number, int mins) {
        }
        public boolean sendSms (String number, String text) {
            return false;
        }
        public boolean charge (int copeiki) {
            return false;
        }
        public boolean isNumberExists (String number) {
            return false;
        }
    }

    private static final String UNIT_NAME = "Phone";
    private static final String CALL_METHOD_NAME = "call";
    private static final String SEND_SMS_METHOD_NAME = "sendSms";
    private static final String CHARGE_METHOD_NAME = "charge";
    private static final String IS_NUMBER_EXISTS_METHOD_NAME = "isNumberExists";

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

        //instance = instanciate(unitClass);
        addMethod = ReflectionUtil.checkMethod(unitClass, CALL_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, int.class);
        addMethod = ReflectionUtil.checkMethod(unitClass, SEND_SMS_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, String.class);
        addMethod = ReflectionUtil.checkMethod(unitClass, CHARGE_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        addMethod = ReflectionUtil.checkMethod(unitClass, IS_NUMBER_EXISTS_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
    }
}
