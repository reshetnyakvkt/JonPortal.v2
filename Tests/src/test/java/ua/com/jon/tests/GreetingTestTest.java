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
 * User: Admin
 * Date: 19.09.13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class GreetingTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccess() throws Exception {
        final String className = "Greeting";
        final String classCode =
                "package lesson;" +
                "import java.util.Scanner;" +
                "public class Greeting {" +
                "   public static void main(String[] args) {" +
                "       Scanner scan = new Scanner(System.in);" +
                "       String name = scan.nextLine();" +
                "       System.out.println(\"Здравствуйте \" + name);" +
                "   }" +
                "}";
        final String testName = "GreetingTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertEquals("100", markString);
    }

    @Test
    public void testThrowException() throws Exception {
        final String className = "Greeting";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class Greeting {" +
                        "   public static void main(String[] args) {" +
                        "       Scanner scan = new Scanner(System.in);" +
                        "       String name = scan.nextLine();" +
                        "       System.out.println(\"Здравствуйте \" + name);" +
                        "       throw new RuntimeException();" +
                        "   }" +
                        "}";
        final String testName = "Greeting";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Во время выполнения метода main произошла ошибка java.lang.RuntimeException", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testInfinitLoop() throws Exception {
        final String className = "Greeting";
        final String classCode =
                "public class Greeting {" +
                "   public static void main(String[] args) {" +
                "       while(true);" +
                "   }" +
                "}";
        final String testName = "Greeting";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("test timed out after 1000 milliseconds", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testWrongMessage() throws Exception {
        final String className = "Greeting";
        final String classCode =
                "" +
                        "import java.util.Scanner;" +
                        "public class Greeting {" +
                        "   public static void main(String[] args) {" +
                        "       Scanner scan = new Scanner(System.in);" +
                        "       String name = scan.nextLine();" +
                        "       System.out.println(\"Hello world \");" +
                        "   }" +
                        "}";
        final String testName = "Greeting";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue("Задание выполнено", resultString.contains("Ожидается строка"));
        assertEquals("10", markString);
    }
}
