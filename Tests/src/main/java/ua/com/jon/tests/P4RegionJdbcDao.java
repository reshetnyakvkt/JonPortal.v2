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
 Написать DAO для таблицы REGIONS

 Реализовать следующие методы:
 long create(Region region)
 Region read(long id)
 void update(Region region)
 void delete(Region region)
 List<Region> findAll()

 Классы задания:
 hw5.dao.RegionDAO
 hw5.dao.Region
 */
@Unit(testName = "P4RegionJdbcDao", value = {"hw5.dao.RegionDAO", "hw5.dao.Region"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P4RegionJdbcDao extends BaseTest {
    private static final String DAO_NAME = "RegionDAO";
    private static final String ENTITY_NAME = "Region";
    private static final String CREATE_METHOD_NAME = "create";
    private static final String READ_METHOD_NAME = "read";
    private static final String UPDATE_METHOD_NAME = "update";
    private static final String DELETE_METHOD_NAME = "delete";
    private static final String FIND_ALL_METHOD_NAME = "findAll";

    @UnitCode
    protected static Map<String, String> codes;
    @UnitClass
    protected static Class[] unitClasses;
    @UnitName
    protected static String[] unitNames;
    @Unit
    protected static String unitJarClasspath;
    @UnitFiles
    private static List<URL> files;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

//    private static Object instance;
//    private static Method method;

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должено быть не более 2х классов", unitClasses.length <= 2);

        Class daoClass = getUnitClass(unitClasses, DAO_NAME);
        assertNotNull("В задании не найден класс " + DAO_NAME, daoClass);
//        CodeValidator.checkCodeFileThread(codes.get(daoClass.getName()));
        ReflectionUtil.checkDefaultConstructor(daoClass);

        Class entityClass = getUnitClass(unitClasses, ENTITY_NAME);
        assertNotNull("В задании не найден класс " + ENTITY_NAME, entityClass);
//        CodeValidator.checkCodeFileThread(codes.get(entityClass.getName()));
        ReflectionUtil.checkDefaultConstructor(entityClass);

        ReflectionUtil.checkMethod(daoClass, CREATE_METHOD_NAME, "long",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Region");

        ReflectionUtil.checkMethod(daoClass, READ_METHOD_NAME, "Region",
                new MethodModifier[]{MethodModifier.PUBLIC}, "long");

        ReflectionUtil.checkMethod(daoClass, UPDATE_METHOD_NAME, "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Region");

        ReflectionUtil.checkMethod(daoClass, DELETE_METHOD_NAME, "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Region");

        ReflectionUtil.checkMethod(daoClass, FIND_ALL_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
