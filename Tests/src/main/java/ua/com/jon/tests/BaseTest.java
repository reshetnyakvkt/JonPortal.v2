package ua.com.jon.tests;

import com.jon.tron.service.evaluation.EvaluationUtil;
import com.jon.tron.service.reflect.JavaProcessBuilder;
import com.jon.tron.service.reflect.ReflectionUtil;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.09.13
 */
public class BaseTest {
    private PrintStream out;
    private ByteArrayOutputStream in;
    private EvaluationUtil evaluationUtil;

    public static String lineSeparator = System.getProperty("line.separator");
    public static Random rnd = new Random();

    @Deprecated
    public Object setUpAndInstanciate(Class unitClass) {
        try {
            evaluationUtil = new EvaluationUtil();
            in = new ByteArrayOutputStream();
            out = new PrintStream(evaluationUtil.setInGetOut(in));
            return unitClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setUp() {
        evaluationUtil = new EvaluationUtil();
        in = new ByteArrayOutputStream();
        out = new PrintStream(evaluationUtil.setInGetOut(in));
    }

    public Object instanciate(Class unitClass) throws Throwable {
        return ReflectionUtil.instanciate(unitClass);
    }

    public void tearDown() {
        evaluationUtil.restoreInOut();
        ReflectionUtil.close();
    }

    public void invokeMainAsProcess(Class unitClass, String unitName, String input) {
        assertTrue("Метод main должен быть 'public static void main(String[] args)'", ReflectionUtil.isCorrectMainPresent(unitClass));
        //assertTrue("Класс должен быть public", instance != null);

        try {
            if(unitClass != null) {
                Object instance = instanciate(unitClass);
                // TODO check is correct invocation
                JavaProcessBuilder.buildProcessAndInvokeMethod(unitClass.getSimpleName(), "main", "/forbid.policy", null,
                        "", (Object) new String[0]);
            } else {
                JavaProcessBuilder.buildProcessAndInvokeMethod(unitName, "main", "/forbid.policy", "",
                        "", (Object) new String[0]);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            fail("Во время выполнения метода main произошла ошибка " + throwable.toString());
        }
    }

    public void invokeMain(Class unitClass, String[] args) {
        assertTrue("Метод main должен быть 'public static void main(String[] args)'", ReflectionUtil.isCorrectMainPresent(unitClass));
        //assertTrue("Класс должен быть public", instance != null);

        try {
            if(unitClass != null) {
                Object instance = instanciate(unitClass);
                ReflectionUtil.invokeMain(instance);
                // TODO check is correct invocation
//                JavaProcessBuilder.buildProcessAndInvokeMethod(file.getSimpleName(), "main", "/forbid.policy", null,
//                        "", (Object) new String[0]);
            } /*else {
                JavaProcessBuilder.buildProcessAndInvokeMethod(urlName, "main", "/forbid.policy", "",
                        "", (Object) new String[0]);
            }*/
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            fail("Во время выполнения метода main произошла ошибка " + throwable.toString());
        }
    }

    public PrintStream getOut() {
        return out;
    }

    public OutputStream getIn() {
        return in;
    }

    public Class getUnitClass(Class[] unitClasses, String unitName) {
        for (Class unitClass : unitClasses) {
            if(unitClass.getSimpleName().equals(unitName)) {
                return unitClass;
            }
        }
        return null;
    }

    public URL getResource(List<URL> files, String urlName) {
        for (URL file : files ) {
            if(file.getFile().contains(urlName)) {
                return file;
            }
        }
        return null;
    }

    protected String getLastStringFromOut() {
        String actualString = getIn().toString().trim();
        String lines[] = actualString.split("\\r?\\n");
        if (lines.length > 0) {
            actualString = lines[lines.length - 1];
        }
        return actualString;
    }
}
