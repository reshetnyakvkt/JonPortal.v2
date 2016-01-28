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

import java.net.URL;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

/**
 Учёт клиентов службы такси. Данные о клиентах хранятся в базе данных.

 Функции:
 - зарегистрировать клиента (имя, фамилия, телефон, адрес, сумма, дата последнего заказа)
 - вывести всех клиентов порциями по 10 человек
 - вывести всех клиентов наездивших на сумму больше указанной
 - вывести всех клиентов, делавших заказы за последний месяц

 hw9.taxi.domain.Client
 hw9.taxi.service.ClientService
 boolean createClient(String name, String surname, String phone, String address) throws OrderException
 void showClientsByPortion(int portionSize)
 void showClientsGtSum(int sum)
 void showClientsLastMonth()
 hw9.taxi.service.ClientServiceImpl
 hw9.taxi.dao.ClientDao
 hw9.taxi.dao.ClientDaoImpl
 hw9.taxi.controller.ClientCreateServlet
 hw9.taxi.controller.ClientShowPortionServlet
 hw9.taxi.controller.ClientShowSumServlet
 hw9.taxi.controller.ClientShowMonthServlet
 hw9.taxi.exception.ClientException
 webapp
 index.jsp
 dashboard.jsp - страница со списком функций (доступна после аутентификации)
 registerClient.jsp - форма создания клиента
 clients.jsp - список клиентов

 Задание выполнить в модуле name_surname_spring
 */
@Unit(testName = "P9TaxiClientRegistration", value = {
        "hw9.taxi.domain.Client",
        "hw9.taxi.service.ClientService",
        "hw9.taxi.service.ClientServiceImpl",
        "hw9.taxi.dao.ClientDao",
        "hw9.taxi.dao.ClientDaoImpl",
        "hw9.taxi.exception.ClientException",
        "hw9.taxi.controller.CreateClientServlet",
        "hw9.taxi.controller.PortionClientServlet",
        "hw9.taxi.controller.SumClientServlet",
        "hw9.taxi.controller.MonthClientServlet"
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P9TaxiClientRegistration extends BaseTest {
    private static final String DOMAIN_NAME = "Client";
    private static final String SERVICE_NAME = "ClientService";
    private static final String SERVICE_IMPL_NAME = "ClientServiceImpl";
    private static final String DAO_NAME = "ClientDao";
    private static final String DAO_IMPL_NAME = "ClientDaoImpl";
    private static final String CREATE_CLIENT_METHOD_NAME = "createClient";
    private static final String SHOW_PORTION_METHOD_NAME = "getClientsByPortion";
    private static final String SHOW_SUM_METHOD_NAME = "getClientsGtSum";
    private static final String SHOW_MONTH_METHOD_NAME = "getClientsLastMonth";
    private static final String CREATE_SERVLET_NAME = "CreateClientServlet";
    private static final String PORTION_SERVLET_NAME = "PortionClientServlet";
    private static final String SUM_SERVLET_NAME = "SumClientServlet";
    private static final String MONTH_SERVLET_NAME = "MonthClientServlet";
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

        Class servlet = getUnitClass(unitClasses, CREATE_SERVLET_NAME);
        assertNotNull("В задании не найден класс " + CREATE_SERVLET_NAME, servlet);
//        CodeValidator.checkCodePkg(codes.get(CREATE_SERVLET_NAME));
        ReflectionUtil.checkDefaultConstructor(servlet);
        ReflectionUtil.checkHasParent(servlet, "HttpServlet");

        ReflectionUtil.checkMethod(servlet, "doGet", "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "HttpServletRequest", "HttpServletResponse");

        servlet = getUnitClass(unitClasses, SUM_SERVLET_NAME);
        assertNotNull("В задании не найден класс " + SUM_SERVLET_NAME, servlet);
//        CodeValidator.checkCodePkg(codes.get(SUM_SERVLET_NAME));
        ReflectionUtil.checkDefaultConstructor(servlet);
        ReflectionUtil.checkHasParent(servlet, "HttpServlet");

        ReflectionUtil.checkMethod(servlet, "doGet", "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "HttpServletRequest", "HttpServletResponse");

        servlet = getUnitClass(unitClasses, PORTION_SERVLET_NAME);
        assertNotNull("В задании не найден класс " + PORTION_SERVLET_NAME, servlet);
//        CodeValidator.checkCodePkg(codes.get(PORTION_SERVLET_NAME));
        ReflectionUtil.checkDefaultConstructor(servlet);
        ReflectionUtil.checkHasParent(servlet, "HttpServlet");

        ReflectionUtil.checkMethod(servlet, "doGet", "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "HttpServletRequest", "HttpServletResponse");

        servlet = getUnitClass(unitClasses, MONTH_SERVLET_NAME);
        assertNotNull("В задании не найден класс " + MONTH_SERVLET_NAME, servlet);
//        CodeValidator.checkCodePkg(codes.get(MONTH_SERVLET_NAME));
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
