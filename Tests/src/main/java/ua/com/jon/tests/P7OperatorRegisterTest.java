package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

/**
 Реализовать авторизацию пользователя системы (оператора).
 Пользователь вводит:
 - логин (должен быть не менее 4 символов, не должен содержать пробелы)
 - идентификационный номер (10 цифр, без букв и других знаков)
 - пароль (должен быть не менее 8 символов,
 включать большие и маленькие буквы, цифры, должен совпадать с подтверждением)
 - подтверждение пароля
 - пользователь с таким логином должен быть уникальным

 hw8.taxi.service.AuthorizationService
    boolean register(String login, String id, String pass) throws AuthenticationException
 hw8.taxi.service.AuthorizationServiceImpl
 hw8.taxi.action.RegisterServlet
 hw8.taxi.exception.AuthorizationException
 webapp
    index.jsp
    register.jsp - форма регистрации

 Задание выполнить в модуле name_surname_web
 */
@Unit(testName = "P7OperatorRegisterTest", value = {
        "hw8.taxi.service.AuthorizationService",
        "hw8.taxi.service.AuthorizationServiceImpl",
        "hw8.taxi.action.RegisterServlet",
        "hw8.taxi.exception.AuthorizationException"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P7OperatorRegisterTest extends BaseTest {
    private static final String SERVICE_NAME = "AuthorizationService";
    private static final String SERVICE_IMPL_NAME = "AuthorizationServiceImpl";
    private static final String AUTHENTICATE_METHOD_NAME = "register";
    private static final String SERVLET_NAME = "RegisterServlet";
    private static final String EXCEPTION_NAME = "AuthorizationException";
    private static final String INDEX_NAME = "index.jsp";
    private static final String REGISTER_NAME = "register.jsp";

    @UnitCode
    private static Map<String, String> codes;
    @UnitFiles
    private static List<URL> files;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    private static Object instance;
    private static Method method;

    @Test(timeout = 1000)
    public void testCheckService() throws Throwable {
        Class service = getUnitClass(unitClasses, SERVICE_NAME);
        assertNotNull("В задании не найден класс ", service);
//        CodeValidator.checkCodePkg(codes.get(service.getName()));

        ReflectionUtil.checkMethod(service, AUTHENTICATE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "String", "String", "String", "String");

        Class serviceImpl = getUnitClass(unitClasses, SERVICE_IMPL_NAME);
        assertNotNull("В задании не найден класс " + SERVICE_IMPL_NAME, serviceImpl);
//        CodeValidator.checkCodePkg(codes.get(serviceImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(serviceImpl);
        ReflectionUtil.checkHasParent(serviceImpl, SERVICE_NAME);
    }

    @Test(timeout = 1000)
    public void testCheckServlet() throws Throwable {

        Class servlet = getUnitClass(unitClasses, SERVLET_NAME);
        assertNotNull("В задании не найден класс " + SERVLET_NAME, servlet);
//        CodeValidator.checkCodePkg(codes.get(SERVLET_NAME));
        ReflectionUtil.checkDefaultConstructor(servlet);
        ReflectionUtil.checkHasParent(servlet, "HttpServlet");

        ReflectionUtil.checkMethod(servlet, "doGet", "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "HttpServletRequest", "HttpServletResponse");

        Class exception = getUnitClass(unitClasses, EXCEPTION_NAME);
        assertNotNull("В задании не найден класс " + EXCEPTION_NAME, exception);
    }

    @Test(timeout = 1000)
    public void testCheckWebContent() throws Throwable {

        URL url = getResource(files, INDEX_NAME);
        assertNotNull("В задании не найден файл " + INDEX_NAME, url);

        URL register = getResource(files, REGISTER_NAME);
        assertNotNull("В задании не найден файл " + REGISTER_NAME, register);
    }
}
