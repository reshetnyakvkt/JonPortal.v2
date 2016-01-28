package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/27/13
 */
@Unit(testName = "FirstPart", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstPartTest extends BaseTest {
    @Unit
    private static Class unitClass;
    private Object instance;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void test1IllegalInput() throws Throwable {
        instance = instanciate(unitClass);
        String expectedString = "Неправильный ввод\n";
        getOut().println("\n");

        invokeMainAsProcess(unitClass, "unitName", getOut().toString());

        String actualString = getIn().toString();

        Assert.assertEquals("Неудачная проверка на неправильный ввод.\nОжидаемый результат [" + expectedString + "], " +
                "но выведено [" + actualString + "]",
                expectedString, actualString);
    }
}
