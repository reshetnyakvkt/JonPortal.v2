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
import java.util.*;

import static org.junit.Assert.*;

/**
 Написать метод, сортирующий массив сортировкой вставками.
 Вывести на экран пары индексов обмениваемых элементов
 public void sortInsertion(int[] vector)
 Пример:
 sortInsertion([3,1,2,3])
 0 1
 1 2
 */
@Unit(testName = "B3InsertSortTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B3InsertSortTest extends BaseTest {
    public static void sortInsertion(int array[]){
        for (int i = 1; i < array.length; i++){
            int j = i;
            while ((j > 0) && (array[j-1] > array[j])){

                int tmp = array[j-1];
                array[j-1] = array[j];
                array[j] = tmp;
                System.out.println((j-1) + " " + j);
                j--;
            }
        }
    }

    private static final String SORT_METHOD_NAME = "sortInsertion";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;
    private static Object instance;
    private static Method unitMethod;
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
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        instance = instanciate(unitClasses[0]);
        unitMethod = ReflectionUtil.checkMethod(unitClasses[0], SORT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[].class);
    }

    @Test(timeout = 1100)
    public void testSuccess() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int from = generateInt(2, 4);
        int to = generateInt(6, 10);
        int[] vector = generateVector(from, to);
        int[] expectedVector = vector.clone();
        List<SimEntry> listExpected = insertionSort(expectedVector);

        int[] clone = vector.clone();
        ReflectionUtil.invokeMethod(instance, unitMethod, clone);
        List<SimEntry> listActual = readPairs();

        assertTrue("Ожидаемый результат " + Arrays.toString(expectedVector) + " при введенных параметрах (" +
                Arrays.toString(vector) + "), но выведено [" + Arrays.toString(clone) + "]", Arrays.equals(expectedVector, clone));
        assertTrue("Ожидаемый результат " + listExpected + " при введенных параметрах (" + Arrays.toString(vector) + "), но выведено " + listActual,
                listExpected.equals(listActual));
    }

    private List<SimEntry> readPairs() {
        List<SimEntry> list = new ArrayList<>();
        String actualString = getIn().toString().trim();
        String lines[] = actualString.split("\\r?\\n");
        for (String line : lines) {
            Scanner scan = new Scanner(line);
            if (!scan.hasNextInt()) {
                fail("Ожидается число, но введено [" + line + "]");
            }
            int first = scan.nextInt();
            if (!scan.hasNextInt()) {
                fail("Ожидается два числа, но введено [" + line + "]");
            }
            int second = scan.nextInt();
            list.add(new SimEntry(first, second));
        }
        return list;
    }

    public List<SimEntry> insertionSort(int vector[]){
        List<SimEntry> list = new ArrayList<>();
        for (int i = 1; i < vector.length; i++){
            int j = i;
            while ((j > 0) && (vector[j-1] > vector[j])){
                int tmp = vector[j-1];
                vector[j-1] = vector[j];
                vector[j] = tmp;
                list.add(new SimEntry(j, j-1));
                j--;
            }
        }
        return list;
    }

    private int generateInt(int from, int to) {
        int range = to - from;
        return (int) (Math.random() * range + from);
    }

    private int[] generateVector(int from, int to) {
        int length = (int)(Math.random() * 5 + 5);
        int[] vector = new int[length];
        int range = to - from;
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (int)(Math.random() * range + from);
        }
        return vector;
    }

    private class SimEntry {
        private Integer first;
        private Integer second;

        private SimEntry(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return first + "=" + second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SimEntry simEntry = (SimEntry) o;

            boolean firstSecond = !first.equals(simEntry.first) && !second.equals(simEntry.second);
            boolean secondFirst = !second.equals(simEntry.first) && !first.equals(simEntry.second);
            if (firstSecond && secondFirst) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = first.hashCode();
            result = 31 * result + second.hashCode();
            return result;
        }
    }
}
