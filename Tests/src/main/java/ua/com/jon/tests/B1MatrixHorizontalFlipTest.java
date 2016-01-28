package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.MatrixUtil;
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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 Написать метод, переворачивающий матрицу относительно горизонтальной оси.
 В случае, если размер матрицы некорректный, выводить сообщение "Неверный размер матрицы"
 Название метода: flipMatrixHorizontaly
 Пример:
 flipMatrixHorizontaly(int[][] matrix); // [4,4,3][2,2,2][3,3,3]
 [3,3,3][2,2,2][4,4,3]
 */
@Unit(testName = "B1MatrixHorizontalFlipTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B1MatrixHorizontalFlipTest extends BaseTest {
    private static final int MAX_SIZE = 9;
    private static final int MIN_SIZE = 1;

    private static final String UNIT_NAME = "VectorRandomFill";
    private static final String UNIT_METHOD_NAME = "flipMatrixHorizontaly";

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

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должен быть только один класс", unitClasses.length == 1);
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        instance = instanciate(unitClasses[0]);
        unitMethod = ReflectionUtil.checkMethod(unitClasses[0], UNIT_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int[][].class);
    }

    @Test(timeout = 1100)
    public void testSuccess() throws Throwable {
        if (instance == null || unitMethod == null) {
            fail();
        }
        int height = rnd.nextInt(MAX_SIZE) + MIN_SIZE;
        int width = rnd.nextInt(MAX_SIZE) + MIN_SIZE;

        int[][] actualMatrix = MatrixUtil.buildMatrix10(height, width);

        int[][] clone = MatrixUtil.cloneDeepMatrix(actualMatrix);
        ReflectionUtil.invokeMethod(instance, unitMethod, clone);

        flipHorizontaly(actualMatrix);

        assertTrue("Ожидается матрица с поменяными наибольшим и наименьшим " + MatrixUtil.deepToString(actualMatrix), MatrixUtil.compareMatrix(actualMatrix, clone));
    }

    private void flipHorizontaly(int[][] matrix) {
        for(int i = 0; i < (matrix.length/2); i++) {
            int[] buffer = matrix[i];
            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = buffer;
        }
    }
}
