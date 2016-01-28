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
 Написать класс фирма (hw2.Firm), содержащий имя, адрес, зарплатный счет (сумма), сотрудников.
 У каждого сотрудника (hw2.Employee) есть имя, фамилия, ставка зарплаты (сумма), его личный карточный счет (сумма), год поступления на работу, месяц поступления на работу, пол, отдел.
 Класс фирма должен выполнять следующие функции:
 - Вывести всех сотрудников фирмы на экран (метод void printAllEmployees())
 - Вывести на экран всех сорудников, отсортированных по зарплате (шейкерным методом)
 - Выдать всем сотрудникам зарплату (перевести на карточный счет каждого сотрудника, сумму равную зарплате сотрудника, с главного счета фирмы) (метод void giveSalaryForAll())
 - Пересчитать всем сотрудникам дни отпуска (всем кто проработал больше 6 месяцев, по 2 дня за каждый отработанный месяц) (метод void calcVocations())
*/
@Unit(testName = "B2FirmTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B2FirmTest extends BaseTest {
    public class Firm {
        public void printAllEmployees () {
        }
        public void giveSalaryForAll () {
        }
        public void calcVocations () {
        }
    }

    private static final String UNIT_NAME = "Firm";
    private static final String PRINT_METHOD_NAME = "printAllEmployees";
    private static final String GIVE_SALARY_METHOD_NAME = "giveSalaryForAll";
    private static final String CALC_VOCATIONS_METHOD_NAME = "calcVocations";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
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
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);

//        instance = instanciate(unitClass);
        addMethod = ReflectionUtil.checkMethod(unitClass, PRINT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, GIVE_SALARY_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, CALC_VOCATIONS_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}

