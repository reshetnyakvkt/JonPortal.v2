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
 Реализовать авторизацию пользователя системы (оператора). Данные об операторах хранятся в базе данных.
 Пользователь вводит:
 - логин (должен быть не менее 4 символов, не должен содержать пробелы)
 - идентификационный номер (10 цифр, без букв и других знаков)
 - пароль (должен быть не менее 8 символов,
 включать большие и маленькие буквы, цифры, должен совпадать с подтверждением)
 - подтверждение пароля
 - пользователь с таким логином должен быть уникальным

 hw9.taxi.service.AuthorizationService
 boolean register(String login, String id, String pass) throws AuthenticationException
 hw9.taxi.service.AuthorizationServiceImpl
 hw9.taxi.domain.User
 hw9.taxi.dao.UserDao
 hw9.taxi.dao.UserDaoImpl
 hw9.taxi.controller.RegisterServlet
 hw9.taxi.exception.AuthenticationException
 webapp
 index.jsp
 register.jsp - форма регистрации

 Задание выполнить в модуле name_surname_spring
 */
@Unit(testName = "TaxiUserAuthorization", value = "hw8.taxi.service.AuthorizationService")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P9TaxiUserAuthorization extends BaseTest {

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
