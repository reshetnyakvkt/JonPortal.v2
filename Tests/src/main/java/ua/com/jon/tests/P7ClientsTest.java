package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.net.URL;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

/**
 Учёт клиентов службы такси.

 Функции:
 - зарегистрировать клиента (имя, фамилия, телефон, адрес, сумма, дата последнего заказа)
 - вывести всех клиентов порциями по 10 человек
 - вывести всех клиентов наездивших на сумму больше указанной
 - вывести всех клиентов, делавших заказы за последний месяц

 hw8.taxi.domain.Client
 hw8.taxi.service.ClientService
    boolean createClient(String name, String surname, String phone, String address) throws OrderException
    List showClientsByPortion(int portionSize)
    List showClientsGtSum(int sum)
    List showClientsLastMonth()
 hw8.taxi.service.ClientServiceImpl
 hw8.taxi.action.ClientServlet
 hw8.taxi.exception.ClientException
 webapp
    index.jsp
    dashboard.jsp - страница со списком функций (доступна после аутентификации)
    registerClient.jsp - форма создания клиента
    clients.jsp - список клиентов

 Задание выполнить в модуле name_surname_web
 */
@Unit(testName = "P7ClientsTest", value = {
        "hw8.taxi.domain.Client",
        "hw8.taxi.service.ClientService",
        "hw8.taxi.service.ClientServiceImpl",
        "hw8.taxi.exception.ClientException",
        "hw8.taxi.action.ClientServlet"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P7ClientsTest extends BaseTest {
    private static final String DOMAIN_NAME = "Client";
    private static final String SERVICE_NAME = "ClientService";
    private static final String SERVICE_IMPL_NAME = "ClientServiceImpl";
    private static final String CREATE_CLIENT_METHOD_NAME = "createClient";
    private static final String SHOW_PORTION_METHOD_NAME = "showClientsByPortion";
    private static final String SHOW_SUM_METHOD_NAME = "showClientsGtSum";
    private static final String SHOW_MONTH_METHOD_NAME = "showClientsLastMonth";
    private static final String SERVLET_NAME = "ClientServlet";
    private static final String EXCEPTION_NAME = "ClientException";
    private static final String INDEX_NAME = "index.jsp";
    private static final String REGISTER_NAME = "registerClient.jsp";
    private static final String WELCOME_NAME = "dashboard.jsp";
    private static final String CLIENTS_NAME = "clients.jsp";

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

    @Test(timeout = 1000)
    public void testCheckService() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, DOMAIN_NAME);
        assertNotNull("В задании не найден класс " + DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        Class service = getUnitClass(unitClasses, SERVICE_NAME);
        assertNotNull("В задании не найден класс ", service);
//        CodeValidator.checkCodePkg(codes.get(service.getName()));

        ReflectionUtil.checkMethod(service, CREATE_CLIENT_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "String", "String", "String", "String");
        ReflectionUtil.checkMethod(service, SHOW_PORTION_METHOD_NAME, "List",
                new MethodModifier[]{MethodModifier.PUBLIC}, "int");
        ReflectionUtil.checkMethod(service, SHOW_SUM_METHOD_NAME, "List",
                new MethodModifier[]{MethodModifier.PUBLIC}, "int");
        ReflectionUtil.checkMethod(service, SHOW_MONTH_METHOD_NAME, "List",
                new MethodModifier[]{MethodModifier.PUBLIC});

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

        URL welcome = getResource(files, WELCOME_NAME);
        assertNotNull("В задании не найден файл " + WELCOME_NAME, welcome);

        URL register = getResource(files, REGISTER_NAME);
        assertNotNull("В задании не найден файл " + REGISTER_NAME, register);

        URL clients = getResource(files, CLIENTS_NAME);
        assertNotNull("В задании не найден файл " + CLIENTS_NAME, clients);
    }
}
