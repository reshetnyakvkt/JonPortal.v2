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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 Написать собственную реализацию динамического массива MyArrayList,
 содержащего целые числа.
 Сделать параметризированную релизацию списка, параметр E.
 Реализовать в списке интерфейсы Iterable.
 Реализовать следующие методы
 - void add(E value)
 - int get(int index)
 - boolean set(int index, E value)
 - boolean add(int index, E value)
 - int indexOf(E value)
 - int size()
 - E remove(int index)
 - Iterator<E> iterator()
 */
@Unit(testName = "P0ArrayListGenericTest", value = "weekend1.task1")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P0ArrayListGenericTest extends BaseTest {
    public static final int MAX_VALUE = 10;
    public static final int MIN_VALUE = 1;

//    import java.util.Arrays;
//    import java.util.Iterator;
    public class MyArrayList<E> implements Iterable {
        private E[] myArray;
        private int realSize = 0;

        public MyArrayList(int size) {
            this.myArray = (E[]) new Object[size];
        }

        public MyArrayList() {
            this.myArray = (E[]) new Object[16];
        }

        public void add(E element) {
            checkSize();
            myArray[realSize++] = element;
//        realSize++;
        }

        public boolean add(int index, E element) {
            if ((index < 0) || (index > realSize)) {
                return false;
            } else {
                resize(index, index + 1);
                myArray[index] = element;
                realSize++;
            }

            return true;
        }

        public E get(int index) {
            if ((index < 0) || (index > realSize)) {
                throw new IndexOutOfBoundsException(String.valueOf(index));
            } else {
                return myArray[index];
            }
        }

        public void checkSize() {
            if (realSize == myArray.length) {
                myArray = Arrays.copyOf(myArray, myArray.length + 16);
            }
        }

        public void resize(int indexFrom, int indexTo) {
            if ((indexFrom < 0) || indexFrom > realSize - 1 || indexTo < 0 || indexTo > realSize - 1 ||
                    indexFrom > myArray.length - 1 || indexTo > myArray.length - 1) {
                return;
            } else {
                System.arraycopy(myArray, indexFrom, myArray, indexTo, myArray.length - Math.max(indexFrom, indexTo));
            }
        }

        public int size() {
            return realSize;
        }

        public E remove(int index) {
            if ((index < 0) || (index > realSize) && (index - 1 < 0) || (index - 1 > realSize)) {
                throw new IndexOutOfBoundsException(String.valueOf(index));
            }
            E removedElement = myArray[index];
            resize(index + 1, index);
            realSize--;
            return removedElement;
        }

        public boolean set(int index, E element) {
            if ((index < 0) || (index > realSize)) {
                return false;
                //throw new IndexOutOfBoundsException();
            } else {
                myArray[index] = element;
            }

            return true;
        }

        public int indexOf(E element) {
            for (int i = 0; i < myArray.length; i++) {
                if (myArray[i].equals(element)) {
                    return i;
                }
            }
            return -1;
        }

        public int parallelIndexOf(E element) {
            for (int i = 0; i < myArray.length; i++) {
                if (myArray[i].equals(element)) {
                    return i;
                }
            }
            return -1;
        }

        public boolean contains(Integer element) {
            for (int i = 0; i < myArray.length; i++) {
                if (myArray[i] == element) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(MyArrayList arrayList) {
            if (size() != arrayList.size()) {
                return false;
            }
            for (int i = 0; i < arrayList.size(); i++) {
                if (get(i) != (arrayList.get(i))) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return Arrays.toString(myArray);
        }

        public void clear() {
            myArray = (E[]) new Object[16];
            realSize = 16;
        }

        @Override
        public Iterator iterator() {
            return new Iterator() {
                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public Object next() {
                    return null;
                }

                @Override
                public void remove() {

                }
            };
        }
    }
    class MyArrayListTest {}


    private static final String UNIT_NAME = "MyArrayList";
    private static final String TEST_NAME = "MyArrayListTest";
    private static final String ADD_METHOD_NAME = "add";
    private static final String ADD2_METHOD_NAME = "add";
    private static final String GET_METHOD_NAME = "get";
    private static final String SET_METHOD_NAME = "set";
    private static final String INDEX_OF_METHOD_NAME = "indexOf";
    private static final String SIZE_METHOD_NAME = "size";
    private static final String REMOVE_METHOD_NAME = "remove";
    private static final String ITERATOR_METHOD_NAME = "iterator";

    @UnitCode
    protected static Map<String, String> codes;
    @UnitClass
    protected static Class[] unitClasses;
    @UnitName
    protected static String[] unitNames;
    @Unit
    protected static String unitJarClasspath;
    @Troubles
    private static List<String> troubles;

    protected Object instance;
    protected Method addMethod;
    protected Method add2Method;
    protected Method getMethod;
    protected Method setMethod;
    protected Method indexOfMethod;
    protected Method sizeMethod;
    protected Method removeMethod;
    protected Method iteratorMethod;

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
        assertTrue("В задании должен не более 3х классов", unitClasses.length <= 3);

        Class testClass = getUnitClass(unitClasses, TEST_NAME);
        assertNotNull("В задании не найден класс теста " + TEST_NAME, testClass);

        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        assertNotNull("В задании не найден класс " + UNIT_NAME, unitClass);
        CodeValidator.checkCode(codes.get(unitClass.getName()));
        StyleChecker.checkStyle(codes, troubles);

        instance = instanciate(unitClass);
        ReflectionUtil.checkHasParent(unitClass, "Iterable");
        ReflectionUtil.checkHasGeneric(unitClass, "E");
        ReflectionUtil.checkMethod(unitClass, ADD_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        ReflectionUtil.checkMethod(unitClass, ADD2_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class, Object.class);
        ReflectionUtil.checkMethod(unitClass, GET_METHOD_NAME, Object.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        ReflectionUtil.checkMethod(unitClass, SET_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class, Object.class);
        ReflectionUtil.checkMethod(unitClass, INDEX_OF_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        ReflectionUtil.checkMethod(unitClass, SIZE_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        ReflectionUtil.checkMethod(unitClass, REMOVE_METHOD_NAME, Object.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        ReflectionUtil.checkMethod(unitClass, ITERATOR_METHOD_NAME, Iterator.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
    }

    @Test(timeout = 1100)
    public void testAdd() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        instance = instanciate(unitClass);
        addMethod = ReflectionUtil.checkMethod(unitClass, ADD_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        if (instance == null || addMethod == null) {
            fail();
        }
        Integer expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;

        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        Integer actualElement = getElementFromList(0);
        assertNotNull("После добавления первого элемента " + expectedElement + " в списке нет элемента с индексом 0", actualElement);
        assertTrue("Метод add работает не верно, после добавления первого элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement.equals(actualElement));

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        actualElement = getElementFromList(1);
        assertNotNull("После добавления второго элемента " + expectedElement + " в списке нет элемента с индексом 1", actualElement);
        assertTrue("Метод add работает не верно, после добавления второго элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement.equals(actualElement));
    }

    @Test(timeout = 1100)
    public void testAdd2() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        instance = instanciate(unitClass);
        add2Method = ReflectionUtil.checkMethod(unitClass, ADD2_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class, Object.class);
        if (instance == null || add2Method == null) {
            fail();
        }
        int expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, add2Method, 0, expectedElement);
        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, add2Method, 0, expectedElement);
        Integer actualElement = getElementFromList(0);
        assertNotNull("После вставки двух элементов " + expectedElement + " в начало, в списке нет элемента с индексом 0", actualElement);
        assertTrue("Метод add(int, int) работает не верно, после вставки элемента " + expectedElement + " в начало, список содержит " +
                actualElement, expectedElement == actualElement);

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, add2Method, 1, expectedElement);
        actualElement = getElementFromList(1);
        assertNotNull("После вставки третьего элемента " + expectedElement + " в 1, в списке нет элемента с индексом 1", actualElement);
        assertTrue("Метод add(int, int) работает не верно, после вставки второго элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement == actualElement);
    }

    private Integer getElementFromList(int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<Field> array = new ArrayList<Field>();
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        Field[] fields = unitClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == Object[].class) {
                field.setAccessible(true);
                array.add(field);
            }
        }
        if (array.size() == 1) {
            try {
                Object[] elements = (Object[]) array.get(0).get(instance);
                if (elements.length > 0) {
                    return (Integer) elements[index];
                }
                return null;
            } catch (IllegalAccessException e) {
                fail("Ошибка тестирования!, массив с элементами недоступен");
            }
        } else if (!array.isEmpty()) {
            return invokeGet();
        }
        fail("Список должен содержать массив типа E");
        return null;
    }

    public int invokeGet() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (Integer) getMethod.invoke(instance, 0);
    }

    @Test(timeout = 1100)
    public void testGet() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        instance = instanciate(unitClass);
        getMethod = ReflectionUtil.checkMethod(unitClass, GET_METHOD_NAME, Object.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        addMethod = ReflectionUtil.checkMethod(unitClass, ADD_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        if (instance == null || getMethod == null) {
            fail();
        }
        int expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        int actualElement = (Integer) ReflectionUtil.invokeMethod(instance, getMethod, 0);
        assertTrue("Метод get работает не верно, после добавления элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement == actualElement);

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        actualElement = (Integer) ReflectionUtil.invokeMethod(instance, getMethod, 1);
        assertTrue("Метод get работает не верно, после добавления второго элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement == actualElement);
    }

    @Test(timeout = 1100)
    public void testSet() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        instance = instanciate(unitClass);
        setMethod = ReflectionUtil.checkMethod(unitClass, SET_METHOD_NAME, boolean.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class, Object.class);
        addMethod = ReflectionUtil.checkMethod(unitClass, ADD_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        getMethod = ReflectionUtil.checkMethod(unitClass, GET_METHOD_NAME, Object.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        if (instance == null || setMethod == null) {
            fail();
        }
        int expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, setMethod, 0, expectedElement);
        int actualElement = (Integer) ReflectionUtil.invokeMethod(instance, getMethod, 0);
        assertTrue("Метод set работает не верно, после замены первого элемента на " + expectedElement + ", список содержит " +
                actualElement, expectedElement == actualElement);

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, setMethod, 1, expectedElement);
        actualElement = (Integer) ReflectionUtil.invokeMethod(instance, getMethod, 1);
        assertTrue("Метод get работает не верно, после добавления второго элемента " + expectedElement + ", список содержит " +
                actualElement, expectedElement == actualElement);
    }

    @Test(timeout = 1100)
    public void testIndexOf() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        instance = instanciate(unitClass);
        indexOfMethod = ReflectionUtil.checkMethod(unitClass, INDEX_OF_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        addMethod = ReflectionUtil.checkMethod(unitClass, ADD_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        if (instance == null || indexOfMethod == null) {
            fail();
        }
        int expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        int actualIndex = (Integer) ReflectionUtil.invokeMethod(instance, indexOfMethod, expectedElement);
        assertTrue("Метод indexOf работает не верно, при поиске первого элемента " + expectedElement + ", индекс найденного элемента " +
                actualIndex, 0 == actualIndex);

        int newExpected = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        while (newExpected == expectedElement) {
            newExpected = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        }
        ReflectionUtil.invokeMethod(instance, addMethod, newExpected);
        actualIndex = (Integer) ReflectionUtil.invokeMethod(instance, indexOfMethod, newExpected);

        assertTrue("Метод indexOf работает не верно, при поиске второго элемента " + expectedElement + ", индекс найденного элемента " +
                actualIndex, 1 == actualIndex);
    }

    @Test(timeout = 1100)
    public void testSize() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        instance = instanciate(unitClass);
        sizeMethod = ReflectionUtil.checkMethod(unitClass, SIZE_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        addMethod = ReflectionUtil.checkMethod(unitClass, ADD_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        if (instance == null || sizeMethod == null) {
            fail();
        }
        Integer expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        Integer actualSize = (Integer) ReflectionUtil.invokeMethod(instance, sizeMethod);
        assertTrue("Метод size работает не верно, после дабавления одного элемента " + expectedElement + ", размер списка равен " +
                actualSize, 1 == actualSize);

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        actualSize = (Integer) ReflectionUtil.invokeMethod(instance, sizeMethod);
        assertTrue("Метод size работает не верно, после дабавления второго элемента " + expectedElement + ", размер списка равен " +
                actualSize, 2 == actualSize);
    }

    @Test(timeout = 1100)
    public void testRemove() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        instance = instanciate(unitClass);
        removeMethod = ReflectionUtil.checkMethod(unitClass, REMOVE_METHOD_NAME, Object.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, int.class);
        addMethod = ReflectionUtil.checkMethod(unitClass, ADD_METHOD_NAME, void.class,
                new MethodModifier[]{MethodModifier.PUBLIC}, Object.class);
        sizeMethod = ReflectionUtil.checkMethod(unitClass, SIZE_METHOD_NAME, int.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        if (instance == null || removeMethod == null) {
            fail();
        }
        int expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        int removedElement = (Integer) ReflectionUtil.invokeMethod(instance, removeMethod, 0);
        int actualSize = (Integer) ReflectionUtil.invokeMethod(instance, sizeMethod);
        assertTrue("Метод remove работает не верно, при удалении первого элемента " + expectedElement + ", удаляемый элемент должен быть " +
                removedElement, expectedElement == removedElement);
        assertTrue("Метод remove работает не верно, после удалении первого элемента " + expectedElement + ", размер списка равен " +
                actualSize, 0 == actualSize);

        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        expectedElement = rnd.nextInt(MAX_VALUE) + MIN_VALUE;
        ReflectionUtil.invokeMethod(instance, addMethod, expectedElement);
        removedElement = (Integer) ReflectionUtil.invokeMethod(instance, removeMethod, 0);
        Integer actualElement = getElementFromList(0);
        assertNotNull("После добавления первого элемента " + expectedElement + " в списке нет элемента с индексом 0", actualElement);
        actualSize = (Integer) ReflectionUtil.invokeMethod(instance, sizeMethod);
        assertTrue("Метод remove работает не верно, при удалении первого элемента из двух" + expectedElement + ", удаляемый элемент должен быть " +
                removedElement, expectedElement == actualElement);
        assertTrue("Метод remove работает не верно, после удалении первого элемента из двух" + expectedElement + ", размер списка равен " +
                actualSize, 1 == actualSize);
    }

    @Test(timeout = 1100)
    public void testIterator() throws Throwable {
        Class unitClass = getUnitClass(unitClasses, UNIT_NAME);
        instance = instanciate(unitClass);
        iteratorMethod = ReflectionUtil.checkMethod(unitClass, ITERATOR_METHOD_NAME, Iterator.class,
                new MethodModifier[]{MethodModifier.PUBLIC});
        if (instance == null || iteratorMethod == null) {
            fail();
        }
        Object iterator = ReflectionUtil.invokeMethod(instance, iteratorMethod);
        if (!(iterator instanceof Iterator)) {
            fail("Метод itearator() должен возрващать значение типа Iterator");
        }
    }
}
