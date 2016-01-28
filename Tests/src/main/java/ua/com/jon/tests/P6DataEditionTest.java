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
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 3. Добавить в приложение ноутбуков следующие функции
 Изменить процессор
 Изменить память
 Изменить имя производителя
 Изменить тип ноутбука
 Списать со склад ноутбуки (ключ, количество)

 domain
     hw7.notes.domain.Notebook
     hw7.notes.domain.Vendor
     hw7.notes.domain.CPU
     hw7.notes.domain.Memory
     hw7.notes.domain.Store
     hw7.notes.domain.Sales
 dao
     hw7.notes.dao.NotebookDao
     hw7.notes.dao.VendorDao
     hw7.notes.dao.CPUDao
     hw7.notes.dao.MemoryDao
     hw7.notes.dao.StoreDao
     hw7.notes.dao.SalesDao
     hw7.notes.dao.NotebookDaoImpl
     hw7.notes.dao.VendorDaoImpl
     hw7.notes.dao.CPUDaoImpl
     hw7.notes.dao.MemoryDaoImpl
     hw7.notes.dao.StoreDaoImpl
     hw7.notes.dao.SalesDaoImpl
 service
    hw7.notes.service.NotebookService
        boolean updateCPU(CPU cpu)
        boolean updateMemory(Memory memory)
        boolean updateVendor(Vendor vendor)
        boolean updateNotebook(Notebook notebook)
        boolean removeFromStore(Store store, int amount)
    hw6.notes.service.NotebookServiceImpl
 view
    hw6.notes.service.Menu
        main()
 */
@Unit(testName = "P6DataEditionTest", value = {
        "hw7.notes.domain.Notebook",
        "hw7.notes.domain.Vendor",
        "hw7.notes.domain.CPU",
        "hw7.notes.domain.Memory",
        "hw7.notes.domain.Store",
        "hw7.notes.domain.Sales",
        "hw7.notes.dao.NotebookDao",
        "hw7.notes.dao.VendorDao",
        "hw7.notes.dao.CPUDao",
        "hw7.notes.dao.MemoryDao",
        "hw7.notes.dao.StoreDao",
        "hw7.notes.dao.SalesDao",
        "hw7.notes.dao.NotebookDaoImpl",
        "hw7.notes.dao.VendorDaoImpl",
        "hw7.notes.dao.CPUDaoImpl",
        "hw7.notes.dao.MemoryDaoImpl",
        "hw7.notes.dao.StoreDaoImpl",
        "hw7.notes.dao.SalesDaoImpl",
        "hw7.notes.service.NotebookService",
        "hw7.notes.service.NotebookServiceImpl",
        "hw7.notes.view.Menu"
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P6DataEditionTest extends BaseTest {
    private static final String NOTEBOOK_DOMAIN_NAME = "Notebook";
    private static final String VENDOR_DOMAIN_NAME = "Vendor";
    private static final String CPU_DOMAIN_NAME = "CPU";
    private static final String MEMORY_DOMAIN_NAME = "Memory";
    private static final String STORE_DOMAIN_NAME = "Store";
    private static final String SALES_DOMAIN_NAME = "Sales";

    private static final String NOTE_DAO_NAME = "NotebookDao";
    private static final String VENDOR_DAO_NAME = "VendorDao";
    private static final String CPU_DAO_NAME = "CPUDao";
    private static final String MEMORY_DAO_NAME = "MemoryDao";
    private static final String STORE_DAO_NAME = "StoreDao";
    private static final String SALES_DAO_NAME = "SalesDao";
    private static final String NOTE_DAO_IMPL_NAME = "NotebookDaoImpl";
    private static final String VENDOR_DAO_IMPL_NAME = "VendorDaoImpl";
    private static final String CPU_DAO_IMPL_NAME = "CPUDaoImpl";
    private static final String MEMORY_DAO_IMPL_NAME = "MemoryDaoImpl";
    private static final String STORE_DAO_IMPL_NAME = "StoreDaoImpl";
    private static final String SALES_DAO_IMPL_NAME = "SalesDaoImpl";
    private static final String UNIT_SERVICE_NAME = "NotebookService";
    private static final String UNIT_SERVICE_IMPL_NAME = "NotebookServiceImpl";
    private static final String CREATE_METHOD_NAME = "create";
    private static final String READ_METHOD_NAME = "read";
    private static final String UPDATE_METHOD_NAME = "update";
    private static final String DELETE_METHOD_NAME = "delete";
    private static final String FIND_ALL_METHOD_NAME = "findAll";
    private static final String UPDATE_CPU_SERVICE_METHOD_NAME = "updateCPU";
    private static final String UPDATE_MEMORY_SERVICE_METHOD_NAME = "updateMemory";
    private static final String UPDATE_VENDOR_SERVICE_METHOD_NAME = "updateVendor";
    private static final String UPDATE_NOTEBOOK_SERVICE_METHOD_NAME = "updateNotebook";
    private static final String REMOVE_FROM_STORE_SERVICE_METHOD_NAME = "removeFromStore";

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
//        assertTrue("В задании должено быть не более 6и классов", unitClasses.length <= 6);
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

    @Test(timeout = 1000)
    public void testCheckNoteDao() throws Throwable {
        Class daoInterface = getUnitClass(unitClasses, NOTE_DAO_NAME);
        assertNotNull("В задании не найден класс " + NOTE_DAO_NAME, daoInterface);
//        CodeValidator.checkCodePkg(codes.get(daoInterface.getName()));

        ReflectionUtil.checkMethod(daoInterface, CREATE_METHOD_NAME, "Long",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Notebook");
        ReflectionUtil.checkMethod(daoInterface, READ_METHOD_NAME, "Notebook",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Long");
        ReflectionUtil.checkMethod(daoInterface, UPDATE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Notebook");
        ReflectionUtil.checkMethod(daoInterface, DELETE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Notebook");
        ReflectionUtil.checkMethod(daoInterface, FIND_ALL_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC});

        Class daoImpl = getUnitClass(unitClasses, NOTE_DAO_IMPL_NAME);
        assertNotNull("В задании не найден класс " + NOTE_DAO_IMPL_NAME, daoImpl);
//        CodeValidator.checkCodePkg(codes.get(daoImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(daoImpl);
        ReflectionUtil.checkHasParent(daoImpl, NOTE_DAO_NAME);

//        instance = instanciate(daoImpl);
    }

    @Test(timeout = 1000)
    public void testCheckVendorDao() throws Throwable {
        Class daoInterface = getUnitClass(unitClasses, VENDOR_DAO_NAME);
        assertNotNull("В задании не найден класс " + VENDOR_DAO_NAME, daoInterface);
//        CodeValidator.checkCodePkg(codes.get(daoInterface.getName()));

        ReflectionUtil.checkMethod(daoInterface, CREATE_METHOD_NAME, "Long",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Vendor");
        ReflectionUtil.checkMethod(daoInterface, READ_METHOD_NAME, "Vendor",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Long");
        ReflectionUtil.checkMethod(daoInterface, UPDATE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Vendor");
        ReflectionUtil.checkMethod(daoInterface, DELETE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Vendor");
        ReflectionUtil.checkMethod(daoInterface, FIND_ALL_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC});

        Class daoImpl = getUnitClass(unitClasses, VENDOR_DAO_IMPL_NAME);
        assertNotNull("В задании не найден класс " + VENDOR_DAO_IMPL_NAME, daoImpl);
//        CodeValidator.checkCodePkg(codes.get(daoImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(daoImpl);
        ReflectionUtil.checkHasParent(daoImpl, VENDOR_DAO_NAME);

//        instance = instanciate(daoImpl);
    }

    @Test(timeout = 1000)
    public void testCheckCPUDao() throws Throwable {
        Class daoInterface = getUnitClass(unitClasses, CPU_DAO_NAME);
        assertNotNull("В задании не найден класс " + CPU_DAO_NAME, daoInterface);
//        CodeValidator.checkCodePkg(codes.get(daoInterface.getName()));

        ReflectionUtil.checkMethod(daoInterface, CREATE_METHOD_NAME, "Long",
                new MethodModifier[]{MethodModifier.PUBLIC}, "CPU");
        ReflectionUtil.checkMethod(daoInterface, READ_METHOD_NAME, "CPU",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Long");
        ReflectionUtil.checkMethod(daoInterface, UPDATE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "CPU");
        ReflectionUtil.checkMethod(daoInterface, DELETE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "CPU");
        ReflectionUtil.checkMethod(daoInterface, FIND_ALL_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC});

        Class daoImpl = getUnitClass(unitClasses, CPU_DAO_IMPL_NAME);
        assertNotNull("В задании не найден класс " + CPU_DAO_IMPL_NAME, daoImpl);
//        CodeValidator.checkCodePkg(codes.get(daoImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(daoImpl);
        ReflectionUtil.checkHasParent(daoImpl, CPU_DAO_NAME);

//        instance = instanciate(daoImpl);
    }

    @Test(timeout = 1000)
    public void testCheckMemoryDao() throws Throwable {
        Class daoInterface = getUnitClass(unitClasses, MEMORY_DAO_NAME);
        assertNotNull("В задании не найден класс " + MEMORY_DAO_NAME, daoInterface);
//        CodeValidator.checkCodePkg(codes.get(daoInterface.getName()));

        ReflectionUtil.checkMethod(daoInterface, CREATE_METHOD_NAME, "Long",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Memory");
        ReflectionUtil.checkMethod(daoInterface, READ_METHOD_NAME, "Memory",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Long");
        ReflectionUtil.checkMethod(daoInterface, UPDATE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Memory");
        ReflectionUtil.checkMethod(daoInterface, DELETE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Memory");
        ReflectionUtil.checkMethod(daoInterface, FIND_ALL_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC});

        Class daoImpl = getUnitClass(unitClasses, MEMORY_DAO_IMPL_NAME);
        assertNotNull("В задании не найден класс " + MEMORY_DAO_IMPL_NAME, daoImpl);
//        CodeValidator.checkCodePkg(codes.get(daoImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(daoImpl);
        ReflectionUtil.checkHasParent(daoImpl, MEMORY_DAO_NAME);

//        instance = instanciate(daoImpl);
    }

    @Test(timeout = 1000)
    public void testCheckStoreDao() throws Throwable {
        Class daoInterface = getUnitClass(unitClasses, STORE_DAO_NAME);
        assertNotNull("В задании не найден класс " + STORE_DAO_NAME, daoInterface);
//        CodeValidator.checkCodePkg(codes.get(daoInterface.getName()));

        ReflectionUtil.checkMethod(daoInterface, CREATE_METHOD_NAME, "Long",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Store");
        ReflectionUtil.checkMethod(daoInterface, READ_METHOD_NAME, "Store",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Long");
        ReflectionUtil.checkMethod(daoInterface, UPDATE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Store");
        ReflectionUtil.checkMethod(daoInterface, DELETE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Store");
        ReflectionUtil.checkMethod(daoInterface, FIND_ALL_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC});

        Class daoImpl = getUnitClass(unitClasses, STORE_DAO_IMPL_NAME);
        assertNotNull("В задании не найден класс " + STORE_DAO_IMPL_NAME, daoImpl);
//        CodeValidator.checkCodePkg(codes.get(daoImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(daoImpl);
        ReflectionUtil.checkHasParent(daoImpl, STORE_DAO_NAME);

//        instance = instanciate(daoImpl);
    }

    @Test(timeout = 1000)
    public void testCheckSalesDao() throws Throwable {
        Class daoInterface = getUnitClass(unitClasses, SALES_DAO_NAME);
        assertNotNull("В задании не найден класс " + SALES_DAO_NAME, daoInterface);
//        CodeValidator.checkCodePkg(codes.get(daoInterface.getName()));

        ReflectionUtil.checkMethod(daoInterface, CREATE_METHOD_NAME, "Long",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Sales");
        ReflectionUtil.checkMethod(daoInterface, READ_METHOD_NAME, "Sales",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Long");
        ReflectionUtil.checkMethod(daoInterface, UPDATE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Sales");
        ReflectionUtil.checkMethod(daoInterface, DELETE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Sales");
        ReflectionUtil.checkMethod(daoInterface, FIND_ALL_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC});

        Class daoImpl = getUnitClass(unitClasses, SALES_DAO_IMPL_NAME);
        assertNotNull("В задании не найден класс " + SALES_DAO_IMPL_NAME, daoImpl);
//        CodeValidator.checkCodePkg(codes.get(daoImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(daoImpl);
        ReflectionUtil.checkHasParent(daoImpl, SALES_DAO_NAME);

//        instance = instanciate(daoImpl);
    }


    @Test(timeout = 1000)
    public void testCheckServicePresent() throws Throwable {
        Class service = getUnitClass(unitClasses, UNIT_SERVICE_NAME);
        assertNotNull("В задании не найден класс " + UNIT_SERVICE_NAME, service);
//        CodeValidator.checkCodePkg(codes.get(service.getName()));

        ReflectionUtil.checkMethod(service, UPDATE_CPU_SERVICE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "CPU");
        ReflectionUtil.checkMethod(service, UPDATE_MEMORY_SERVICE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Memory");
        ReflectionUtil.checkMethod(service, UPDATE_NOTEBOOK_SERVICE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Notebook");
        ReflectionUtil.checkMethod(service, UPDATE_VENDOR_SERVICE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Vendor");
        ReflectionUtil.checkMethod(service, REMOVE_FROM_STORE_SERVICE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Store", "int");

        Class serviceImpl = getUnitClass(unitClasses, UNIT_SERVICE_IMPL_NAME);
        assertNotNull("В задании не найден класс " + UNIT_SERVICE_IMPL_NAME, serviceImpl);
//        CodeValidator.checkCodePkg(codes.get(serviceImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(serviceImpl);
        ReflectionUtil.checkHasParent(serviceImpl, UNIT_SERVICE_NAME);
    }
}
