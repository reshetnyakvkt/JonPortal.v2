package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.JavaProcessBuilder;
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
 Написать класс представляющий парковку. С методами:
 - int park(Car car) - поместить машину на парковку, возвращает номер паркоместа
 - Car leave(int placeNumber) - удалить машину с парковки по номеру парокместа, возвращает удаляемую машину
 Методы выбрасывают ислключения IndexOutOfBoundsException и ParkFullException

 Написать модульный тест на класс Parking.

 Классы задания:
 hw2.park.Parking
 hw2.park.Car
 */
@Unit(testName = "P1Parking", value = "hw2.park.Parking")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P1Parking extends BaseTest {
    public class Parking {
        public int park(Car car) {
            return 0;
        }
    }
    class ParkingTest {

    }
    class Car {

    }

    private static final String UNIT_NAME = "Parking";
    private static final String TEST_NAME = "ParkingTest";
    private static final String PARK_METHOD_NAME = "park";
    private static final String LEAVE_METHOD_NAME = "leave";

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

    @Test(timeout = 1000)
    public void testCheckUnitPresent() throws Throwable {
        assertTrue("В задании должно быть не более 4х классов, а не " + unitClasses.length, unitClasses.length <= 4);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCodeFile(unitClass.getName());
        StyleChecker.checkStyle(codes, troubles);

        ReflectionUtil.checkMethod(unitClass, PARK_METHOD_NAME, "int",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Car");
        ReflectionUtil.checkMethod(unitClass, LEAVE_METHOD_NAME, "Car",
                new MethodModifier[]{MethodModifier.PUBLIC}, "int");
    }

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
}
