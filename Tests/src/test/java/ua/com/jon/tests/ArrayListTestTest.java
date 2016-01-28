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
 * Date: 27.10.13
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class ArrayListTestTest {
    @Autowired
    private ClassProcessor classProcessor;

    @Test
    public void testInfinitLoop() throws Exception {
        // TODO : fix
        final String className = "MyArrayList";
        final String classCode = "import java.util.Arrays;\n"+
                "public class MyArrayList {" +
                        "       private Integer[] myArray;\n" +
                        "        private int realSize = 0;\n" +
                        "\n" +
                        "        public MyArrayList(int size) {\n" +
                        "            this.myArray = new Integer[size];\n" +
                        "        }\n" +
                        "\n" +
                        "        public MyArrayList() {\n" +
//                "while(true);\n"+
                        "        }\n" +
                        "\n" +
                        "        public boolean add(Integer element){\n" +

                        "            checkSize();\n" +
                        "            myArray[realSize++] = element;\n" +
                        "            return true;\n" +
                        "        }\n" +
                        "\n" +
                        "        public boolean add(int index, Integer element){\n" +
                        "            checkSize();\n" +
                        "            //myArray[realSize++] = element;\n" +
                        "            return true;\n" +
                        "        }\n" +
                        "\n" +
                        "        public Integer get(int index){\n" +
                        "            if((index < 0)||(index > realSize)){\n" +
                        "                throw new IndexOutOfBoundsException();\n" +
                        "            } else {\n" +
                        "                return myArray[index];\n" +
                        "            }\n" +
                        "        }\n" +
                        "\n" +
                        "        public void checkSize(){\n" +
                                "while(true);\n"+
//                        "            if(realSize == myArray.length){\n" +
//                        "                myArray = Arrays.copyOf(myArray, myArray.length + 16);\n" +
//                        "            }\n" +
                        "        }\n" +
                        "\n" +
                        "        public int size(){\n" +
                        "            return realSize;\n" +
                        "        }\n" +
                        "\n" +
                        "        public Integer remove(int index){\n" +
                        "            if(index >= myArray.length){\n" +
                        "                return null;\n" +
                        "            }\n" +
                        "            Integer removedElement = myArray[index];\n" +
                        "            myArray[index] = null;\n" +
                        "            return removedElement;\n" +
                        "        }\n" +
                        "\n" +
                        "        public Integer set(int index, Integer element){\n" +
                        "            return element;\n" +
                        "        }\n" +
                        "\n" +
                        "        public int indexOf(Integer element){\n" +
                        "            for(int i=0; i<myArray.length; i++){\n" +
                        "                if(myArray[i].equals(element)){\n" +
                        "                    return i;\n" +
                        "                }\n" +
                        "            }\n" +
                        "            return -1;\n" +
                        "        }\n" +
                        "\n" +
                        "        public boolean contains(Integer element){\n" +
                        "            for(int i=0; i<myArray.length; i++){\n" +
                        "                if(myArray[i].equals(element)){\n" +
                        "                    return true;\n" +
                        "                }\n" +
                        "            }\n" +
                        "            return false;\n" +
                        "        }\n" +
                        "\n" +
                        "        public boolean equals(MyArrayList arrayList){\n" +
                        "            if(size() != arrayList.size()) {\n" +
                        "                return false;\n" +
                        "            }\n" +
                        "            for(int i=0; i<arrayList.size(); i++){\n" +
                        "                if(!get(i).equals(arrayList.get(i))) {\n" +
                        "                   return false;\n" +
                        "                }\n" +
                        "            }\n" +
                        "            return true;\n" +
                        "        }\n" +
                        "\n" +
                        "        public String toString(){\n" +
                        "            return \"{1-2-3}\";\n" +
                        "        }\n" +
                        "\n" +
                        "        public void clear(){\n" +
                        "            myArray = new Integer[16];\n" +
                        "            realSize = 16;\n" +
                        "        }" +
                        "}";
        final String testName = "ArrayListTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("test timed out after 1000 milliseconds", resultString);
        assertEquals("40", markString);
    }

    @Test
    public void testAddIncorrect(){
        final String className = "MyArrayList";
        final String classCode ="    public class MyArrayList {\n" +

                "        public boolean add(Integer element){\n" +
                "            return true;\n" +
                "        }\n" +
                "    }";
        final String testName = "ArrayListTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("отсутствует"));
        assertEquals("10", markString);
    }

    @Test
    public void testAddSuccess(){
        final String className = "MyArrayList";
        final String classCode ="import java.util.Arrays;\n"+
                "    public class MyArrayList {\n" +

                "        private Integer[] myArray;\n" +
                "        private int realSize = 0;\n" +
                "\n" +
                "        public MyArrayList(int size) {\n" +
                "            this.myArray = new Integer[size];\n" +
                "        }\n" +
                "\n" +
                "        public MyArrayList() {\n" +
                "            this.myArray = new Integer[16];\n" +
                "        }\n" +
                "\n" +
                "        public boolean add(Integer element){\n" +
                "            checkSize();\n" +
                "            myArray[realSize++] = element;\n" +
                "            return true;\n" +
                "        }"+
                "       public void checkSize(){\n" +
                "            if(realSize == myArray.length){\n" +
                "                myArray = Arrays.copyOf(myArray, myArray.length + 16);\n" +
                "            }\n" +
                "        }"+
                "    }";
        final String testName = "ArrayListTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("отсутствует"));
        assertEquals("40", markString);
    }

    @Test
    public void testGetIncorrect(){
        final String className = "MyArrayList";
        final String classCode ="    public class MyArrayList {\n" +
                "        public Integer get(int index){\n" +
                "            return 12345;\n" +
                "        }\n" +
                "    }";
        final String testName = "ArrayListTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("отсутствует"));
        assertEquals("10", markString);
    }

    @Test
    public void testGetSuccess(){
        final String className = "MyArrayList";
        final String classCode ="import java.util.Arrays;\n"+
                "    public class MyArrayList {\n" +
                "        private Integer[] myArray;\n" +
                "        private int realSize = 0;\n" +
                "\n" +
                "        public MyArrayList(int size) {\n" +
                "            this.myArray = new Integer[size];\n" +
                "        }\n" +
                "\n" +
                "        public MyArrayList() {\n" +
                "            this.myArray = new Integer[16];\n" +
                "        }\n" +
                "\n" +
                "        public Integer get(int index){\n" +
                "            if((index < 0)||(index > realSize)){\n" +
                "                throw new IndexOutOfBoundsException();\n" +
                "            } else {\n" +
                "                return myArray[index];\n" +
                "            }\n" +
                "        }"+
                "\n" +
                "        public boolean add(Integer element){\n" +
                "            checkSize();\n" +
                "            myArray[realSize++] = element;\n" +
                "            return true;\n" +
                "        }"+
                "       public void checkSize(){\n" +
                "            if(realSize == myArray.length){\n" +
                "                myArray = Arrays.copyOf(myArray, myArray.length + 16);\n" +
                "            }\n" +
                "        }"+
                "    }";
        final String testName = "ArrayListTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertTrue(resultString.contains("отсутствует"));
        assertEquals("70", markString);
    }

    @Test
    public void testInfinitLoopInConstractor() {
        final String className = "";
        final String classCode = "public class A {" +
                "public A() {" +
                "   while(true);" +
                "}" +
                "public static void main(String[] args) {" +
                "   while(true);" +
                "}" +
                "}";
        String testName = "ArrayListTest";
        Map.Entry<String,String> processResult = classProcessor.processClass(classCode, testName, null, null);
        String resultString = processResult.getValue();
        String markString = processResult.getKey();
        assertEquals("test timed out after 1000 milliseconds", resultString);
        assertEquals("10", markString);
    }
}
