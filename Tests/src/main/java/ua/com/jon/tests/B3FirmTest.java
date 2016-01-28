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
 На фирме должны работать следующие сотрудники:
 Классы Firm, Employee, Manager, SalesManager.
 - Обычный сотрудник
 - Фамилия
 - Имя
 - Отчество
 - Пол
 - Дата рождения
 - Ставка зарплаты
 - Зарплатный счет
 - Стаж
 - Дни отпуска
 - Дата последнего отпуска
 - Отдел
 - получение размера зарплаты, метод double getSalary()
 Менеджер (отдел, подчиненные, зп + по 50 за подчененного),
 Продавец (продажи, зп + 30% от продаж)

 Фирма:
 - сотрудники
 - счет
 - вывести всех сотрудников на экран, метод void printAllEmployees()
 - вывести всех сотрудников, отсортированных по зп, метод void printAllEmployeesSortedBySalary()
 - вывести всех сотрудников, отсортированных по фамилии, метод void printAllEmployeesSortedBySName()
 - всем продавцам продать на сумму 10000, метод void sellFor10()
 - выдать всем зарплату (если не хватает, выдать сколько есть, метод void giveSalaryForAll())
 - пересчитать всем отпуска (после 6 мес, по 2 дня в месяц, метод void calcVocations())
 - поднять жалование на 10% всем сотрудникам со стажем > 5 лет, метод void riseSalary()
 -* выдать праздничный бонус 20% (если сегодня 8 марта - женщинам,
 если 23 февраля мужчинам)
 -* сокращение - уволить всех со стажем < 6 мес и нечетной
 датой рождения, зарплату увольняемого разделить между
 всеми коллегами по отделу
 -* нанять сотрудника, снять с зарплаты сотрудников отдела зарплату для новичка
 -* после любой изменяющей операции сохранять фирму в файл
 */
@Unit(testName = "B3FirmTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B3FirmTest  extends BaseTest {
    public class Firm {
        public void printAllEmployees () {
        }
        public void printAllEmployeesSortedBySalary () {
        }
        public void printAllEmployeesSortedBySName () {
        }
        public void sellFor10 () {
        }
        public void giveSalaryForAll () {
        }
        public void calcVocations () {
        }
        public void riseSalary () {
        }
    }
    class Employee {
        public void getSalary () {
        }
    }
    class Manager {
    }
    class SalesManager {
    }
    private static final String EMPLOYEE_NAME = "Employee";
    private static final String MANAGER_NAME = "Manager";
    private static final String SALES_MANAGER_NAME = "SalesManager";
    private static final String GET_SALARY_METHOD_NAME = "getSalary";
    private static final String FIRM_NAME = "Firm";
    private static final String PRINT_METHOD_NAME = "printAllEmployees";
    private static final String PRINT_BY_SALARY_METHOD_NAME = "printAllEmployeesSortedBySalary";
    private static final String PRINT_BY_SNAME_METHOD_NAME = "printAllEmployeesSortedBySName";
    private static final String SELL_BY_10_METHOD_NAME = "sellFor10";
    private static final String GIVE_SALARY_METHOD_NAME = "giveSalaryForAll";
    private static final String CALC_VOCATIONS_METHOD_NAME = "calcVocations";
    private static final String RISE_SALARY_METHOD_NAME = "riseSalary";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Object employee;
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
        assertTrue("В задании должно быть 4 класса, вместо " + unitClasses.length, unitClasses.length <= 4);
        CodeValidator.checkCodeFile(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        Class firmClass = getUnitClass(unitClasses, FIRM_NAME);
        assertNotNull("В задании не найден класс " + FIRM_NAME, firmClass);
        Class empClass = getUnitClass(unitClasses, EMPLOYEE_NAME);
        assertNotNull("В задании не найден класс " + EMPLOYEE_NAME, empClass);
        Class managerClass = getUnitClass(unitClasses, MANAGER_NAME);
        assertNotNull("В задании не найден класс " + MANAGER_NAME, managerClass);
        Class salesManagerClass = getUnitClass(unitClasses, SALES_MANAGER_NAME);
        assertNotNull("В задании не найден класс " + SALES_MANAGER_NAME, salesManagerClass);

//        employee = instanciate(empClass);
        addMethod = ReflectionUtil.checkMethod(empClass, GET_SALARY_METHOD_NAME, double.class,
                new MethodModifier[]{MethodModifier.PUBLIC});

//        instance = instanciate(firmClass);
        addMethod = ReflectionUtil.checkMethod(firmClass, PRINT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(firmClass, PRINT_BY_SALARY_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(firmClass, PRINT_BY_SNAME_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(firmClass, SELL_BY_10_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(firmClass, GIVE_SALARY_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(firmClass, CALC_VOCATIONS_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(firmClass, RISE_SALARY_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
