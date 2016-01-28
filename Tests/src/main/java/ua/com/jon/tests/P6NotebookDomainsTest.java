package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Создать сущности для базы данных "Магазин ноутбуков":
    Тип ноутбука(производитель, модель, дата производства, процессор, память)
    Производители(имя)
    Процессоры(производитель, частота, модель)
    Память(производитель, размер)
    Склад ноутбуков(тип ноутбука, количество, цена)
    Продажи(склад ноутбуков, дата продажи, количество)

 domain
    hw7.notes.domain.Notebook
    hw7.notes.domain.Vendor
    hw7.notes.domain.CPU
    hw7.notes.domain.Memory
    hw7.notes.domain.Store
    hw7.notes.domain.Sales
 */
@Unit(testName = "P5Notebooks1Test", value = {
        "hw7.notes.domain.Notebook",
        "hw7.notes.domain.Vendor",
        "hw7.notes.domain.CPU",
        "hw7.notes.domain.Memory",
        "hw7.notes.domain.Store",
        "hw7.notes.domain.Sales"
        })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P6NotebookDomainsTest extends BaseTest {
    private static final String NOTEBOOK_DOMAIN_NAME = "Notebook";
    private static final String VENDOR_DOMAIN_NAME = "Vendor";
    private static final String CPU_DOMAIN_NAME = "CPU";
    private static final String MEMORY_DOMAIN_NAME = "Memory";
    private static final String STORE_DOMAIN_NAME = "Store";
    private static final String SALES_DOMAIN_NAME = "Sales";

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
        assertTrue("В задании должено быть не более 6и классов", unitClasses.length <= 6);
        Class unitClass = getUnitClass(unitClasses, NOTEBOOK_DOMAIN_NAME);
        assertNotNull("В задании не найден класс " + NOTEBOOK_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        unitClass = getUnitClass(unitClasses, VENDOR_DOMAIN_NAME);
        assertNotNull("В задании не найден класс " + VENDOR_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        unitClass = getUnitClass(unitClasses, CPU_DOMAIN_NAME);
        assertNotNull("В задании не найден класс " + CPU_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        unitClass = getUnitClass(unitClasses, MEMORY_DOMAIN_NAME);
        assertNotNull("В задании не найден класс " + MEMORY_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        unitClass = getUnitClass(unitClasses, STORE_DOMAIN_NAME);
        assertNotNull("В задании не найден класс " + STORE_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        unitClass = getUnitClass(unitClasses, SALES_DOMAIN_NAME);
        assertNotNull("В задании не найден класс " + SALES_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);
    }
}
