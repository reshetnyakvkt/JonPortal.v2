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
 Написать приложение с иерархией фигур (треугольник, круг, квадрат, прямоугольник).
 Классы Figure, Square, Circle, Rectangle, Triangle, Main.
 Реализовать методы:
 Вычисления периметра, double perim()
 Вычисления площади, double square()
 Вывода фигуры в консоль, void print()
 Посчитать общую площадь всех фигур методе main.
 */
@Unit(testName = "B4FiguresTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B4FiguresTest extends BaseTest {
    public class Figure {
        public double perim() {
            return 0;
        }
        public double square() {
            return 0;
        }
        public void print() {}
    }

    private static final String FIGURE_NAME = "Figure";
    private static final String SQUARE_NAME = "Square";
    private static final String CIRCLE_NAME = "Circle";
    private static final String RECTANGLE_NAME = "Rectangle";
    private static final String TRIANGLE_NAME = "Triangle";
    private static final String MAIN_NAME = "Main";

    private static final String PERIM_METHOD_NAME = "perim";
    private static final String SQUARE_METHOD_NAME = "square";
    private static final String PRINT_METHOD_NAME = "print";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method getMethod;
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
        assertTrue("В задании должно быть не более 7 классов, а не " + unitClasses.length, unitClasses.length <= 7);
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        Class unitClass = getUnitClass(unitClasses, FIGURE_NAME);
        assertNotNull("В задании не найден класс " + FIGURE_NAME, unitClass);
        getUnitClass(unitClasses, SQUARE_NAME);
        assertNotNull("В задании не найден класс " + SQUARE_NAME, unitClass);
        getUnitClass(unitClasses, RECTANGLE_NAME);
        assertNotNull("В задании не найден класс " + RECTANGLE_NAME, unitClass);
        getUnitClass(unitClasses, CIRCLE_NAME);
        assertNotNull("В задании не найден класс " + CIRCLE_NAME, unitClass);
        getUnitClass(unitClasses, TRIANGLE_NAME);
        assertNotNull("В задании не найден класс " + TRIANGLE_NAME, unitClass);
        getUnitClass(unitClasses, MAIN_NAME);
        assertNotNull("В задании не найден класс " + MAIN_NAME, unitClass);

        //ReflectionUtil.checkDefaultConstructor(unitClass);
//        ReflectionUtil.checkDefaultConstructor(unitClass, "");

        //instance = instanciate(unitClasses[0]);
        addMethod = ReflectionUtil.checkMethod(unitClass, SQUARE_METHOD_NAME, double.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, PERIM_METHOD_NAME, double.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, PRINT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
