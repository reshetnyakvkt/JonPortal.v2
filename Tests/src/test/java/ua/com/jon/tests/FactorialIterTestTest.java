package ua.com.jon.tests;

import com.jon.tron.service.processor.ClassProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/27/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class FactorialIterTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccess() throws Exception {
        final String className = "FactorialIter";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class FactorialIter {" +
                        "public static void main(String[] args) {" +
                        "Scanner scan = new Scanner(System.in);\n" +
                        "        if(!scan.hasNextInt()) {\n" +
                        "            System.out.println(\"Неправильный ввод\");\n" +
                        "            return;\n" +
                        "        }\n" +
                        "        int number = scan.nextInt();\n" +
                        "        int fib = 1;\n" +
                        "        for(int i=1; i<=number; i++) {\n" +
                        "            fib *= i;\n" +
                        "        }\n" +
                        "        System.out.println(fib);" +
                        "}" +
                 "}";
        final String testName = "FactorialIter";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertEquals("100", markString);
    }

    @Test
    public void testWrong() throws Exception {
        final String className = "FactorialIter";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class FactorialIter {" +
                        "public static void main(String[] args) {" +
                        "Scanner scan = new Scanner(System.in);\n" +
                        "        if(!scan.hasNextInt()) {\n" +
                        "            System.out.println(\"Неправильный ввод\");\n" +
                        "            return;\n" +
                        "        }\n" +
                        "        int number = scan.nextInt();\n" +
                        "        int fib = 1;\n" +
                        "        for(int i=1; i<=number; i++) {\n" +
                        "            fib *= 12;\n" +
                        "        }\n" +
                        "        System.out.println(fib);" +
                        "}" +
                 "}";
        final String testName = "FactorialIter";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("Ожидаемый результат"));
        assertEquals("55", markString);
    }

    @Test
    public void testThrowException() throws Exception {
        final String className = "FactorialIter";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class FactorialIter {" +
                        "   public static void main(String[] args) {" +
                        "       Scanner scan = new Scanner(System.in);" +
                        "       String name = scan.nextLine();" +
                        "       System.out.println(\"Здравствуйте \" + name);" +
                        "       throw new RuntimeException();" +
                        "   }" +
                        "}";
        final String testName = "FactorialIter";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Во время выполнения метода main произошла ошибка java.lang.RuntimeException", resultString);
        assertEquals("10", markString);
    }
}
