package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
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
 Создать класс Самолет на основе класса Крыло.
 Классы Plane и Wing.
 Самолет должен уметь:
 - Взлетать (это возможно если в обоих крыльях достаточно топлива для взлета, которое тратится при взлете, метод void takeoff())
 - Задавать маршрут (маршрут состоит из последовательности координат, метод void selectRoute(int[][] coordinates))
 - Вывести на экран маршрут, метод void printRoute()
 */
@Unit(testName = "B3PlaneTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B3PlaneTest extends BaseTest {
    public class Plane {
        public void takeoff() {
        }

        public void selectRoute(int[][] coordinates) {
        }

        public void printRoute() {
        }
    }

    class Wing {

    }

    private static final String PLANE_NAME = "Plane";
    private static final String WING_NAME = "Wing";
    private static final String TAKEOF_METHOD_NAME = "takeoff";
    private static final String SELECT_ROUTE_METHOD_NAME = "selectRoute";
    private static final String PRINT_METHOD_NAME = "printRoute";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object plane;
    private static Object wing;
    private static Method addMethod;
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
        assertTrue("В задании должно быть не более 2х классов, а не " + unitClasses.length, unitClasses.length <= 2);

        Class planeClass = getUnitClass(unitClasses, PLANE_NAME);
        assertNotNull("В задании не найден класс " + PLANE_NAME, planeClass);
        CodeValidator.checkCode(codes.get(planeClass.getName()));
        StyleChecker.checkStyle(codes, troubles);

        Class wingClass = getUnitClass(unitClasses, WING_NAME);
        assertNotNull("В задании не найден класс " + WING_NAME, wingClass);

//        plane = instanciate(planeClass);
//        wing = instanciate(wingClass);
        addMethod = ReflectionUtil.checkMethod(planeClass, TAKEOF_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(planeClass, PRINT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(planeClass, SELECT_ROUTE_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[][].class);
    }
}
