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

import java.net.URL;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

/**
 Добавить функции работы с заказами такси. Заказы хранятся в базе данных.
 Функции:
 - оформить заказ (дата, клиент, сумма, адрес подачи, адрес назначения)
 - отредактировать заказ (поменять свойства заказа)
 - вывести список заказов на сумму в указанном диапазоне
 - вывести список всех заказов порциями по 5 штук

 hw8.taxi.domain.Order
 hw8.taxi.domain.Client
 hw8.taxi.service.OrderService
 boolean createOrder(Long id, Client client, String amount, String addressFrom, String addressTo) throws OrderException
 void editOrder(Long id, Client client, String amount, String addressFrom, String addressTo)
 List showOrders(Long from, Long to)
 List showOrdersByPortion()
 hw9.taxi.service.OrderServiceImpl
 hw9.taxi.dao.OrderDao
 hw9.taxi.dao.OrderDaoImpl
 hw9.taxi.controller.OrderCreateServlet
 hw9.taxi.controller.OrderEditServlet
 hw9.taxi.controller.OrderShowServlet
 hw9.taxi.controller.OrderShowPortionServlet
 hw9.taxi.exception.OrderException
 webapp
 index.jsp
 dashboard.jsp - страница со списком функций
 order.jsp - форма оформления/редактирования заказа
 orders.jsp - список заказов

 Задание выполнить в модуле name_surname_spring
 */
@Unit(testName = "P8OrdersTest", value = {
        "hw9.taxi.domain.Order",
        "hw9.taxi.domain.Client",
        "hw9.taxi.service.OrderService",
        "hw9.taxi.service.OrderServiceImpl",
        "hw9.taxi.dao.OrderDao",
        "hw9.taxi.dao.OrderDaoImpl",
        "hw9.taxi.controller.OrderCreateServlet",
        "hw9.taxi.controller.OrderEditServlet",
        "hw9.taxi.controller.OrderShowServlet",
        "hw9.taxi.controller.OrderShowPortionServlet",
        "hw9.taxi.exception.OrderException"
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P8OrdersTest extends BaseTest {
    private static final String DOMAIN_NAME = "Order";
    private static final String SERVICE_NAME = "OrderService";
    private static final String SERVICE_IMPL_NAME = "OrderServiceImpl";
    private static final String DAO_NAME = "OrderDao";
    private static final String DAO_IMPL_NAME = "OrderDaoImpl";
    private static final String CREATE_ORDER_METHOD_NAME = "createOrder";
    private static final String EDIT_ORDER_METHOD_NAME = "editOrder";
    private static final String SHOW_ORDERS_METHOD_NAME = "showOrders";
    private static final String SHOW_ORDERS_PORTION_METHOD_NAME = "sowOrdersByPortion";
    private static final String CREATE_SERVLET_NAME = "OrderCreateServlet";
    private static final String EDIT_SERVLET_NAME = "OrderEditServlet";
    private static final String SHOW_SERVLET_NAME = "OrderShowServlet";
    private static final String PORTION_SERVLET_NAME = "OrderShowPortionServlet";
    private static final String EXCEPTION_NAME = "OrderException";
    private static final String INDEX_NAME = "index.jsp";
    private static final String REGISTER_NAME = "order.jsp";
    private static final String WELCOME_NAME = "dashboard.jsp";
    private static final String ORDERS_NAME = "orders.jsp";

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

        ReflectionUtil.checkMethod(service, CREATE_ORDER_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Client", "Double", "String", "String");
        ReflectionUtil.checkMethod(service, EDIT_ORDER_METHOD_NAME, "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Long", "Client", "String", "String", "String");
        ReflectionUtil.checkMethod(service, SHOW_ORDERS_METHOD_NAME, "List",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Long", "Long");
        ReflectionUtil.checkMethod(service, SHOW_ORDERS_PORTION_METHOD_NAME, "List",
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
        CodeValidator.checkCodePkg(codes.get(CREATE_SERVLET_NAME));
        ReflectionUtil.checkDefaultConstructor(servlet);
        ReflectionUtil.checkHasParent(servlet, "HttpServlet");

        ReflectionUtil.checkMethod(servlet, "doGet", "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "HttpServletRequest", "HttpServletResponse");

        servlet = getUnitClass(unitClasses, EDIT_SERVLET_NAME);
        assertNotNull("В задании не найден класс " + EDIT_SERVLET_NAME, servlet);
        CodeValidator.checkCodePkg(codes.get(EDIT_SERVLET_NAME));
        ReflectionUtil.checkDefaultConstructor(servlet);
        ReflectionUtil.checkHasParent(servlet, "HttpServlet");

        ReflectionUtil.checkMethod(servlet, "doGet", "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "HttpServletRequest", "HttpServletResponse");

        servlet = getUnitClass(unitClasses, PORTION_SERVLET_NAME);
        assertNotNull("В задании не найден класс " + PORTION_SERVLET_NAME, servlet);
        CodeValidator.checkCodePkg(codes.get(PORTION_SERVLET_NAME));
        ReflectionUtil.checkDefaultConstructor(servlet);
        ReflectionUtil.checkHasParent(servlet, "HttpServlet");

        ReflectionUtil.checkMethod(servlet, "doGet", "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "HttpServletRequest", "HttpServletResponse");

        servlet = getUnitClass(unitClasses, SHOW_SERVLET_NAME);
        assertNotNull("В задании не найден класс " + SHOW_SERVLET_NAME, servlet);
        CodeValidator.checkCodePkg(codes.get(SHOW_SERVLET_NAME));
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

        URL orders = getResource(files, ORDERS_NAME);
        assertNotNull("В задании не найден файл " + ORDERS_NAME, orders);
    }
}
