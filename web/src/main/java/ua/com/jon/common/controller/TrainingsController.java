package ua.com.jon.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/20/13
 */
@Controller
public class TrainingsController {
    @RequestMapping("/trainings/list.html")
    public String trainingsOracle(ModelMap modelMap)  {
        modelMap.put("item","mitem1");
        return "trainings/list";
    }

    @RequestMapping("/trainings/scheduling.html")
    public String trainingsSched(ModelMap modelMap)  {
        modelMap.put("item","mitem2");
        return "trainings/scheduling";
    }

    @RequestMapping("/trainings/reviews.html")
    public String trainingsReviews(ModelMap modelMap)  {
        modelMap.put("item","mitem5");
        return "trainings/reviews";
    }
/*
    @RequestMapping("/trainings/rules.html")
    public String trainingsRules(ModelMap modelMap)  {
        modelMap.put("item","mitem3");
        return "trainings/rules";
    }*/

    @RequestMapping("/trainings/assistant.html")
    public String assistantRules(ModelMap modelMap)  {
        modelMap.put("item","mitem4");
        return "trainings/assistant";
    }

    @RequestMapping("/trainings/newcomer.html")
    public String newcomerRules(ModelMap modelMap)  {
        modelMap.put("item","mitem6");
        return "trainings/newcomer";
    }

    @RequestMapping("/trainings/registration.html")
    public String registration(ModelMap modelMap)  {
        modelMap.put("item","mitem7");
        return "trainings/registration";
    }

    @RequestMapping("/trainings/practicum.html")
    public String practicum(ModelMap modelMap)  {
        modelMap.put("item","mitem8");
        return "trainings/practicum";
    }

    @RequestMapping("/trainings/jon.html")
    public String jon(ModelMap modelMap)  {
        modelMap.put("item","mitem9");
        return "trainings/jon";
    }

    @RequestMapping("/trainings/nedocode.html")
    public String nedocode(ModelMap modelMap)  {
        modelMap.put("item","mitem10");
        return "trainings/nedocode";
    }

    @RequestMapping("/trainings/slang.html")
    public String slang(ModelMap modelMap)  {
        modelMap.put("item","mitem11");
        return "trainings/slang";
    }

    @RequestMapping("/trainings/folc.html")
    public String folc(ModelMap modelMap)  {
        modelMap.put("item","mitem12");
        return "trainings/folc";
    }

    @RequestMapping("/trainings/style.html")
    public String style(ModelMap modelMap)  {
        modelMap.put("item","mitem13");
        return "trainings/style";
    }

    @RequestMapping("/trainings/styleChecker.html")
    public String styleChecker(ModelMap modelMap)  {
        modelMap.put("item","mitem14");
        return "trainings/styleChecker";
    }
}
