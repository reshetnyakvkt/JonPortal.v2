package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 11/24/13
 */
@Unit(testName = "Bubble", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BubbleSortTest extends BaseTest {
    private Random rnd = new Random();

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
    public void test1Success() throws Throwable {
        final String signature = "int[] bubbleSort(int[] vector)";
        instance = instanciate(unitClass);

        int[] originalVector = generateVector(10, 10);
        int[] expectedVector = bubbleSort(originalVector.clone());
        int[] actualVector = null;
//        try {
            actualVector = (int[])ReflectionUtil.invokeMethodAsProcess(instance, "bubbleSort", int[].class, int[].class,
                    "", originalVector.clone());
/*        } catch (Throwable throwable) {
            fail("Было выброшено исключение " + throwable.getClass().getName() + ": " + throwable.getMessage() +
                    " при вызове метода " + signature);
        }*/
        assertNotNull("Метод " + signature + " не должен возвращать null");

        assertArrayEquals("После сортировки вектора " + Arrays.toString(actualVector) +
                " ожидается вектор " + Arrays.toString(expectedVector), expectedVector, actualVector);

    }

    private int[] generateVector(int elementCount, int maxRandom) {
        int[] vector = new int[elementCount];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = rnd.nextInt(maxRandom);
        }
        return vector;
    }

    private int[] bubbleSort(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < vector.length - 1; j++) {
                if(vector[j] > vector[j + 1]) {
                    int tmp = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = tmp;
                }
            }
        }
        return vector;
    }
}
