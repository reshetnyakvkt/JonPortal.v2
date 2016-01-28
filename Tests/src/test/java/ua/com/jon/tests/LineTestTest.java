package ua.com.jon.tests;

import com.jon.tron.service.processor.ClassProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/23/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class LineTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccessWithoutValidation() throws Exception {
        final String className = "Line";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class Line {" +
                        "   public static void main(String[] args) {" +
                        "       Scanner scan = new Scanner(System.in);" +
                        "       int length = scan.nextInt();" +
                        "       for(int i=0; i < length; i++) {" +
                        "           System.out.print('*');" +
                        "       }" +
                        "   }" +
                        "}";
        final String testName = "Line";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Во время выполнения метода main произошла ошибка java.util.NoSuchElementException", resultString);
        assertEquals("55", markString);
    }

    @Test
    public void testWrongLine() throws Exception {
        final String className = "Line";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class Line {" +
                        "   public static void main(String[] args) {" +
                        "       Scanner scan = new Scanner(System.in);" +
                        "       int length = scan.nextInt();" +
                        "       for(int i=0; i <= length; i++) {" +
                        "           System.out.print('*');" +
                        "       }" +
                        "   }" +
                        "}";
        final String testName = "Line";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("Ожидаемый результат"));
        assertEquals("10", markString);
    }


    @Test
    public void testSuccessWithValidation() throws Exception {
        final String className = "Line";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class Line {" +
                        "   public static void main(String[] args) {" +
                        "       Scanner scan = new Scanner(System.in);" +
                        "       if(!scan.hasNextInt()) {" +
                        "           System.out.println(\"Неправильный ввод\");" +
                        "           return;" +
                        "       }" +
                        "       int length = scan.nextInt();" +
                        "       for(int i=0; i < length; i++) {" +
                        "           System.out.print('*');" +
                        "       }" +
                        "   }" +
                        "}";
        final String testName = "Line";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("Задание выполнено"));
        assertEquals("100", markString);
    }
}
