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
Пользователь вводит семизначное число, вывести в консоль среднее арифметическое его цифр
Пример:
1600061
2
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 30.05.14
 */
@Unit(testName = "F1DigitsAvgTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F1DigitsAvgTest extends BaseTest {
    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        int number = scan.nextInt();
        double res = 0.0;
        int por = 10;
        int count = 0;
        while (number % por != 0) {
            res += number % por;
            number /= por;
            count++;
        }
        System.out.println((int)(res/count));
    }

    private static final String UNIT_NAME = "DigitsAvg";

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
        final int DIGITS_NUMBER = 7;
        int res = generateNumber(MAX_NUMBER, DIGITS_NUMBER);
        getOut().println(res);
        int expectedRes = (int)calcAvgOfDigits(res);

        ReflectionUtil.invokeMain(instance);
        String actualString = getLastStringFromOut();
//        assertTrue("\n--- Проверка величины числа ---\nВместо [" + DIGITS_NUMBER + "] размер числа [" +
//                actualString.length() + "] разрядов", actualString.length() == DIGITS_NUMBER);
        double actualRes = 0.0;
        try {
            actualRes = Double.parseDouble(actualString);
        } catch (NumberFormatException e) {
            fail("\n--- Проверка корректности результата---\nРезультатом должно быть числом, а не " + actualString);
        }

        assertTrue("В задании должен выполняться вывод текста " + actualString, !actualString.isEmpty());
        assertTrue("\n--- Проверка корректности результата---\nПри введенном числе " + res + ", результат должен быть [" + expectedRes + "], а не [" + actualString + "]",
                expectedRes == actualRes);
    }

    private int generateNumber(int MAX_NUMBER, int DIGITS_NUMBER) {
        int por = 1;
        int res = 0;
        for (int i = 0; i < DIGITS_NUMBER; i++) {
            res += por * rnd.nextInt(MAX_NUMBER);
            por *= 10;
        }
        return res;
    }

    private double calcAvgOfDigits (int number) {
        double res = 0.0;
        int por = 1;
        int digitCount = 0;
        while (number / por != 0) {
            res += (number/por)%10;
            por *= 10;
            digitCount++;
        }
        return res/digitCount;
    }
}
