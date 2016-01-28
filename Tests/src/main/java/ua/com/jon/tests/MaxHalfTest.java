package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.reflect.JavaProcessBuilder;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Julia
 * Date: 01.12.13
 */
@Unit(testName = "MaxHalf", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MaxHalfTest extends BaseTest {

    private void printMaxHalf(int[] vector) {
        int firstSum = 0;
        int secondSum = 0;
        for (int i = 0; i < vector.length / 2; i++) {
            firstSum += vector[i];
            secondSum += vector[vector.length - 1 - i];
        }
        if (firstSum > secondSum) {
            for (int i = 0; i < vector.length / 2; i++) {
                System.out.print(vector[i] + " ");
            }
        } else {
            for (int i = 0; i < vector.length / 2; i++) {
                System.out.print(vector[vector.length - 1 - i] + " ");
            }
        }
    }

    private Random rnd = new Random();

    private static final String UNIT_NAME = "MaxHalf";
    private static final String METHOD_NAME = "printMaxHalf";

    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1000)
    public void testCheckMainMethod() throws Throwable {
        Class unitClass;
        if(unitClasses.length != 1) {
            unitClass = getUnitClass(unitClasses, UNIT_NAME);
            TestCase.assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        } else {
            unitClass = unitClasses[0];
        }
        assertTrue("В задании не найден класс " + UNIT_NAME, UNIT_NAME.equals(unitClass.getSimpleName()));
        Method taskMethod = ReflectionUtil.checkMethod(unitClass, METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[].class);
    }

    @Test(timeout = 1000)
    public void test1Success() throws Throwable {
        final String signature = "void maxHalf(int[] vector)";
        //instance = instanciate(unitClass);

        int[] originalVector = generateVector(10, 10);
        String actualVector = checkMaxHalf(originalVector.clone());

        try {
            JavaProcessBuilder.buildProcessAndInvokeMethod(UNIT_NAME, METHOD_NAME, "/forbid.policy", unitJarClasspath,
                    "", (Object) originalVector.clone());
            //ReflectionUtil.invokeMethodAsProcess(instance, "maxHalf", int[].class, void.class, "", originalVector.clone());
            String expectedVector = getIn().toString();

            assertEquals("Ожидается " + expectedVector + "но выведено " + actualVector, expectedVector, actualVector);

        } catch (Throwable throwable) {
//            fail("Было выброшено исключение " + throwable.getClass().getName() + ": " + throwable.getMessage() + " при вызове метода " + signature);
        }
    }

    private int[] generateVector(int elementCount, int maxRandom) {
        int[] vector = new int[elementCount];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = rnd.nextInt(maxRandom);
        }
        return vector;
    }

    private String checkMaxHalf(int[] vector) {
        int firstSum = 0;
        int secondSum = 0;
        StringBuilder resultMaxHalf = new StringBuilder();
        for (int i = 0; i < vector.length / 2; i++) {
            firstSum += vector[i];
            secondSum += vector[vector.length - 1 - i];
        }
        if (firstSum > secondSum) {
            for (int i = 0; i < vector.length / 2; i++) {
                resultMaxHalf.append(vector[i]).append(" ");
            }
        } else {
            for (int i = 0; i < vector.length / 2; i++) {
                resultMaxHalf.append(vector[vector.length - 1 - i]).append(" ");
            }
        }
        return resultMaxHalf.toString();
    }

}
