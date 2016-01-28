package ua.com.jon.tasks.validator.codeValidator;


import org.springframework.stereotype.Component;
import ua.com.jon.tasks.validator.codeValidator.jsonObjects.JSONTaskResponse;
import ua.com.jon.tasks.validator.codeValidator.templates.CodeValidator;

@Component
public class UniqueArrayListValidator extends CodeValidator {

    public UniqueArrayListValidator() {

        setTaskName("UniqueArrayList");
        setPseudoName("Динамический массив с уникальными значениями");
        setTaskDescription("Необходимо написать класс, у которого есть метод, принимающий " +
                "ссылку на ArrayList и целое число n. Метод должен заполнить переданный ArrayList" +
                " n колличеством уникальных целых чисел");


        String codePattern0 = "// insert your code here\n" +
                "import java.util.List;\n"+
                "public class UniqueArrayList {\n" +
                "    // this method receives ArrayList and integer n\n" +
                "    // and fill up ArrayList with n unique integer elements\n"+
                "    // don't change the name of this method!!!\n"+
                "    public void fillUpByUniqueVal(List ArrayList, int n) {\n" +
                "    }\n" +
                "}";
        addTaskClass("UniqueArrayList.java",codePattern0);

        String codePattern1="//\n"+
                "public class MyClass {\n"+
                "}";
        addTaskClass("MyClass.java",codePattern1);

    }

    @Override
    public void testClass(Class clazz, JSONTaskResponse jsonResponse) throws Exception {
        jsonResponse.addTestMessage(true,"Only stub here");
    }
}
