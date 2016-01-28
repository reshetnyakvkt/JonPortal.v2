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
 * Date: 9/29/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class AnyTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccess()  {
        final String className = "";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class FactorialIter {" +
                        "public static void main(String[] args) {" +
                        "       Scanner scan = new Scanner(System.in);\n" +
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
        final String testName = "AnyTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание принято", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testEmpty() {
        final String className = "";
        final String classCode = "public class A{}";
        String testName = "AnyTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание принято", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testInfinitLoop() {
        final String className = "";
        final String classCode = "public class A {" +
                "public static void main(String[] args) {" +
                "while(true){}" +
                "}" +
                "}";
        String testName = "AnyTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание принято", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testInfinitLoopInConstractor() {
        final String className = "";
        final String classCode = "public class A {" +
                "public A() {" +
                "   while(true);" +
                "}" +
                "public static void main(String[] args) {" +
                "   while(true);" +
                "}" +
                "}";
        String testName = "AnyTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание принято", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testThrowExceptionInConstractor() {
        final String className = "";
        final String classCode = "public class A {" +
                "public A() throws Throwable {" +
                "   throw new Throwable();" +
                "}" +
                "public static void main(String[] args) {" +
                "   while(true);" +
                "}" +
                "}";
        String testName = "AnyTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание принято", resultString);
        assertEquals("10", markString);
    }
}
