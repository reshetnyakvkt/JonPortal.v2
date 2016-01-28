package ua.com.jon.tasks.validator.controller;


//import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.com.jon.tasks.validator.codeValidator.SummatorValidator;
import ua.com.jon.tasks.validator.codeValidator.UniqueArrayListValidator;
import ua.com.jon.tasks.validator.codeValidator.jsonObjects.JSONTaskRequest;
import ua.com.jon.tasks.validator.codeValidator.templates.CodeValidator;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;

@Controller
@RequestMapping("/codeValidator")
public class CodeValidatorController extends HttpServlet {

    @Autowired
    private SummatorValidator summatorValidator;

    @Autowired
    private UniqueArrayListValidator uniqueArrayListValidator;


    private ArrayList<CodeValidator> codeValidators;


    private void fillUpModelForIndex(ModelMap modelMap) {
        codeValidators = new ArrayList<CodeValidator>();
        codeValidators.add(summatorValidator);
        codeValidators.add(uniqueArrayListValidator);
        modelMap.put("codeValidators", codeValidators);
        modelMap.put("item", "item4");
    }


    @RequestMapping("")
    public String byDefault(ModelMap modelMap) {
        fillUpModelForIndex(modelMap);
        return "codeValidator/index";
    }

    @RequestMapping("/index")
    public String index(ModelMap modelMap) {
        fillUpModelForIndex(modelMap);
        return "/codeValidator/index";

    }

    @RequestMapping("/task")
    public String task(@RequestParam String taskName, ModelMap modelMap) {

        // переберем все задания и передадим модели нужное
        for (CodeValidator codeValidator : codeValidators) {
            if (taskName.equals(codeValidator.getTaskName())) {
                modelMap.put("codeValidator", codeValidator);
                modelMap.put("item", "item4");
                return "codeValidator/task";
            }
        }

        fillUpModelForIndex(modelMap);
        return "/codeValidator/index"; // если нет такого задания

    }

    @RequestMapping(value = "/task/validate", method = RequestMethod.POST)
    public
    @ResponseBody
    String validate(@RequestBody String data) {

/*
        Gson gson=new Gson();

        JSONTaskRequest jsonTaskRequest=gson.fromJson(data,JSONTaskRequest.class);

        // переберем все задания и проведем валидацию необходимым классом
        for (CodeValidator codeValidator : codeValidators) {
            if (jsonTaskRequest.getTaskName().equals(codeValidator.getTaskName())) {
                return codeValidator.validateCode(jsonTaskRequest.getTaskClasses(),codeValidator.getTaskName());
            }
        }
*/
        return null; // если нет такого задания

    }

}
