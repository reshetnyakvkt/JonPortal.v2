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
 * Date: 9/4/13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class HelloWorldTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testEmpty() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{}";
        final String testName = "HelloWorldTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        assertEquals("В классе отсутствует метод void main( java.lang.String[] )\n" +
                "Метод main должен выводить в консоль сообщение 'Hello world'\n", resultString);
    }

    @Test
    public void testWrongMain() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{public void main(){}}";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        assertEquals("В методе main() неверный список аргументов", resultString);

    }

    @Test
    public void testMainWithoutMessage() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{public static void main(String[] args){}}";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        assertEquals("Метод main должен выводить в консоль сообщение 'Hello world'", resultString);
    }


    @Test
    public void testNonPublicClass() throws Exception {
        final String className = "HelloWorld";
        final String classCode =
                        "    class HelloWorld {\n" +
                        "        public static void main(String[] args) {\n" +
                        "        }\n" +
                        "    }";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String resultMarkString = processResult.getKey();
        assertEquals("55", resultMarkString);
        assertEquals("В методе отсутстует модификатор public\n" +
                "В методе отсутстует модификатор static\n" +
                "\n" +
                "Метод main должен выводить в консоль сообщение 'Hello world'\n", resultString);
//        assertEquals("Невозможно создать объект класса " + className + ", возможно класс не public", resultString);
    }

    @Test
    public void testNonPublicMain() throws Exception {
        final String className = "HelloWorld";
        final String classCode =
                        "    class HelloWorld {\n" +
                        "        static void main(String[] args) {\n" +
                        "        }\n" +
                        "    }";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String resultMarkString = processResult.getKey();
        assertEquals("Невозможно создать объект класса, возможно класс не public\n" +
                "Метод main должен выводить в консоль сообщение 'Hello world'", resultString);
        assertEquals("10", resultMarkString);
    }

    @Test
    public void testNonStaticMain() throws Exception {
        final String className = "HelloWorld";
        final String classCode =
                        "    class HelloWorld {\n" +
                        "        public void main(String[] args) {\n" +
                        "        }\n" +
                        "    }";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String resultMarkString = processResult.getKey();
        assertEquals("55", resultMarkString);
        assertEquals("Невозможно создать объект класса, возможно класс не public", resultString);
    }

    @Test
    public void testNonVoidMain() throws Exception {
        final String className = "HelloWorld";
        final String classCode =
                        "    class HelloWorld {\n" +
                        "        public static String main(String[] args) {\n" +
                        "           return \"sdggg\";" +
                        "        }\n" +
                        "    }";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String resultMarkString = processResult.getKey();
        assertEquals("10", resultMarkString);
        assertEquals("Невозможно создать объект класса, возможно класс не public\n" +
                "Метод main должен выводить в консоль сообщение 'Hello world'", resultString);
    }

    @Test
    public void testMainWrongMessage() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{public static void main(String[] args){" +
                "System.out.println(\"hello world\");}}";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        assertEquals("Метод main должен выводить в консоль сообщение 'Hello world'", resultString);
    }

    @Test
    public void testMainCorrectMessage() throws Exception {
        final String className = "HelloWorld";
        final String classCode = "public class HelloWorld{public static void main(String[] args){" +
                "System.out.println(\"Hello world\");}}";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String resultMarkString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertEquals("100", resultMarkString);
    }


    @Test
    public void testThrowException() throws Exception {
        final String className = "HelloWorld";
        final String errorMessage = "errorr";
        final String classCode = "package a;" +
                "import java.util.Scanner;" +
                "public class HelloWorld{public static void main(String[] args){" +
                "   throw new RuntimeException(\""+errorMessage+"\");\n" +
                "}}";
        final String testName = "HelloWorldTest";

        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String resultMarkString = processResult.getKey();
        assertEquals("Метод main должен выводить в консоль сообщение 'Hello world'", resultString);
        assertEquals("10", resultMarkString);
    }
}

