package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.MatrixUtil;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 Написать метод, меняющий местами первый встреченный наибольший и наимешьний элементы в матрице.
 public void swapMaxMin(int[][] matrix)
 Пример;
 1234
 5678
 9123

 9234
 5678
 1123
 */
@Unit(testName = "B1MatrixMaxMinSwapTest", value = "checked.HelloWorld")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B1MatrixMaxMinSwapTest extends BaseTest {
    public static final String ILLEGAL_SIZE = "Не верный размер массива";

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{1, 2},
                {3, 4}};
        swapMaxMinMatrix(matrix);
    }

    public static void swapMaxMinMatrix(int[][] matrix) {
        if (matrix == null || matrix.length <= 0) {
            System.out.println("Не верный размер массива");
            return;
        }
        int min = matrix[0][0];
        int max = matrix[0][0];
        int minX = 0;
        int minY = 0;
        int maxX = 0;
        int maxY = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (min > matrix[i][j]) {
                    minX = i;
                    minY = j;
                    min = matrix[i][j];
                }
                if (max < matrix[i][j]) {
                    maxX = i;
                    maxY = j;
                    max = matrix[i][j];
                }
            }
        }
        int tmp = matrix[minX][minY];
        matrix[minX][minY] = matrix[maxX][maxY];
        matrix[maxX][maxY] = tmp;
    }

    private static final int MAX_SIZE = 9;
    private static final int MIN_SIZE = 1;

    private static final String UNIT_NAME = "VectorRandomFill";
    private static final String UNIT_METHOD_NAME = "swapMaxMinMatrix";

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
    public void testCheckMainMethod() throws Throwable {
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        instance = instanciate(unitClasses[0]);
        unitMethod = ReflectionUtil.checkMethod(unitClasses[0], UNIT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[][].class);
    }

    //@Ignore
    @Test(timeout = 1000)
    public void testSuccess() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = rnd.nextInt(MAX_SIZE) + MIN_SIZE;
        int width = rnd.nextInt(MAX_SIZE) + MIN_SIZE;

        int[][] actualMatrix = MatrixUtil.buildMatrix10(height, width);

        int[][] clone = MatrixUtil.cloneDeepMatrix(actualMatrix);
        ReflectionUtil.invokeMethod(instance, unitMethod, clone);

        swapMinMax(actualMatrix);

        assertTrue("Ожидается матрица с поменяными наибольшим и наименьшим " + MatrixUtil.deepToString(actualMatrix), MatrixUtil.compareMatrix(actualMatrix, clone));
    }

    private void swapMinMax(int[][] actualMatrix) {
        int min = actualMatrix[0][0];
        int max = actualMatrix[0][0];
        int minX = 0;
        int minY = 0;
        int maxX = 0;
        int maxY = 0;
        for (int i = 0; i < actualMatrix.length; i++) {
            for (int j = 0; j < actualMatrix[i].length; j++) {
                if (min > actualMatrix[i][j]) {
                    min = actualMatrix[i][j];
                    minX = i;
                    minY = j;
                }
                if (max < actualMatrix[i][j]) {
                    max = actualMatrix[i][j];
                    maxX = i;
                    maxY = j;
                }
            }
        }
        int tmp = actualMatrix[minX][minY];
        actualMatrix[minX][minY] = actualMatrix[maxX][maxY];
        actualMatrix[maxX][maxY] = tmp;
    }
}
