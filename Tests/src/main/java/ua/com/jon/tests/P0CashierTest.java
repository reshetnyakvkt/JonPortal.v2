package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
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
 Написать приложение "Рабочее место кассира в супермаркете", класс Cashier.
 Реализовать следующие функции приложения в виде консольного меню:
 Авторизация по имени и паролю, метод boolean isAuthorized(String login, String password).
 Показать меню, метод void showMenu()
 Создать покупку, метод void createPurchase()
 Добавить в покупку товар по штрихкоду, указать кол-во единиц покупаемого товара, метод void addGoods(String barCode, int count)
 Убрать из покупки товар по штрихкоду, указать кол-во единиц покупаемого товара, метод void removeGoods(String barCode, int count)
 Отменить покупку, метод void cancelPurchase().
 Завершить покупку, на экран выводится общаяя сумма покупки, метод double closePurchase()
 */
@Unit(testName = "P0CashierTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P0CashierTest extends BaseTest {
    public class Cashier {
        public boolean isAuthorized(String login, String password) {
            return false;
        }
        public void showMenu() {}
        public void createPurchase() {}
        public void addGoods(String barCode, int count) {}
        public void removeGoods(String barCode, int count) {}
        public void cancelPurchase() {}
        public double closePurchase() {
           return 0;
        }
    }

    private static final String UNIT_NAME = "Cashier";
    private static final String IS_AUTHORIZED_METHOD_NAME = "isAuthorized";
    private static final String SHOW_MENU_METHOD_NAME = "showMenu";
    private static final String CREATE_METHOD_NAME = "createPurchase";
    private static final String ADD_METHOD_NAME = "addGoods";
    private static final String REMOVE_METHOD_NAME = "removeGoods";
    private static final String CANCEL_METHOD_NAME = "cancelPurchase";
    private static final String CLOSE_METHOD_NAME = "closePurchase";

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
        //assertTrue("В задании должен быть только один класс", unitClasses.length == 1);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCode(unitClass.getName());

        ReflectionUtil.checkDefaultConstructor(unitClass);

//        instance = instanciate(unitClass);
        ReflectionUtil.checkMethod(unitClass, IS_AUTHORIZED_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, String.class);
        ReflectionUtil.checkMethod(unitClass, SHOW_MENU_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, CREATE_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, ADD_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, int.class);
        ReflectionUtil.checkMethod(unitClass, REMOVE_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, int.class);
        ReflectionUtil.checkMethod(unitClass, CANCEL_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, CLOSE_METHOD_NAME, double.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
