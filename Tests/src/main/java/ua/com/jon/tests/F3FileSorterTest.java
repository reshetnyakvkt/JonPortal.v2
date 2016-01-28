package ua.com.jon.tests;

//import com.jon.tron.service.executor.RemoteMethodInvoker;
import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 Пользователь водит имя файла со строковыми числами.
 Отсортировать файл с помощью сортировки вставками, метод void sortInsertion(String fileName)
 // TODOs add non number file elements
 */
@Unit(testName = "B3FileSorterTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class F3FileSorterTest extends BaseTest {
//    import java.io.FileReader;
//    import java.io.FileWriter;
//    import java.util.ArrayList;
//    import java.util.Collections;
//    import java.util.List;
    public static void main(String[] args) throws Exception {
        java.util.Scanner console = new java.util.Scanner(System.in);
        String fileName = console.nextLine();
        java.util.Scanner scan = new java.util.Scanner(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<Integer>();
        while (scan.hasNextInt()) {
            list.add(scan.nextInt());
        }
        Collections.sort(list);
        for (Integer integer : list) {
            sb.append(String.valueOf(integer)).append(" ");
        }
        java.io.File file = new java.io.File(fileName);
        FileWriter writer = new FileWriter(file);

        writer.write(sb.toString().trim());
        writer.close();
        scan.close();
        console.close();
    }

    private static final String UNIT_NAME = "FileReverter";
    private static final String UNIT_METHOD_NAME = "main";
    private static final String TEST_FILE_NAME = "text.txt";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;
    @ClassCode
    private static byte[][] classCodes;
    @Troubles
    private static List<String> troubles;

    private static Object instance;
    private static Method unitMethod;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test(timeout = 1100)
    public void test() throws Throwable {
        assertTrue("В задании должно быть не более 1го класса", unitClasses.length == 1);

        CodeValidator.checkCodeFile(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        unitMethod = ReflectionUtil.checkMainMethod(unitClasses[0]);
    }

//    @Ignore
//    @Test(timeout = 1100)
    public void testSuccess() throws Throwable {
        if (unitMethod == null) {
            fail();
        }
        getOut().println(TEST_FILE_NAME);
        StringBuilder res = new StringBuilder();
        String textString = generateVector(10, res);
        FileWriter textWriter = new FileWriter(TEST_FILE_NAME);
        textWriter.write(textString);
        textWriter.close();

/*
        RemoteMethodInvoker.getInvoker()
                .setFilePolicy()
                .setClassPath(unitJarClasspath)
                .setClasses(String[].class)
                .setObjects((Object)new String[0])
                .invokeMethod(classCodes[0], UNIT_METHOD_NAME);
*/

        BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_NAME));
        String actualString = reader.readLine();
        reader.close();

        String expectedString = res.toString().trim();
        assertTrue("Задание должно отсортировать файл (сейчас файл не отсортирован)", !textString.equals(actualString));
        assertTrue("В результате должен быть отсортированный файл с содержимым ["+expectedString+"], а не {" +
                actualString + "}", expectedString.equals(actualString));
    }

    private String generateVector(int to, StringBuilder res) {
        int length = (int)(Math.random() * to + 5);
        List<Integer> vector = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random() * 20);
            vector.add(number);
            sb.append(number).append(" ");
        }
        Collections.sort(vector);
        for (Integer integer : vector) {
            res.append(integer).append(" ");
        }
        return sb.toString();
    }
}
