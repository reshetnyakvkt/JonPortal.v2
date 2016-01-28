package ua.com.jon.tests;

//import com.jon.tron.service.executor.RemoteMethodInvoker;
import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.processor.StyleChecker;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
Пользователь вводит имя текстового файла (text.txt), перевернуть содержимое файла (содержимое файла должно измениться)
Пример:
йцукен
некуцй
 */
@Unit(testName = "F3RevertFileTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//import java.io.FileReader;
//import java.io.FileWriter;
public class F3RevertFileTest extends BaseTest {
    public static void main(String[] args) throws Exception {
        java.util.Scanner scan = new java.util.Scanner(new FileReader("text.txt"));
        String textString = scan.nextLine();
        int length = textString.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = textString.length() - 1; i >= 0; i--) {
            sb.append(textString.charAt(i));
        }
        java.io.File file = new java.io.File("text.txt");
        System.out.println(" file.getAbsolutePath()");
        FileWriter writer = new FileWriter(file);

        writer.write(sb.toString());
        writer.close();
        scan.close();
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
        assertTrue("В задании должен быть один класс", unitClasses != null && classCodes != null &&
                unitClasses.length == 1 && classCodes.length == 1);
        CodeValidator.checkCodeFile(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
//        instance = instanciate(unitClasses[0]);
        unitMethod = ReflectionUtil.checkMainMethod(unitClasses[0]);
    }


    //@Test(timeout = 1100)
    public void testSuccess() throws Throwable {
        if (unitMethod == null) {
            fail();
        }
        String textString = generateString(10);
        FileWriter textWriter = new FileWriter(TEST_FILE_NAME);
        textWriter.write(textString);
        textWriter.close();

/*        RemoteMethodInvoker.getInvoker()
                .setFilePolicy()
                .setClassPath(unitJarClasspath)
                .setClasses(String[].class)
                .setObjects((Object)new String[0])
                .invokeMethod(classCodes[0], UNIT_METHOD_NAME);*/
        String out = getIn().toString().trim();
//        ReflectionUtil.invokeMethod(instance, unitMethod, actualVector);

        BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_NAME));
        String actualString = reader.readLine();
        reader.close();

        String expectedString = reverseString(textString);
        assertTrue("Задание должно перевернуть файл (сейчас файл не перевёрнут)", !textString.equals(actualString));
        assertTrue("В результате должен быть перевернутый файл с содержимым ["+expectedString+"], а не {" +
                actualString + "}", expectedString.equals(actualString));
    }

    private String reverseString(String textString) {
        int length = textString.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = textString.length() - 1; i >= 0; i--) {
            sb.append(textString.charAt(i));
        }
        return sb.toString();
    }

    private String generateString(int to) {
        int length = (int)(Math.random() * to + 5);
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int ch = 'a' + (int)(Math.random() * 20);
            sb.append((char)ch);
        }
        return sb.toString();
    }
}
