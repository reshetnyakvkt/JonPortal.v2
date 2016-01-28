package ua.com.jon.tests;

import com.jon.tron.service.processor.ClassProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 11/24/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class BubbleSortTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccess()  {
        final String className = "";
        final String classCode =
                "package lesson;" +
                        "public class BubbleSorter {" +
                        "    private int[] bubbleSort(int[] vector) {\n" +
                        "        for (int i = 0; i < vector.length; i++) {\n" +
                        "            for (int j = 0; j < vector.length - 1; j++) {\n" +
                        "                if(vector[j] > vector[j + 1]) {\n" +
                        "                    int tmp = vector[j];\n" +
                        "                    vector[j] = vector[j + 1];\n" +
                        "                    vector[j + 1] = tmp;\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return vector;\n" +
                        "    }" +
//                        "}" +
                        "}";
        final String testName = "Bubble";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertEquals("100", markString);
    }

    @Test
    public void testEmpty() {
        final String className = "";
        final String classCode = "public class A{}";
        String testName = "Bubble";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("В классе отсутствует метод int[] bubbleSort( int[] )", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testInfinitLoop() {
        final String className = "";
        final String classCode = "public class A {" +
                "private int[] bubbleSort(int[] vector) {\n" +
                "   while(true);" +
                "}" +
                "}";
        String testName = "Bubble";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("test timed out after 1000 milliseconds", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testEmptyMethod() {
        final String className = "";
        final String classCode = "public class A {" +
                "private int[] bubbleSort(int[] vector) {\n" +
                "   return vector;" +
                "}" +
                "}";
        String testName = "Bubble";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("ожидается вектор"));
        assertEquals("10", markString);
    }

    @Test
    public void testEmptyStaticMethod() {
        final String className = "";
        final String classCode = "public class A {" +
                "public static int[] bubbleSort(int[] vector) {\n" +
                "   return vector;" +
                "}" +
                "}";
        String testName = "Bubble";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue( resultString.contains("ожидается вектор"));
        assertEquals("10", markString);
    }

    @Test
    public void testIllegal() {
        final String className = "";
        final String classCode =
                "package lesson;" +
                "public class BubbleSorter {" +
                "    private int[] bubbleSort(int[] vector) {\n" +
                "        for (int i = 0; i < vector.length; i++) {\n" +
                "            for (int j = 0; j < vector.length; j++) {\n" +
                "                if(vector[j] > vector[j + 1]) {\n" +
                "                    int tmp = vector[j];\n" +
                "                    vector[j] = vector[j + 1];\n" +
                "                    vector[j + 1] = tmp;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        return vector;\n" +
                "    }" +
                "}";
        String testName = "Bubble";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        System.out.println(resultString);
        assertTrue(resultString.contains("было выброшено исключение java.lang.ArrayIndexOutOfBoundsException"));
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
        String testName = "Bubble";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("test timed out after 1000 milliseconds", resultString);
        assertEquals("10", markString);
    }

}
