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
import static org.junit.Assert.assertTrue;

/**
 Написать приложение "Магазин ноутбуков" с использованием Spring:
 Тип ноутбука(производитель, модель, дата производства, процессор, память)
 Производители(имя)
 Процессоры(производитель, частота, модель)
 Память(производитель, размер)
 Склад ноутбуков(тип ноутбука, количество, цена)
 Продажи(склад ноутбуков, дата продажи, количество)

 Добавить следующие функции:
 Создать процессор
 Создать память
 Создать производителя
 Создать тип ноутбука
 Принять на склад партию ноутбуков (тип ноутбука, количество, цена)
 Продать указанное количество ноутбуков со склада(id склада, количество)
 Изменить процессор
 Изменить память
 Изменить имя производителя
 Изменить тип ноутбука
 Списать со склад ноутбуки (ключ, количество)
 Показать все ноутбуки на складе (пользователь указывает размер порции)
 Показать все ноутбуки которых больше указанного количества
 Показать все ноутбуки по указанному имени производителя процессора
 Показать все ноутбуки на складе
 Показать типы ноутбуков, оставшиеся на складе по каждому производителю
 Получить объем продаж ноутбуков по каждому дню

 domain
 hw7.springnotes.domain.Notebook
 hw7.springnotes.domain.Vendor
 hw7.springnotes.domain.CPU
 hw7.springnotes.domain.Memory
 hw7.springnotes.domain.Store
 hw7.springnotes.domain.Sales
 dao
 hw7.springnotes.dao.NotebookDao
 Long create(Notebook notebook)
 Notebook read(Long ig)
 boolean update(Notebook notebook)
 boolean delete(Notebook notebook)
 List<Notebook> findAll()
 hw7.springnotes.dao.VendorDao
 Long create(Vendor vendor)
 Vendor read(Long ig)
 boolean update(Vendor vendor)
 boolean delete(Vendor vendor)
 List<Vendor> findAll()
 hw7.springnotes.dao.CPUDao
 Long create(CPU cpu)
 CPU read(Long ig)
 boolean update(CPU cpu)
 boolean delete(CPU cpu)
 List<CPU> findAll()
 hw7.springnotes.dao.MemoryDao
 Long create(Memory memory)
 Memory read(Long ig)
 boolean update(Memory memory)
 boolean delete(Memory memory)
 List<Memory> findAll()
 hw7.springnotes.dao.StoreDao
 Long create(Store store)
 Store read(Long ig)
 boolean update(Store store)
 boolean delete(Store store)
 List<Store> findAll()
 hw7.springnotes.dao.SalesDao
 Long create(Sales store)
 Sales read(Long ig)
 boolean update(Sales store)
 boolean delete(Sales store)
 List<Sales> findAll()

 hw7.springnotes.dao.NotebookDaoImpl
 hw7.springnotes.dao.VendorDaoImpl
 hw7.springnotes.dao.CPUDaoImpl
 hw7.springnotes.dao.MemoryDaoImpl
 hw7.springnotes.dao.StoreDaoImpl
 hw7.springnotes.dao.SalesDaoImpl
 service
 hw7.springnotes.service.NotebookService
 List<Notebook> getNotebooksByPortion(int size)
 List<Notebook> getNotebooksGtAmount(int amount)
 List<Notebook> getNotebooksByCpuVendor(Vendor cpuVendor)
 List<Notebook> getNotebooksFromStore()
 List<Notebook> getNotebooksStorePresent()
 Map<Notebook, int> getSalesByDays()
 boolean updateCPU(CPU cpu)
 boolean updateMemory(Memory memory)
 boolean updateVendor(Vendor vendor)
 boolean updateNotebook(Notebook notebook)
 boolean removeFromStore(Store store, int amount)
 hw7.springnotes.service.NotebookServiceImpl
 view
 hw7.springnotes.service.Menu
 hw7.springnotes/context.xml
 Main
 */
@Unit(testName = "P6SpringNotebookTest", value = {
        "hw7.springnotes.domain.Notebook",
        "hw7.springnotes.domain.Vendor",
        "hw7.springnotes.domain.CPU",
        "hw7.springnotes.domain.Memory",
        "hw7.springnotes.domain.Store",
        "hw7.springnotes.domain.Sales",
        "hw7.springnotes.dao.NotebookDao",
        "hw7.springnotes.dao.VendorDao",
        "hw7.springnotes.dao.CPUDao",
        "hw7.springnotes.dao.MemoryDao",
        "hw7.springnotes.dao.StoreDao",
        "hw7.springnotes.dao.SalesDao",
        "hw7.springnotes.dao.NotebookDaoImpl",
        "hw7.springnotes.dao.VendorDaoImpl",
        "hw7.springnotes.dao.CPUDaoImpl",
        "hw7.springnotes.dao.MemoryDaoImpl",
        "hw7.springnotes.dao.StoreDaoImpl",
        "hw7.springnotes.dao.SalesDaoImpl",
        "hw7.springnotes.service.NotebookService",
        "hw7.springnotes.service.NotebookServiceImpl",
        "hw7.springnotes.view.Menu"
        })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P6SpringNotebookTest extends BaseTest {
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
    private static final String RECEIVE_SERVICE_METHOD_NAME = "receive";
    private static final String SALE_SERVICE_METHOD_NAME = "sale";
    private static final String BY_PORTION_METHOD_NAME = "getNotebooksByPortion";
    private static final String GT_AMOUNT_SERVICE_METHOD_NAME = "getNotebooksGtAmount";
    private static final String CPU_VENDOR_SERVICE_METHOD_NAME = "getNotebooksByCpuVendor";
    private static final String FROM_STORE_SERVICE_METHOD_NAME = "getNotebooksFromStore";
    private static final String STORE_PRESENT_SERVICE_METHOD_NAME = "getNotebooksStorePresent";
    private static final String SALES_BY_DAYS_SERVICE_METHOD_NAME = "getSalesByDays";
    private static final String CONTEXT_NAME = "context.xml";

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
    public void testCheckMainUnitPresent() throws Throwable {
//        assertTrue("В задании должено быть не более 6и классов", unitClasses.length <= 6);
        Class unitClass = getUnitClass(unitClasses, NOTEBOOK_DOMAIN_NAME);
        ReflectionUtil.checkHasAnnotation(unitClass, "Entity");
        assertNotNull("В задании не найден класс " + NOTEBOOK_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        unitClass = getUnitClass(unitClasses, VENDOR_DOMAIN_NAME);
        ReflectionUtil.checkHasAnnotation(unitClass, "Entity");
        assertNotNull("В задании не найден класс " + VENDOR_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        unitClass = getUnitClass(unitClasses, CPU_DOMAIN_NAME);
        ReflectionUtil.checkHasAnnotation(unitClass, "Entity");
        assertNotNull("В задании не найден класс " + CPU_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        unitClass = getUnitClass(unitClasses, MEMORY_DOMAIN_NAME);
        ReflectionUtil.checkHasAnnotation(unitClass, "Entity");
        assertNotNull("В задании не найден класс " + MEMORY_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        unitClass = getUnitClass(unitClasses, STORE_DOMAIN_NAME);
        ReflectionUtil.checkHasAnnotation(unitClass, "Entity");
        assertNotNull("В задании не найден класс " + STORE_DOMAIN_NAME, unitClass);
//        CodeValidator.checkCodePkg(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

        unitClass = getUnitClass(unitClasses, SALES_DOMAIN_NAME);
        ReflectionUtil.checkHasAnnotation(unitClass, "Entity");
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
        ReflectionUtil.checkHasAnnotation(daoImpl, "Repository");
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
        ReflectionUtil.checkHasAnnotation(daoImpl, "Repository");
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
        ReflectionUtil.checkHasAnnotation(daoImpl, "Repository");
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
        ReflectionUtil.checkHasAnnotation(daoImpl, "Repository");
        assertNotNull("В задании не найден класс " + MEMORY_DAO_IMPL_NAME, daoImpl);
//        CodeValidator.checkCodePkg(codes.get(daoImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(daoImpl);
        ReflectionUtil.checkHasParent(daoImpl, MEMORY_DAO_NAME);

//        instance = instanciate(daoImpl);
    }

    @Test(timeout = 1000)
    public void testCheckStoreDao() throws Throwable {
        Class daoInterface = getUnitClass(unitClasses, STORE_DAO_NAME);
//        assertNotNull("В задании не найден класс " + STORE_DAO_NAME, daoInterface);
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
        ReflectionUtil.checkHasAnnotation(daoImpl, "Repository");
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
        ReflectionUtil.checkHasAnnotation(daoImpl, "Repository");
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

        ReflectionUtil.checkMethod(service, RECEIVE_SERVICE_METHOD_NAME, "Long",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Notebook", "int", "double");
        ReflectionUtil.checkMethod(service, SALE_SERVICE_METHOD_NAME, Long.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Long.class, int.class);
        ReflectionUtil.checkMethod(service, BY_PORTION_METHOD_NAME, "List",
                new MethodModifier[]{MethodModifier.PUBLIC}, "int");
        ReflectionUtil.checkMethod(service, GT_AMOUNT_SERVICE_METHOD_NAME, "List",
                new MethodModifier[]{MethodModifier.PUBLIC}, "int");
        ReflectionUtil.checkMethod(service, CPU_VENDOR_SERVICE_METHOD_NAME, "List",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Vendor");
        ReflectionUtil.checkMethod(service, FROM_STORE_SERVICE_METHOD_NAME, "List",
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(service, STORE_PRESENT_SERVICE_METHOD_NAME, "List",
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(service, SALES_BY_DAYS_SERVICE_METHOD_NAME, "Map",
                new MethodModifier[]{MethodModifier.PUBLIC});

        Class serviceImpl = getUnitClass(unitClasses, UNIT_SERVICE_IMPL_NAME);
        ReflectionUtil.checkHasAnnotation(serviceImpl, "Service");
        assertNotNull("В задании не найден класс " + UNIT_SERVICE_IMPL_NAME, serviceImpl);
//        CodeValidator.checkCodePkg(codes.get(serviceImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(serviceImpl);
        ReflectionUtil.checkHasParent(serviceImpl, UNIT_SERVICE_NAME);
    }

    @Test(timeout = 1000)
    public void testCheckSpringContent() throws Throwable {
        URL context = getResource(files, CONTEXT_NAME);
        assertNotNull("В задании не найден файл " + CONTEXT_NAME, context);
    }
}
