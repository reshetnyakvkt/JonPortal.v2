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
 Добавить функции работы с заказами такси.
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
 hw8.taxi.service.OrderServiceImpl
 hw8.taxi.action.OrderServlet
 hw8.taxi.exception.OrderException
 webapp
    index.jsp
    dashboard.jsp - страница со списком функций
    order.jsp - форма оформления/редактирования заказа
    orders.jsp - список заказов

 Задание выполнить в модуле name_surname_web
 */
@Unit(testName = "P7OrdersTest", value = {
        "hw8.taxi.domain.Order",
        "hw8.taxi.domain.Client",
        "hw8.taxi.service.OrderService",
        "hw8.taxi.service.OrderServiceImpl",
        "hw8.taxi.exception.OrderException",
        "hw8.taxi.action.OrderServlet"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P7OrdersTest extends BaseTest {
    private static final String DOMAIN_NAME = "Order";
    private static final String SERVICE_NAME = "OrderService";
    private static final String SERVICE_IMPL_NAME = "OrderServiceImpl";
    private static final String CREATE_ORDER_METHOD_NAME = "createOrder";
    private static final String EDIT_ORDER_METHOD_NAME = "editOrder";
    private static final String SHOW_ORDERS_METHOD_NAME = "showOrders";
    private static final String SHOW_ORDERS_PORTION_METHOD_NAME = "showOrdersByPortion";
    private static final String SERVLET_NAME = "OrderServlet";
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
                new MethodModifier[]{MethodModifier.PUBLIC}, "Long", "Client", "String", "String", "String");
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

        URL orders = getResource(files, ORDERS_NAME);
        assertNotNull("В задании не найден файл " + ORDERS_NAME, orders);
    }
}
