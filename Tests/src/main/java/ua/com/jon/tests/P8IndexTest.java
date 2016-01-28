package ua.com.jon.tests;

import com.jon.tron.service.junit.*;
import com.jon.tron.service.processor.CodeValidator;
import com.jon.tron.service.reflect.MethodModifier;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

/**
 Создать главную страницу сайта с формой аутентификации и ссылкой на форму регистрации (авторизации). На странице должны быть следующие элементы, оформленные с помощью html и css:
 1. Заголовок с картинкой.
 2. Форма аутентификации.
 3. Ссылка на форму регистрации.
 4. Описание работы службы такси.
 5. Колонтитул с описанием прав собственности.

 webapp
    img
        header.jpg
    css
        style.css
    index.jsp - страница с формой аутентификации

 Задание выполнить в модуле name_surname_spring
 */
@Unit(testName = "P8IndexTest")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P8IndexTest extends BaseTest {
    private static final String INDEX_NAME = "index.jsp";
    private static final String IMG_NAME = "header.jpg";
    private static final String CSS_NAME = "style.css";

    @UnitCode
    private static Map<String, String> codes;
    @UnitFiles
    private static List<URL> files;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    private static Object instance;
    private static Method method;

    @Test(timeout = 1000)
    public void testCheckIndex() throws Throwable {

        URL index = getResource(files, INDEX_NAME);
        assertNotNull("В задании не найден файл " + INDEX_NAME, index);
    }

    @Test(timeout = 1000)
    public void testCheckImg() throws Throwable {

        URL index = getResource(files, IMG_NAME);
        assertNotNull("В задании не найден файл " + IMG_NAME, index);
    }

    @Test(timeout = 1000)
    public void testCheckCSS() throws Throwable {
        URL welcome = getResource(files, CSS_NAME);
        assertNotNull("В задании не найден файл " + CSS_NAME, welcome);
    }
}
