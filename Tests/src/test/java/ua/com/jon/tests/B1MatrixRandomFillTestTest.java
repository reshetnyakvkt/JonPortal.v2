package ua.com.jon.tests;

import com.jon.tron.service.processor.ClassProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 02.08.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class B1MatrixRandomFillTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccess() throws Exception {
        final String className = "Greeting";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class Greeting {" +
                        "       public static void main(String[] args) {\n" +
                        "        final String illegalSize = \"Не верный размер массива\";\n" +
                        "        Scanner scan = new Scanner(System.in); \n" +
                        "        if (!scan.hasNextInt()) {\n" +
                        "            System.out.println(illegalSize);\n" +
                        "            System.exit(0);\n" +
                        "        }\n" +
                        "        int height = scan.nextInt();\n" +
                        "        if (!scan.hasNextInt()) {\n" +
                        "            System.out.println(illegalSize);\n" +
                        "            System.exit(0);\n" +
                        "        }\n" +
                        "        int width = scan.nextInt();\n" +
                        "        if (height < 1 || width < 1) {\n" +
                        "            System.out.println(illegalSize);\n" +
                        "            System.exit(0);\n" +
                        "        }\n" +
                        "        int[][] matrix = new int[height][width];\n" +
                        "        fillMatrixByRandom(matrix);\n" +
                        "        printMatrix(matrix);\n" +
                        "    }\n" +
                        "    public static void fillMatrixByRandom(int[][] matrix) {\n" +
                        "        for (int i = 0; i < matrix.length; i++) {\n" +
                        "            for (int j = 0; j < matrix[i].length; j++) {\n" +
                        "                matrix[i][j] = (int)(Math.random() * 10);\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "    public static void printMatrix(int[][] matrix) {\n" +
                        "        for (int i = 0; i < matrix.length; i++) {\n" +
                        "            for (int j = 0; j < matrix[i].length; j++) {\n" +
                        "                System.out.print(matrix[i][j]);\n" +
                        "            }\n" +
                        "            System.out.println();\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";
        final String testName = "B1MatrixRandomFillTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertEquals("100", markString);
    }
}
