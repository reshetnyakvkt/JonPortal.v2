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
import java.util.Date;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

/**
 Добавить в приложение ноутбуков следующие функции:
 - Удалить ноутбуки по названию модели
 - Получить ноутбуки по производителю
 - Получить ноутбуки по цене и году выпуска
 - Получить ноутбуки по цене в указанном диапазоне, меньше указанной даты выпуска и указанного производителя

 domain
    hw6.notes.domain.Notebook
 dao
    hw6.notes.dao.NotebookDao
        List<Notebook> findByModel(String model)
        List<Notebook> findByVendor(String vendor)
        List<Notebook> findByPriceManufDate(Double price, Date date)
        List<Notebook> findBetweenPriceLtDateByVendor(Double priceFrom, Double priceTo, Date date, String vendor)
    hw6.notes.dao.NotebookDaoImpl
 util
    hw6.notes.util.HibernateUtil
 service
    hw6.notes.service.NotebookService
        boolean deleteByModel(String model)
        List<Notebook> findByVendor(String vendor)
        List<Notebook> findByPriceManufDate(Double price, Date date)
        List<Notebook> findBetweenPriceLtDateByVendor(Double priceFrom, Double priceTo, Date date, String vendor)
    hw6.notes.service.NotebookServiceImpl
 view
    hw6.notes.service.Menu
        main()
        void deleteByModel()
        void showByVendor()
        void showByPriceManufDate()
        void showBetweenPriceLtDateByVendor()

 */
@Unit(testName = "P5Notebooks3Test", value = {
        "hw6.notes.domain.Notebook",
        "hw6.notes.dao.NotebookDao",
        "hw6.notes.dao.NotebookDaoImpl",
        "hw6.notes.service.NotebookService",
        "hw6.notes.service.NotebookServiceImpl",
        "hw6.notes.view.Menu"
        })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P5Notebooks3Test extends BaseTest {
    private static final String UNIT_DAO_NAME = "NotebookDao";
    private static final String UNIT_SERVICE_NAME = "NotebookService";
    private static final String UNIT_SERVICE_IMPL_NAME = "NotebookServiceImpl";
    private static final String UNIT_DAO_IMPL_NAME = "NotebookDaoImpl";
    private static final String UNIT_DOMAIN_NAME = "Notebook";
    private static final String FIND_BY_MODEL_DAO_METHOD_NAME = "findByModel";
    private static final String FIND_BY_VENDOR_DAO_METHOD_NAME = "findByVendor";
    private static final String FIND_BY_PRICE_DAO_METHOD_NAME = "findByPriceManufDate";
    private static final String FIND_BETWEEN_DAO_METHOD_NAME = "findBetweenPriceLtDateByVendor";
    private static final String DELETE_BY_MODEL_SERVICE_METHOD_NAME = "deleteByModel";

    @UnitCode
    private static Map<String, String> codes;
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
    public void testCheckMainUnitPresent() throws Throwable {
//        assertTrue("В задании должено быть не более 3х классов", unitClasses.length <= 3);
        Class unitClass = getUnitClass(unitClasses, UNIT_DOMAIN_NAME);
        assertNotNull("В задании не найден класс " + UNIT_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);
    }

    @Test(timeout = 1000)
    public void testCheckDaoPresent() throws Throwable {
        Class daoInterface = getUnitClass(unitClasses, UNIT_DAO_NAME);
        assertNotNull("В задании не найден класс " + UNIT_DAO_NAME, daoInterface);
//        CodeValidator.checkCodePkg(codes.get(daoInterface.getName()));
//        ReflectionUtil.checkDefaultConstructor(daoInterface);

//        instance = instanciate(daoInterface);
        ReflectionUtil.checkMethod(daoInterface, FIND_BY_MODEL_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        ReflectionUtil.checkMethod(daoInterface, FIND_BY_VENDOR_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        ReflectionUtil.checkMethod(daoInterface, FIND_BY_PRICE_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Double.class, Date.class);
        ReflectionUtil.checkMethod(daoInterface, FIND_BETWEEN_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Double.class, Double.class, Date.class, String.class);
    }

    @Test(timeout = 1000)
    public void testCheckDaoImplPresent() throws Throwable {
        Class daoImpl = getUnitClass(unitClasses, UNIT_DAO_IMPL_NAME);
        assertNotNull("В задании не найден класс " + UNIT_DAO_IMPL_NAME, daoImpl);
//        CodeValidator.checkCodePkg(codes.get(daoImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(daoImpl);
        ReflectionUtil.checkHasParent(daoImpl, UNIT_DAO_NAME);

//        instance = instanciate(daoImpl);
        ReflectionUtil.checkMethod(daoImpl, FIND_BY_MODEL_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        ReflectionUtil.checkMethod(daoImpl, FIND_BY_VENDOR_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        ReflectionUtil.checkMethod(daoImpl, FIND_BY_PRICE_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Double.class, Date.class);
        ReflectionUtil.checkMethod(daoImpl, FIND_BETWEEN_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Double.class, Double.class, Date.class, String.class);
    }

    @Test(timeout = 1000)
    public void testCheckServicePresent() throws Throwable {
        Class daoImpl = getUnitClass(unitClasses, UNIT_SERVICE_NAME);
        assertNotNull("В задании не найден класс " + UNIT_SERVICE_NAME, daoImpl);
//        CodeValidator.checkCodePkg(codes.get(daoImpl.getName()));
//        ReflectionUtil.checkDefaultConstructor(daoImpl);

//        instance = instanciate(daoImpl);
        ReflectionUtil.checkMethod(daoImpl, DELETE_BY_MODEL_SERVICE_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        ReflectionUtil.checkMethod(daoImpl, FIND_BY_VENDOR_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        ReflectionUtil.checkMethod(daoImpl, FIND_BY_PRICE_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Double.class, Date.class);
        ReflectionUtil.checkMethod(daoImpl, FIND_BETWEEN_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Double.class, Double.class, Date.class, String.class);

    }

    @Test(timeout = 1000)
    public void testCheckServiceImplPresent() throws Throwable {
        Class daoImpl = getUnitClass(unitClasses, UNIT_SERVICE_IMPL_NAME);
        assertNotNull("В задании не найден класс " + UNIT_SERVICE_IMPL_NAME, daoImpl);
//        CodeValidator.checkCodePkg(codes.get(daoImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(daoImpl);
        ReflectionUtil.checkHasParent(daoImpl, UNIT_SERVICE_NAME);

//        instance = instanciate(daoImpl);
        ReflectionUtil.checkMethod(daoImpl, DELETE_BY_MODEL_SERVICE_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        ReflectionUtil.checkMethod(daoImpl, FIND_BY_VENDOR_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        ReflectionUtil.checkMethod(daoImpl, FIND_BY_PRICE_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Double.class, Date.class);
        ReflectionUtil.checkMethod(daoImpl, FIND_BETWEEN_DAO_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Double.class, Double.class, Date.class, String.class);
    }
}
