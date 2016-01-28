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
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 06.06.14
 */
@Unit(testName = "P4UserAuthTest", value = {"hw5.auth.MainWindow", "hw5.auth.UserJDBCManager", "hw5.auth.User"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P4UserAuthTest extends BaseTest {
    private static final String MAIN_UNIT_NAME = "MainWindow";
    private static final String MANAGER_UNIT_NAME = "UserJDBCManager";
    private static final String DOMAIN_UNIT_NAME = "User";
    //    private static final String TEST_NAME = "SequenceSummatorTest";
    private static final String MANAGER_CREATE_METHOD_NAME = "create";
    private static final String MANAGER_FIND_ALL_METHOD_NAME = "findAll";
    private static final String READ_BY_NAME_METHOD_NAME = "readByNamePass";


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

    @Test(timeout = 1000)
    public void testCheckMainUnitPresent() throws Throwable {
        Class unitClass;
        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, MAIN_UNIT_NAME);
            assertNotNull("В задании не найден класс " + MAIN_UNIT_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
//        assertTrue("В задании не найден класс " + UNIT_NAME, UNIT_NAME.equals(unitClass.getSimpleName()));
//        Method methodProduce = ReflectionUtil.checkMethod(unitClass, PARALLEL_SUM_METHOD_NAME, long.class,
//                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
    }

    @Test(timeout = 1000)
    public void testCheckManagerUnitPresent() throws Throwable {
        Class unitClass;
        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, MANAGER_UNIT_NAME);
            assertNotNull("В задании не найден класс " + MANAGER_UNIT_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
        assertTrue("В задании не найден класс " + MANAGER_UNIT_NAME, MANAGER_UNIT_NAME.equals(unitClass.getSimpleName()));
        Method methodCreate = ReflectionUtil.checkMethod(unitClass, MANAGER_CREATE_METHOD_NAME, "int",
                new MethodModifier[]{MethodModifier.PUBLIC},"User");
        Method methodFindAll = ReflectionUtil.checkMethod(unitClass, MANAGER_FIND_ALL_METHOD_NAME, List.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        Method methodReadByName = ReflectionUtil.checkMethod(unitClass, READ_BY_NAME_METHOD_NAME, "User",
                new MethodModifier[]{MethodModifier.PUBLIC}, "String", "String");
    }
/*

    @Test(timeout = 1000)
    public void testCheckTestPresent() throws Throwable {
        Class unitClass;
        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, TEST_NAME);
            assertNotNull("В задании не найден класс теста " + TEST_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
        assertTrue("В задании не найден класс теста " + TEST_NAME, TEST_NAME.equals(unitClass.getSimpleName()));
    }
*/


}
