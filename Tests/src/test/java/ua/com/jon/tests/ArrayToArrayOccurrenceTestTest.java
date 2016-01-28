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
 * User: Julia
 * Date: 03.11.13
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class ArrayToArrayOccurrenceTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccess() throws Exception {
        final String className = "";
        final String classCode = "import java.util.Scanner;\n" +
                "\n" +
                "public class ArrayToArrayOccurrence {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner scan = new Scanner(System.in);\n" +
                "        String firstLine = scan.nextLine();\n" +
                "        String secondLine = scan.nextLine();\n" +
                "\n" +
                "        String[] first = firstLine.split(\" \");\n" +
                "        String[] second = secondLine.split(\" \");\n" +
                "        int numberEquals = 0;\n" +
                "\n" +
                "        for(int i=0; i<second.length; i++){\n" +
                "            for(int j=0; j<first.length; j++){\n" +
                "                if(second[i].equals(first[j])){\n" +
                "                    numberEquals += 1;\n" +
                "                    break;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        if(numberEquals == second.length){\n" +
                "            System.out.println(\"да\");\n" +
                "        }else{\n" +
                "            System.out.println(\"нет\");\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        final String testName = "ArrayToArrayOccurrence";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertEquals("100", markString);
    }

    @Test
    public void testNoOut() throws Exception {
        final String className = "ArrayToArrayOccurrence";
        final String classCode =
                "package forProject;\n" +
                        "\n" +
                        "import java.util.Scanner;\n" +
                        "\n" +
                        "public class ArrayToArrayOccurrence {\n" +
                        "\n" +
                        "    public static void method(){\n" +
                        "        Scanner scan = new Scanner(System.in);\n" +
                        "        String listStr = scan.nextLine();\n" +
                        "    }\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        ArrayToArrayOccurrence.method();\n" +
                        "    }\n" +
                        "}\n";
        final String testName = "ArrayToArrayOccurrence";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("Ожидаемый результат"));
        assertEquals("10", markString);
    }

    @Test
    public void testInfinitLoop() throws Exception {
        final String className = "ArrayToArrayOccurrence";
        final String classCode =
                "public class ArrayToArrayOccurrence {" +
                        "   public static void main(String[] args) {" +
                        "       while(true);" +
                        "   }" +
                        "}";
        final String testName = "ArrayToArrayOccurrence";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("test timed out after 1000 milliseconds", resultString);
        assertEquals("10", markString);
    }

}
