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

/**
 Написать DAO для таблицы REGIONS с использованием Hibernate
 domain
    hw6.regions.domain.Region
 dao
    hw6.regions.dao.RegionDao
        Long create(Region region)
        Region read(Long ig)
        boolean update(Region region)
        boolean delete(Region region)
        List<Region> findAll()
    hw6.regions.dao.RegionDaoImpl
 */
@Unit(testName = "P5RegionDaoTest", value = {
        "hw6.regions.domain.Region",
        "hw6.regions.dao.RegionDao",
        "hw6.regions.dao.RegionDaoImpl"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P5RegionDaoTest extends BaseTest {
    private static final String UNIT_DAO_NAME = "RegionDao";
    private static final String UNIT_DAO_IMPL_NAME = "RegionDaoImpl";
    private static final String UNIT_DOMAIN_NAME = "Region";
    private static final String CREATE_METHOD_NAME = "create";
    private static final String READ_METHOD_NAME = "read";
    private static final String UPDATE_METHOD_NAME = "update";
    private static final String DELETE_METHOD_NAME = "delete";
    private static final String FIND_ALL_METHOD_NAME = "findAll";

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
        ReflectionUtil.checkMethod(daoInterface, CREATE_METHOD_NAME, "Long",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Region");
        ReflectionUtil.checkMethod(daoInterface, READ_METHOD_NAME, "Region",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Long");
        ReflectionUtil.checkMethod(daoInterface, UPDATE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Region");
        ReflectionUtil.checkMethod(daoInterface, DELETE_METHOD_NAME, "boolean",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Region");
        ReflectionUtil.checkMethod(daoInterface, FIND_ALL_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }

    @Test(timeout = 1000)
    public void testCheckDaoImplPresent() throws Throwable {
        Class daoImpl = getUnitClass(unitClasses, UNIT_DAO_IMPL_NAME);
        assertNotNull("В задании не найден класс " + UNIT_DAO_IMPL_NAME, daoImpl);
//        CodeValidator.checkCodePkg(codes.get(daoImpl.getName()));
        ReflectionUtil.checkDefaultConstructor(daoImpl);
        ReflectionUtil.checkHasParent(daoImpl, UNIT_DAO_NAME);

        //instance = instanciate(daoImpl);
    }
}
