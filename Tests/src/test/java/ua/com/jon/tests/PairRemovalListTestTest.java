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
 * Date: 13.10.13
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class PairRemovalListTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testSuccess() throws Exception {
        final String className = "";
        final String classCode = "package example;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "import java.util.Scanner;\n" +
                "\n" +
                "public class Main {\n" +
                "\n" +
                "    public static void pairRemovalList(){\n" +
                "        Scanner scan = new Scanner(System.in);\n" +
                "        String listStr = scan.nextLine();\n" +
                "\n" +
                "        List<Integer> list = new ArrayList<Integer>();\n" +
                "        String[] elements = listStr.split(\" \");\n" +
                "        for(int i=0; i<elements.length; i++){\n" +
                "            Integer number = 0;\n" +
                "            try {\n" +
                "                number = Integer.parseInt(elements[i]);\n" +
                "                list.add(number);\n" +
                "            }\n" +
                "            catch (Exception e){\n" +
                "                e.printStackTrace();\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        for(int i=0; i<list.size(); i++){\n" +
                "            if(list.get(i)%2 == 0){\n" +
                "                list.remove(i--);\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        StringBuilder builder = new StringBuilder();\n" +
                "        for(Integer el : list){\n" +
                "            builder.append(el);\n" +
                "            builder.append(' ');\n"+
                "        }\n" +
                "\n" +
                "        System.out.println(builder.toString());\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        Main.pairRemovalList();\n" +
                "    }\n" +
                "}\n";
        final String testName = "PairRemovalList";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("Задание выполнено", resultString);
        assertEquals("100", markString);
    }

    @Test
    public void testNoOut() throws Exception {
        final String className = "PairRemovalList";
        final String classCode =
                "package forProject;\n" +
                        "\n" +
                        "import java.util.Scanner;\n" +
                        "\n" +
                        "public class PairRemovalList {\n" +
                        "\n" +
                        "    public static void pairRemovalList(){\n" +
                        "        Scanner scan = new Scanner(System.in);\n" +
                        "        String listStr = scan.nextLine();\n" +
                        "    }\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        PairRemovalList.pairRemovalList();\n" +
                        "    }\n" +
                        "}\n";
        final String testName = "PairRemovalList";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("Ожидаемый результат"));
        assertEquals("10", markString);
    }

    @Test
    public void testInfinitLoop() throws Exception {
        final String className = "PairRemovalList";
        final String classCode =
                "public class PairRemovalList {" +
                        "   public static void main(String[] args) {" +
                        "       while(true);" +
                        "   }" +
                        "}";
        final String testName = "PairRemovalList";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("test timed out after 1000 milliseconds", resultString);
        assertEquals("10", markString);
    }

}
