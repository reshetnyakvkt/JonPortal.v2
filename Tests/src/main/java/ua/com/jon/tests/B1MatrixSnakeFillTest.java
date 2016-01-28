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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 Написать метод, заполняющий матрицу змейкой. В случае, если размер матрицы некорректный, выводить сообщение "Неверный размер матрицы"
 Название метода: fillMatrixLikeSnake
 Пример:
 void fillMatrixLikeSnake(int[][] matrix); // [4,4,3][2,2,2][3,3,3]
 [1,2,3][6,5,4][7,8,9]
 */
@Unit(testName = "B1MatrixSnakeFillTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B1MatrixSnakeFillTest extends BaseTest {
    public void fillMatrixLikeSnake(int[][] matrix) {
        int num = 1;
        for(int i = 0; matrix.length > i; i++){
            if(i%2 == 0) {
                for(int j = 0; matrix[i].length > j; j++, num++){
                    matrix[i][j] = num;
                }
            } else {
                for(int j = matrix[i].length - 1; j>=0; j--, num++)
                    matrix[i][j] = num;
            }
        }
    }
    private static final int MAX_SIZE = 9;
    private static final int MIN_SIZE = 1;

    private static final String UNIT_NAME = "VectorRandomFill";
    private static final String UNIT_METHOD_NAME = "fillMatrixLikeSnake";

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

        fillLikeSnake(actualMatrix);

        assertTrue("Ожидается матрица с поменяными наибольшим и наименьшим " + MatrixUtil.deepToString(actualMatrix), MatrixUtil.compareMatrix(actualMatrix, clone));
    }

    private void fillLikeSnake(int[][] matrix) {
        int num = 1;
        for(int i = 0; matrix.length > i; i++){
            if(i%2 == 0) {
                for(int j = 0; matrix[i].length > j; j++, num++){
                    matrix[i][j] = num;
                }
            } else {
                for(int j = matrix[i].length - 1; j>=0; j--, num++)
                    matrix[i][j] = num;
            }
        }
    }
}
