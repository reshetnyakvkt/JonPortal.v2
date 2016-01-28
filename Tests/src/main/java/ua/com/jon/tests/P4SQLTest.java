package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 09.09.14
 */
@Unit(testName = "P4SQLTest", value = {"hw4.sql.Queries"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P4SQLTest extends BaseTest {
    private static final String QUERIES_NAME = "Queries";

    @UnitCode
    protected static Map<String, String> codes;
    @UnitClass
    protected static Class[] unitClasses;
    @UnitName
    protected static String[] unitNames;
    @Unit
    protected static String unitJarClasspath;

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должено быть не более 1го класса", unitClasses.length == 1);

        Class queriesClass = getUnitClass(unitClasses, QUERIES_NAME);
        assertNotNull("В задании не найден класс " + QUERIES_NAME, queriesClass);
//        CodeValidator.checkCode(codes.get(queriesClass.getName()));
        ReflectionUtil.checkDefaultConstructor(queriesClass);
    }
}
