package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.JavaProcessBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 Добавить функции работы с заказами такси. Заказы хранятся в базе данных.
 Функции:
 - оформить заказ (дата, клиент, сумма, адрес подачи, адрес назначения)
 - отредактировать заказ (поменять свойства заказа)
 - вывести список заказов на сумму в указанном диапазоне
 - вывести список всех заказов порциями по 5 штук


 hw9.taxi.domain.Order
 hw9.taxi.service.OrderService
 boolean createOrder(String id, String pass) throws OrderException
 void editOrder(Long id)
 void showOrders(Long from, Long to)
 void showOrdersByPortion()
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
@Unit(testName = "TaxiOrderCreation", value = "hw8.taxi.service.OrderService")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P9TaxiOrderCreation extends BaseTest {

    @UnitName
    private static String unitName;

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

    @Test/*(timeout = 1000)*/
    public void testSuccess() throws Throwable {
        JavaProcessBuilder.buildProcessAndInvokeMethod(unitName, null, "/forbid.policy", unitJarClasspath,
                null, (Object) new String[0]);
    }
}
