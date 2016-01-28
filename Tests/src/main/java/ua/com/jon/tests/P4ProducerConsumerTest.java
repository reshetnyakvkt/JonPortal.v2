package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Map;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Один поток генерирует числа

 public void produce()

 второй выводит на экран

 public void consume()

 используя промежуточное хранилище.

 public void put(int value)
 public int get()

 Классы задания:

 hw4.threads.Producer
 hw4.threads.Consumer
 hw4.threads.Holder

 Класс теста:
 hw4.threads.ProducerConsumerTest
 */
@Unit(testName = "P4ProducerConsumerTest", value = {"hw4.threads.Producer", "hw4.threads.Consumer", "hw4.threads.Holder",
        "hw4.threads.ProducerConsumerTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P4ProducerConsumerTest extends BaseTest {
    private static final String PRODUCER_NAME = "Producer";
    private static final String CONSUMER_NAME = "Consumer";
    private static final String HOLDER_NAME = "Holder";
    private static final String TEST_NAME = "ProducerConsumerTest";
    private static final String PRODUCE_METHOD_NAME = "produce";
    private static final String CONSUME_METHOD_NAME = "consume";
    private static final String PUT_METHOD_NAME = "put";
    private static final String GET_METHOD_NAME = "get";

    @UnitCode
    protected static Map<String, String> codes;
    @UnitClass
    protected static Class[] unitClasses;
    @UnitName
    protected static String[] unitNames;
    @Unit
    protected static String unitJarClasspath;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

//    private static Object instance;
//    private static Method method;

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должено быть не более 4х классов", unitClasses.length <= 4);
        Class testClass = getUnitClass(unitClasses, TEST_NAME);
        assertNotNull("В задании не найден класс " + TEST_NAME, testClass);

        Class producerClass = getUnitClass(unitClasses, PRODUCER_NAME);
        assertNotNull("В задании не найден класс " + PRODUCER_NAME, producerClass);
        CodeValidator.checkCodeFileThread(codes.get(producerClass.getName()));
        ReflectionUtil.checkDefaultConstructor(producerClass);

//        instance = instanciate(unitClass);
        ReflectionUtil.checkMethod(producerClass, PRODUCE_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});

        Class consumerClass = getUnitClass(unitClasses, CONSUMER_NAME);
        assertNotNull("В задании не найден класс " + CONSUMER_NAME, consumerClass);
        CodeValidator.checkCodeFileThread(codes.get(consumerClass.getName()));
        ReflectionUtil.checkDefaultConstructor(consumerClass);

//        instance = instanciate(unitClass);
        ReflectionUtil.checkMethod(consumerClass, CONSUME_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});

        Class holderClass = getUnitClass(unitClasses, HOLDER_NAME);
        assertNotNull("В задании не найден класс " + HOLDER_NAME, holderClass);
        CodeValidator.checkCodeFileThread(codes.get(holderClass.getName()));
        ReflectionUtil.checkDefaultConstructor(holderClass);

//        instance = instanciate(unitClass);
        ReflectionUtil.checkMethod(holderClass, PUT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        ReflectionUtil.checkMethod(holderClass, GET_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
