package ua.com.jon.tests;

import com.jon.tron.service.junit.Unit;
import com.jon.tron.service.junit.UnitClass;
import com.jon.tron.service.junit.UnitCode;
import com.jon.tron.service.junit.UnitName;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Написать многопоточный поиск в файловой системе. Пользователь вводит путь к папке и имя файла.
 Вывести на экран те пути, где найден файл.
 Метод public void parallelFind(String path, String fileName)
 В тестах проверить поиск:
 - по существующему пути, 3х существующих файлов на разных уровнях (1,2,3)
 - по существующему пути, несуществующего файла
 - по несуществующему пути, несуществующего файла
 - единственного существующего файла в единственном каталоге

 Класс задания:
 hw3.parallel.FileFinder

 Класс теста:
 hw3.parallel.FileFinderTest
 */
@Unit(testName = "P3ParallelFileFindTest", value = {"hw4.parallel.FileFinder", "hw4.parallel.FileFinderTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P3ParallelFileFindTest  extends BaseTest {
    private static final String UNIT_NAME = "FileFinder";
    private static final String TEST_NAME = "FileFinderTest";
    private static final String PARALLEL_FIND_METHOD_NAME = "parallelFind";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method addMethod;

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
        assertTrue("В задании должено быть не более 3х классов", unitClasses.length <= 3);
//        validateCodeFileThread(codes.entrySet().iterator().next().getValue());

        Class unitClass = getUnitClass(unitClasses, TEST_NAME);
        assertNotNull("В задании не найден класс " + TEST_NAME, unitClass);

        unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCodeFileThread(codes.get(unitClass.getName()));
        ReflectionUtil.checkDefaultConstructor(unitClass);

//        instance = instanciate(unitClass);
        addMethod = ReflectionUtil.checkMethod(unitClass, PARALLEL_FIND_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, String.class);
    }
}
