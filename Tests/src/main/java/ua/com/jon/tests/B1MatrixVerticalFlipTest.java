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
 Написать метод, переворачивающий матрицу относительно вертикальной оси. В случае, если размер матрицы некорректный, выводить сообщение "Неверный размер матрицы"
 Название метода: flipMatrixVertically
 Пример:
 flipMatrixVertically(int[][] matrix); // [4,4,3][2,2,4][3,1,2]
 [3,4,4][4,2,2][2,1,3]
 */
@Unit(testName = "B1MatrixVerticalFlipTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B1MatrixVerticalFlipTest extends BaseTest {
    private static final int MAX_SIZE = 9;
    private static final int MIN_SIZE = 1;

    private static final String UNIT_NAME = "VectorRandomFill";
    private static final String UNIT_METHOD_NAME = "flipMatrixVertically";

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

        flipVertically(actualMatrix);

        assertTrue("Ожидается матрица с поменяными наибольшим и наименьшим " + MatrixUtil.deepToString(actualMatrix), MatrixUtil.compareMatrix(actualMatrix, clone));
    }

    private static void flipVertically (int[][] matrix) {
        for(int i = 0; matrix.length > i; i++) {
            for(int j = 0; j < (matrix[i].length/2); j++) {
                int buffer = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[i].length - 1 - j];
                matrix[i][matrix[i].length - 1 - j] = buffer;
            }
        }
    }
}
