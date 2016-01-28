package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
Пользователь вводит высоту и ширину матрицы. Заполнить матрицу случайными числами [0..9] и вывести на экран.
Пример;
3
4
1234
5678
9123
 */
@Unit(testName = "B1MatrixRandomFillTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B1MatrixRandomFillTest extends BaseTest {
    public static final String ILLEGAL_SIZE = "Не верный размер массива";
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите размер массива");
        if (!scan.hasNextInt()) {
            System.out.println(ILLEGAL_SIZE);
//            System.exit(0);
        }
        int height = scan.nextInt();
        System.out.println("Введите размер массива");
        if (!scan.hasNextInt()) {
            System.out.println(ILLEGAL_SIZE);
//            System.exit(0);
        }
        int width = scan.nextInt();
        if (height < 1 || width < 1) {
            System.out.println(ILLEGAL_SIZE);
//            System.exit(0);
        }
        int[][] matrix = new int[height][width];
        fillMatrixByRandom(matrix);
        printMatrix(matrix);
    }
    public static void fillMatrixByRandom(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int)(Math.random() * 10);
            }
        }
    }
    public static void printMatrix(int[][] matrix) {
        for (int[] vector : matrix) {
            for (int el : vector) {
                System.out.print(el);
            }
            System.out.println();
        }
    }

    private static final int MAX_SIZE = 9;
    private static final int MIN_SIZE = 1;

    private static final String UNIT_NAME = "VectorRandomFill";
    private static final String UNIT_METHOD_NAME = "fillMatrixByRandom";

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
        int height = rnd.nextInt(MAX_SIZE) + MIN_SIZE;
        int width = rnd.nextInt(MAX_SIZE) + MIN_SIZE;

        getOut().println(height);
        getOut().println(width);

        ReflectionUtil.invokeMain(instance);
        String actualMatrixStr = getIn().toString();
        checkSize(actualMatrixStr, height, width);

        int[][] actualMatrix = null;
        actualMatrix = digitalizeMatrix(actualMatrixStr, height, width);
        boolean isAllZero = true;

        for (int[] line : actualMatrix) {
            for (int elem : line) {
                if (elem != 0) {
                    isAllZero = false;
                }
                if (elem < 0 || elem > 9) {
                    fail("Значения в массиве должны быть в диапазоне [0-9], а элемент " + elem + " нет");
                }
            }
        }

        assertFalse("Массив должен быть заполнен случайными значениями, а не быть пустым ", isAllZero);

        super.setUp();
        getOut().println(height);
        getOut().println(width);
        ReflectionUtil.invokeMain(instance);
        String secondMatrixStr = getIn().toString();

        assertTrue("При разных вызовах метода, массив заполняется одинаково " + actualMatrixStr, !actualMatrixStr.equals(secondMatrixStr));

        super.setUp();
        getOut().println(1);
        getOut().println(1);
        ReflectionUtil.invokeMain(instance);
        String singletonMatrixStr = getIn().toString().trim();
        try {
            int elem = Integer.parseInt(singletonMatrixStr);
            if (elem < 0 || elem > 9) {
                fail("Значения в массиве должны быть в диапазоне [0-9], а элемент " + elem + " нет");
            }
        } catch (Exception e) {
            fail("В массиве не число " + singletonMatrixStr);
        }
    }

    @Test(timeout = 1000)
    public void testIllegal() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = -1;
        int width = rnd.nextInt(MAX_SIZE + MIN_SIZE);

        getOut().println(height);
        getOut().println(width);

        ReflectionUtil.invokeMain(instance);
        String actualMatrixStr = getIn().toString().trim();

        assertTrue("При неправильном размере массива (высота -1), необходимо выводить сообщение " + ILLEGAL_SIZE + "а не " + actualMatrixStr, ILLEGAL_SIZE.equals(actualMatrixStr));

        height = rnd.nextInt(MAX_SIZE + MIN_SIZE);
        width = -1;

        super.setUp();
        getOut().println(height);
        getOut().println(width);
        ReflectionUtil.invokeMain(instance);
        actualMatrixStr = getIn().toString().trim();

        assertTrue("При неправильном размере массива (ширина -1), необходимо выводить сообщение " + ILLEGAL_SIZE + "а не " + actualMatrixStr, ILLEGAL_SIZE.equals(actualMatrixStr));
    }

    private boolean checkSize(String actualMatrixStr, int height, int width) {
        String lines[] = actualMatrixStr.split("\\r?\\n");
        if (lines.length != height) {
            fail("Не верная высота массива " + lines.length + ", должна быть " + height + ", " + Arrays.toString(lines));
        }
        for (String line : lines) {
            if (line.length() != width) {
                fail("Не верная ширина массива " + line.length() + ", должна быть " + width + ", " + Arrays.toString(lines));
            }
        }
        return true;
    }

    private int[][] digitalizeMatrix(String matrixStr, int height, int width) throws IllegalArgumentException {
        int[][] matrix = new int[height][width];
        String lines[] = matrixStr.split("\\r?\\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (int j = 0; j < line.length(); j++) {
                int digit = Character.digit(line.charAt(j), 10);
                if (digit < 0) {
                    fail("Значения в массиве должны быть в диапазоне [0-9], а элемент " + digit + " нет");
                }
                matrix[i][j] = digit;
            }
        }
        return matrix;
    }
}
