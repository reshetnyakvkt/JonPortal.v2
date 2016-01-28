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

import static com.jon.tron.service.reflect.ReflectionUtil.checkMethod;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Добавить в приложение управления курсами сущность Курс (название, темы, дата начала обучения, группы)
 Добавить следующие возможности в виде консольного меню, а так же методы в класс Courses:
 - Добавить курс, void addCourse(Course course)
 - Добавить Группу в Курс по имени, void addGroupToCourse(String groupName, String courseName) throws NoSuchGroupException, NoSuchCourseException
 - Задать темы курса, void setThemes(String courseName, List<String> themes) throws NoSuchCourseException
 - Удалить из Курса группы, в которых учяться указанные студенты (по имени), void removeGroupsFromCourseByStudents(String courseName, List<String> students) throws NoSuchCourseException
 - Вывести все курсы на экран, void printCourses()

 Классы задания hw6.courses.Course
 hw6.courses.Group,
 hw6.courses.Student
 hw6.courses.Courses
 hw6.courses.NoSuchGroupException
 hw6.courses.NoSuchCourseException
 hw6.courses.NoSuchStudentException
 hw6.courses.GroupExistsException
 */
@Unit(testName = "B5CoursesCourseTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class B5CoursesCourseTest extends BaseTest {
    public class Courses {
        public Courses() {}
        public void setGroupName() throws NoSuchGroupException {}
        public void addStudent(Student student) {}
        public void addCourse(Course student) {}
        public void addGroup(String groupName) throws GroupExistsException {}
        public void addGroupToCourse(String groupName, String courseName) throws NoSuchGroupException, NoSuchCourseException {}
        public void printGroupInfo() throws NoSuchGroupException {}
        public void addStudentToGroup(String studentSName, String groupName) throws NoSuchStudentException {}
        public void removeStudentFromGroup(String studentSName, String groupName) throws NoSuchStudentException {}
        public void setThemes(String courseName, List<String> themes) throws NoSuchCourseException {}
        public void removeGroupsFromCourseByStudents(String courseName, List<String> students) throws NoSuchCourseException {}
        public void cloneGroup(String groupName) throws NoSuchGroupException {}
        public void serialize() {}
        public void deserialize() {}
        public void printCourses() {}
    }
    class Student {}
    class Course {}
    class NoSuchGroupException extends Exception {}
    class NoSuchStudentException extends Exception {}
    class GroupExistsException extends Exception {}
    class NoSuchCourseException extends Exception {}

    private static final String COURSES_NAME = "Courses";
    private static final String STUDENT_NAME = "Student";
    private static final String GROUP_NAME = "Group";
    private static final String COURSE_NAME = "Course";
    private static final String GROUP_EX_NAME = "NoSuchGroupException";
    private static final String STUDENT_EX_NAME = "NoSuchStudentException";
    private static final String GROUP_EX_EX_NAME = "GroupExistsException";
    private static final String COURSE_EX_NAME = "NoSuchCourseException";
    private static final String SET_GROUP_METHOD_NAME = "setGroupName";
    private static final String ADD_STUDENT_COURSE_METHOD_NAME = "addStudent";
    private static final String ADD_GROUP_METHOD_NAME = "addGroup";
    private static final String PRINT_GROUP_METHOD_NAME = "printGroupInfo";
    private static final String ADD_STUDENT_GROUP_METHOD_NAME = "addStudentToGroup";
    private static final String REMOVE_STUDENT_METHOD_NAME = "removeStudentFromGroup";
    private static final String CLONE_METHOD_NAME = "cloneGroup";
    private static final String SERIALIZE_METHOD_NAME = "serialize";
    private static final String DESERIALIZE_METHOD_NAME = "deserialize";
    private static final String ADD_COURSE_METHOD_NAME = "addCourse";
    private static final String ADD_GROUP_TO_COURSE_METHOD_NAME = "addGroupToCourse";
    private static final String SET_THEMES_METHOD_NAME = "setThemes";
    private static final String REMOVE_GROUPS_METHOD_NAME = "removeGroupsFromCourseByStudents";
    private static final String PRINT_COURSES_METHOD_NAME = "printCourses";


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
        CodeValidator.checkCode(codes.entrySet().iterator().next().getValue());
        StyleChecker.checkStyle(codes, troubles);
        Class unitClass = getUnitClass(unitClasses, COURSES_NAME);
        assertNotNull("В задании не найден класс " + COURSES_NAME, unitClass);
        ReflectionUtil.checkDefaultConstructor(unitClass);
        unitClass = getUnitClass(unitClasses, COURSE_NAME);
        assertNotNull("В задании не найден класс " + COURSE_NAME, unitClass);
        ReflectionUtil.checkDefaultConstructor(unitClass);

        unitClass = getUnitClass(unitClasses, STUDENT_NAME);
        assertNotNull("В задании не найден класс " + STUDENT_NAME, unitClass);
        ReflectionUtil.checkDefaultConstructor(unitClass);
        unitClass = getUnitClass(unitClasses, GROUP_NAME);
        assertNotNull("В задании не найден класс " + GROUP_NAME, unitClass);
        ReflectionUtil.checkDefaultConstructor(unitClass);
        unitClass = getUnitClass(unitClasses, GROUP_EX_NAME);
        assertNotNull("В задании не найден класс " + GROUP_EX_NAME, unitClass);
        unitClass = getUnitClass(unitClasses, GROUP_EX_EX_NAME);
        assertNotNull("В задании не найден класс " + GROUP_EX_EX_NAME, unitClass);
        unitClass = getUnitClass(unitClasses, STUDENT_EX_NAME);
        assertNotNull("В задании не найден класс " + STUDENT_EX_NAME, unitClass);
        unitClass = getUnitClass(unitClasses, COURSE_EX_NAME);
        assertNotNull("В задании не найден класс " + COURSE_EX_NAME, unitClass);

//        instance = instanciate(unitClasses[0]);
        addMethod = checkMethod(unitClass, SET_GROUP_METHOD_NAME, "NoSuchGroupException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = checkMethod(unitClass, ADD_STUDENT_COURSE_METHOD_NAME, "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Student");
        addMethod = checkMethod(unitClass, ADD_GROUP_METHOD_NAME, "GroupExistsException", String.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = checkMethod(unitClass, PRINT_GROUP_METHOD_NAME, "NoSuchCourseException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, List.class);
        addMethod = checkMethod(unitClass, ADD_STUDENT_GROUP_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, String.class);
        addMethod = checkMethod(unitClass, REMOVE_STUDENT_METHOD_NAME, "NoSuchStudentException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, String.class);
        addMethod = checkMethod(unitClass, CLONE_METHOD_NAME, "NoSuchGroupException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class);
        addMethod = checkMethod(unitClass, SERIALIZE_METHOD_NAME, "NoSuchCourseException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, List.class);
        addMethod = checkMethod(unitClass, DESERIALIZE_METHOD_NAME, "NoSuchCourseException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, List.class);
        addMethod = checkMethod(unitClass, ADD_COURSE_METHOD_NAME, "void",
                new MethodModifier[]{MethodModifier.PUBLIC}, "Course");
        addMethod = checkMethod(unitClass, ADD_GROUP_TO_COURSE_METHOD_NAME, "NoSuchCourseException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, String.class);
        addMethod = checkMethod(unitClass, SET_THEMES_METHOD_NAME, "NoSuchCourseException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, List.class);
        addMethod = checkMethod(unitClass, REMOVE_STUDENT_METHOD_NAME, "NoSuchCourseException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, List.class);
        addMethod = checkMethod(unitClass, PRINT_COURSES_METHOD_NAME, "NoSuchCourseException", void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, String.class, List.class);


    }



}
