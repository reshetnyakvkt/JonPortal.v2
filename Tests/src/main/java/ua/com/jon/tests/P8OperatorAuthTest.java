package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
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
 Оператор вводит логин и пароль, они передаются методом POST на сервер.
 Если пароль с логином не совпадает то повторить ввод. Информация об операторах хранится в базе данных.
 Колличество попыток ввода задается в properties файле. После последней неудачной попытки пользователь блокируется.
 Время действия пароля задается в properties файле
 После истечения действия пароля, пользователь должен поменять пароль. Предыдущий вводить нельзя

 hw9.taxi.service.AuthenticationService
 boolean authenticate(String login, String pass) throws AuthenticationException
 hw9.taxi.service.AuthenticationServiceImpl
 hw9.taxi.domain.User
 hw9.taxi.dao.UserDao
 hw9.taxi.dao.UserDaoImp
 hw9.taxi.controller.AuthenticationServlet
 hw9.taxi.exception.AuthenticationException
 webapp
    index.jsp - страница с формой аутентификации
    dashboard.jsp - поздравления об удачной аутентификации

 Задание выполнить в модуле name_surname_spring
 */
@Unit(testName = "P7OperatorAuthTest", value = {
        "hw9.taxi.service.AuthenticationService",
        "hw9.taxi.service.AuthenticationServiceImpl",
        "hw9.taxi.domain.User",
        "hw9.taxi.dao.UserDao",
        "hw9.taxi.dao.UserDaoImp",
        "hw9.taxi.controller.AuthenticationServlet",
        "hw9.taxi.exception.AuthenticationException"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P8OperatorAuthTest extends BaseTest {
    private static final String SERVICE_NAME = "AuthenticationService";
    private static final String SERVICE_IMPL_NAME = "AuthenticationServiceImpl";
    private static final String AUTHENTICATE_METHOD_NAME = "authenticate";
    private static final String DAO_NAME = "UserDao";
    private static final String DAO_IMPL_NAME = "UserDaoImp";
    private static final String SERVLET_NAME = "AuthenticationServlet";
    private static final String EXCEPTION_NAME = "AuthenticationException";
    private static final String INDEX_NAME = "index.jsp";
    private static final String WELCOME_NAME = "dashboard.jsp";

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
                new MethodModifier[]{MethodModifier.PUBLIC}, "String", "String");

        Class serviceImpl = getUnitClass(unitClasses, SERVICE_IMPL_NAME);
        assertNotNull("В задании не найден класс " + SERVICE_IMPL_NAME, serviceImpl);
//        CodeValidator.checkCodePkg(codes.get(serviceImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(serviceImpl);
        ReflectionUtil.checkHasParent(serviceImpl, SERVICE_NAME);
    }

    @Test(timeout = 1000)
    public void testCheckDao() throws Throwable {
        Class service = getUnitClass(unitClasses, DAO_NAME);
        assertNotNull("В задании не найден класс " + DAO_NAME, service);
//        CodeValidator.checkCodePkg(codes.get(service.getName()));

        Class serviceImpl = getUnitClass(unitClasses, DAO_IMPL_NAME);
        assertNotNull("В задании не найден класс " + DAO_IMPL_NAME, serviceImpl);
//        CodeValidator.checkCodePkg(codes.get(serviceImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(serviceImpl);
        ReflectionUtil.checkHasParent(serviceImpl, DAO_NAME);
    }

    @Test(timeout = 1000)
    public void testCheckServlet() throws Throwable {

        Class servlet = getUnitClass(unitClasses, SERVLET_NAME);
        assertNotNull("В задании не найден класс " + SERVLET_NAME, servlet);
//        CodeValidator.checkCodePkg(codes.get(SERVLET_NAME));
        ReflectionUtil.checkDefaultConstructor(servlet);
        ReflectionUtil.checkHasParent(servlet, "HttpServlet");

        ReflectionUtil.checkMethod(servlet, "doPost", "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "HttpServletRequest", "HttpServletResponse");

        Class exception = getUnitClass(unitClasses, EXCEPTION_NAME);
        assertNotNull("В задании не найден класс " + EXCEPTION_NAME, exception);
    }

    @Test(timeout = 1000)
    public void testCheckWebContent() throws Throwable {

        URL index = getResource(files, INDEX_NAME);
        assertNotNull("В задании не найден файл " + INDEX_NAME, index);

        URL welcome = getResource(files, WELCOME_NAME);
        assertNotNull("В задании не найден файл " + WELCOME_NAME, welcome);
    }
}
