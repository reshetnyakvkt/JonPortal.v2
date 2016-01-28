package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Пользователь вводит число, если число чётное, то вывести на экран "четное",
если число не четное, вывести на экран "нечетное".
Пример:
5
нечетное
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 29.05.14
 */
@Unit(testName = "F1PairOfTwo", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F1PairOfTwo extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int first = scan.nextInt();
        if (first % 2 == 0) {
            System.out.println("четное");
        } else {
            System.out.println("нечетное");
        }
    }

    private static final String UNIT_NAME = "MaxOfTwo";

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
    private static Method unitMethod;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void test() throws Throwable {
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        instance = instanciate(unitClasses[0]);
        unitMethod = ReflectionUtil.checkMainMethod(unitClasses[0]);
    }

    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        final int MAX_NUMBER = 10;
        int firstNumber = rnd.nextInt(MAX_NUMBER);

        String expectedString = firstNumber % 2 == 0 ? "четное" : "нечетное";
        getOut().println(firstNumber);

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();
        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("При введенном числе " + firstNumber + ", должно быть выведено [" + expectedString + "], а не [" + actualString + "]",
                expectedString.equals(actualString));

    }
}
