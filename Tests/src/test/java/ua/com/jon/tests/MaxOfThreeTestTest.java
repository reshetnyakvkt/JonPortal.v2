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
 * Date: 9/24/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class MaxOfThreeTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccess() throws Exception {
        final String className = "MaxOfThree";
        final String classCode =
                "    package lesson;\n" +
                        "    import java.util.Scanner;\n" +
                        "    public class MaxOfThree {\n" +
                        "        public static void main(String[] args) {\n" +
                        "            Scanner scan = new Scanner(System.in);\n" +
                        "            int first = scan.nextInt();\n" +
                        "            int second = scan.nextInt();\n" +
                        "            int third = scan.nextInt();\n" +
                        "            int max = 0;\n" +
                        "            int min = 0;\n" +
                        "        if(first > second) {\n" +
                        "            if(first > third) {\n" +
                        "                max = first;\n" +
                        "            } else {\n" +
                        "                max = third;\n" +
                        "            }\n" +
                        "        } else {\n" +
                        "            if(second > third) {\n" +
                        "                max = second;\n" +
                        "            } else {\n" +
                        "                max = third;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        if(first < second) {\n" +
                        "            if(first < third) {\n" +
                        "                min = first;\n" +
                        "            } else {\n" +
                        "                min = third;\n" +
                        "            }\n" +
                        "        } else {\n" +
                        "            if(second < third) {\n" +
                        "                min = second;\n" +
                        "            } else {\n" +
                        "                min = third;\n" +
                        "            }\n" +
                        "        }\n" +
                        "            System.out.println(max);\n" +
                        "            System.out.println(min);\n" +
                        "        }\n" +
                        "    }";
        final String testName = "MaxOfThree";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertEquals("100", markString);
    }

    @Test
    public void testExtremumsSwoped() throws Exception {
        final String className = "MaxOfThree";
        final String classCode =
                "    package lesson;\n" +
                        "    import java.util.Scanner;\n" +
                        "    public class MaxOfThree {\n" +
                        "        public static void main(String[] args) {\n" +
                        "            Scanner scan = new Scanner(System.in);\n" +
                        "            int first = scan.nextInt();\n" +
                        "            int second = scan.nextInt();\n" +
                        "            int third = scan.nextInt();\n" +
                        "            int max = 0;\n" +
                        "            int min = 0;\n" +
                        "        if(first > second) {\n" +
                        "            if(first > third) {\n" +
                        "                max = first;\n" +
                        "            } else {\n" +
                        "                max = third;\n" +
                        "            }\n" +
                        "        } else {\n" +
                        "            if(second > third) {\n" +
                        "                max = second;\n" +
                        "            } else {\n" +
                        "                max = third;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        if(first < second) {\n" +
                        "            if(first < third) {\n" +
                        "                min = first;\n" +
                        "            } else {\n" +
                        "                min = third;\n" +
                        "            }\n" +
                        "        } else {\n" +
                        "            if(second < third) {\n" +
                        "                min = second;\n" +
                        "            } else {\n" +
                        "                min = third;\n" +
                        "            }\n" +
                        "        }\n" +
                        "            System.out.println(min);\n" +
                        "            System.out.println(max);\n" +
                        "        }\n" +
                        "    }";
        final String testName = "MaxOfThree";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("Ожидаемый результат"));
        assertEquals("10", markString);
    }

    @Test
    public void testNoOut() throws Exception {
        final String className = "MaxOfThree";
        final String classCode =
                "package lesson;" +
                        "import java.util.Scanner;" +
                        "public class MaxOfThree {" +
                        "   public static void main(String[] args) {" +
                        "       Scanner scan = new Scanner(System.in);" +
                        "       int first = scan.nextInt();" +
                        "       int second = scan.nextInt();" +
                        "       int third = scan.nextInt();" +
                        "   }" +
                        "}";
        final String testName = "MaxOfThree";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Первым должно быть выведено наибольшее число, но выведено []", resultString);
        assertEquals("10", markString);
    }

    @Test
    public void testInfinitLoop() throws Exception {
        final String className = "MaxOfThree";
        final String classCode =
                "public class MaxOfThree {" +
                        "   public static void main(String[] args) {" +
                        "       while(true);" +
                        "   }" +
                        "}";
        final String testName = "MaxOfThree";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("test timed out after 1000 milliseconds", resultString);
        assertEquals("10", markString);
    }
}
