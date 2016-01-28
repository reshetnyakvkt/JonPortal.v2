package ua.com.jon.tests;

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

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 Написать приложение управления курсами.
 Используемые сущности: Группа (название, студенты), Студент (имя, фамилия, отчество, оценки по викендам).
 Добавить следующие возможности в виде консольного меню, а так же методы в класс Courses:
 - Задать новое имя группы (старое имя, новое имя), void setGroupName(String groupName, String newName) throws NoSuchGroupException
 - Добавить студента на курсы, void addStudent(Student student)
 - Добавить группу по имени, void addGroup(String groupName) throws GroupExistsException
 - Вывести информацию о группе, void printGroupInfo(String groupName) throws NoSuchGroupException
 - Добавить студента в группу по фамилии, void addStudentToGroup(String studentSName, String groupName) throws NoSuchStudentException
 - Удалить студента из группы по фамилии (на курсах студент остается), void removeStudentFromGroup(String studentSName, String groupName) throws NoSuchStudentException
 - Создать копию группы со студентами, void cloneGroup(String groupName, String newName) throws NoSuchGroupException
 - Сохранить информацию о курсах в файл, void serialize()
 - Прочитать информацию о курсах из файла, void deserialize()

 Классы задания hw6.courses.Group,
 hw6.courses.Student
 hw6.courses.Courses
 hw6.courses.NoSuchGroupException
 hw6.courses.NoSuchStudentException
 hw6.courses.GroupExistsException
 */
@Unit(testName = "B5CoursesTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B5CoursesTest extends BaseTest {
    public class Courses {
        public Courses() {}
        public void setGroupName(String groupName, String newName) throws NoSuchGroupException {}
        public void addStudent(Student student) {}
        public void addGroup(String groupName) throws GroupExistsException {}
        public void printGroupInfo() throws NoSuchGroupException {}
        public void addStudentToGroup(String studentSName, String groupName) throws NoSuchStudentException {}
        public void removeStudentFromGroup(String studentSName, String groupName) throws NoSuchStudentException {}
        public void cloneGroup(String groupName, String newName) throws NoSuchGroupException {}
        public void serialize() {}
        public void deserialize() {}
    }
    class Student {}
    class NoSuchGroupException extends Exception {}
    class NoSuchStudentException extends Exception {}
    class GroupExistsException extends Exception {}

    private static final String COURSES_NAME = "Courses";
    private static final String STUDENT_NAME = "Student";
    private static final String GROUP_NAME = "Group";
    private static final String GROUP_EX_NAME = "NoSuchGroupException";
    private static final String STUDENT_EX_NAME = "NoSuchStudentException";
    private static final String GROUP_EX_EX_NAME = "GroupExistsException";
    private static final String SET_GROUP_METHOD_NAME = "setGroupName";
    private static final String ADD_STUDENT_COURSE_METHOD_NAME = "addStudent";
    private static final String ADD_GROUP_METHOD_NAME = "addGroup";
    private static final String PRINT_GROUP_METHOD_NAME = "printGroupInfo";
    private static final String ADD_STUDENT_GROUP_METHOD_NAME = "addStudentToGroup";
    private static final String REMOVE_STUDENT_METHOD_NAME = "removeStudentFromGroup";
    private static final String CLONE_METHOD_NAME = "cloneGroup";
    private static final String SERIALIZE_METHOD_NAME = "serialize";
    private static final String DESERIALIZE_METHOD_NAME = "deserialize";

    @UnitCode
    private static Map<String, String> codes;
    @UnitClass
    private static Class[] unitClasses;
    @UnitName
    private static String[] unitNames;
    @Unit
    private static String unitJarClasspath;

    private static Object instance;
    private static Method method;
    @Troubles
    private static List<String> troubles;

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
        assertTrue("В задании должно быть не более 8и классов, а не " + unitClasses.length, unitClasses.length <= 8);
        CodeValidator.checkCodeFile(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        Class unitClass = unitClass = getUnitClass(unitClasses, STUDENT_NAME);
        //ReflectionUtil.checkDefaultConstructor(unitClass);

        assertNotNull("В задании не найден класс " + STUDENT_NAME, unitClass);
        //ReflectionUtil.checkDefaultConstructor(unitClass);
        unitClass = getUnitClass(unitClasses, GROUP_NAME);
        assertNotNull("В задании не найден класс " + GROUP_NAME, unitClass);
        //ReflectionUtil.checkDefaultConstructor(unitClass);
        unitClass = getUnitClass(unitClasses, GROUP_EX_NAME);
        assertNotNull("В задании не найден класс " + GROUP_EX_NAME, unitClass);
        //ReflectionUtil.checkDefaultConstructor(unitClass);
        unitClass = getUnitClass(unitClasses, GROUP_EX_EX_NAME);
        assertNotNull("В задании не найден класс " + GROUP_EX_EX_NAME, unitClass);
        //ReflectionUtil.checkDefaultConstructor(unitClass);
        unitClass = getUnitClass(unitClasses, STUDENT_EX_NAME);
        assertNotNull("В задании не найден класс " + STUDENT_EX_NAME, unitClass);
        //ReflectionUtil.checkDefaultConstructor(unitClass);
        unitClass = getUnitClass(unitClasses, COURSES_NAME);
        assertNotNull("В задании не найден класс " + COURSES_NAME, unitClass);

//        instance = instanciate(unitClasses[0]);
        method = ReflectionUtil.checkMethod(unitClass, SET_GROUP_METHOD_NAME, "NoSuchGroupException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, String.class);
        method = ReflectionUtil.checkMethod(unitClass, ADD_STUDENT_COURSE_METHOD_NAME, "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Student");
        method = ReflectionUtil.checkMethod(unitClass, ADD_GROUP_METHOD_NAME, "GroupExistsException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        method = ReflectionUtil.checkMethod(unitClass, PRINT_GROUP_METHOD_NAME, "NoSuchGroupException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        method = ReflectionUtil.checkMethod(unitClass, ADD_STUDENT_GROUP_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, String.class);
        method = ReflectionUtil.checkMethod(unitClass, REMOVE_STUDENT_METHOD_NAME, "NoSuchStudentException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, String.class);
        method = ReflectionUtil.checkMethod(unitClass, CLONE_METHOD_NAME, "NoSuchGroupException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, String.class);
        method = ReflectionUtil.checkMethod(unitClass, SERIALIZE_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        method = ReflectionUtil.checkMethod(unitClass, DESERIALIZE_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }
}
