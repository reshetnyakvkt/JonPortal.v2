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
 Пользователь выбирает график функции (x*x, 10*sin(x/5), x). Построить график выбранной функции звездочками в консоле, на промежутке 0-5.

 public void print()

 Плавно строить график заданной функции (задержка в 0.5 секунды) пока пользователь не нажмет ентер.

 Класс задания:
 hw3.graph.GraphPresenter

 Класс теста:
 hw3.graph.GraphPresenterTest
 */
@Unit(testName = "P2GraphPresenterTest", value = {"hw3.graph.GraphPresenter", "hw3.graph.GraphPresenterTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P2GraphPresenterTest extends BaseTest {
    private static final String UNIT_NAME = "GraphPresenter";
    private static final String TEST_NAME = "GraphPresenterTest";
    private static final String PRINT_METHOD_NAME = "print";

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

    private static Object instance;
    private static Method addMethod;

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
        assertTrue("В задании должено быть не более 3х классов", unitClasses.length <= 3);
        Class unitClass = getUnitClass(unitClasses, TEST_NAME);
        assertNotNull("В задании не найден класс " + TEST_NAME, unitClass);


        unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCodeFileThread(codes.get(unitClass.getName()));
        StyleChecker.checkStyle(codes, troubles);
        ReflectionUtil.checkDefaultConstructor(unitClass);

//        instance = instanciate(unitClass);
        addMethod = ReflectionUtil.checkMethod(unitClass, PRINT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
