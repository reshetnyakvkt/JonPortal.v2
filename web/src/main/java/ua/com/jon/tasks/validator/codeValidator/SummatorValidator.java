package ua.com.jon.tasks.validator.codeValidator;


import org.springframework.stereotype.Component;
import ua.com.jon.tasks.validator.codeValidator.jsonObjects.JSONTaskResponse;
import ua.com.jon.tasks.validator.codeValidator.templates.CodeValidator;

@Component
public class SummatorValidator extends CodeValidator {


    private int getRandomInt(int minVal, int maxVal) {

        if (minVal > maxVal) {
            minVal = minVal ^ maxVal;
            maxVal = maxVal ^ minVal;
            minVal = minVal ^ maxVal;
        }
        int res = (int) (Math.random() * (double) (maxVal - minVal));
        res += minVal;
        return res;

    }


    public SummatorValidator() {

        setTaskName("Summator");
        setPseudoName("Сумматор целых чисел");
        setTaskDescription("Необходимо написать класс, у которого есть метод, принимающий " +
                "два целых числа и возвращающий их сумму. Впишите код в поле редактирования");

        String codePattern0 = "// this is simle class with one method\n" +
                "public class Summator {\n" +
                "    // this method returns sum of accepted int values\n" +
                "    // don't change the name of this method!!!\n" +
                "    public int sum(int a, int b) {\n" +
                "        return 0;\n" +
                "    }\n" +
                "}";

        addTaskClass("Summator.java", codePattern0);

    }

    @Override
    public void testClass(Class clazz, JSONTaskResponse jsonResponse) throws Exception {

        int a, b, realSum, testSum;

        // тесты
        for (int i = 0; i <= 10; i++) {
            a = getRandomInt(-100, 100);
            b = getRandomInt(-100, 100);
            String strA = String.valueOf(a);
            if (a < 0) strA = "(" + strA + ")";
            String strB = String.valueOf(b);
            if (b < 0) strB = "(" + strB + ")";
            realSum = a + b;
            testSum = (Integer) clazz.getMethod("sum", int.class, int.class).invoke(clazz.newInstance(), a, b);
            String strValidation = strA + "+" + strB + ":expected " + realSum + ", received " + testSum;
            jsonResponse.addTestMessage(realSum == testSum,strValidation);
        }

    }


}
