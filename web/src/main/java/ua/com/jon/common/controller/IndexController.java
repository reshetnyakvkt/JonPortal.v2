package ua.com.jon.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index.html")
    public String mainPage(ModelMap modelMap)  {
        modelMap.put("item","item1");
        return "index";
    }

    @RequestMapping("/trainings/index.html")
    public String trainingsIndex(ModelMap modelMap)  {
        modelMap.put("item","mitem13");
        return "trainings/style";
    }

    @RequestMapping("/lessons.html")
    public String lessons(ModelMap modelMap)  {
        modelMap.put("item","item3");
        return "lessons";
    }

    @RequestMapping("/tasks.html")
    public String tasks(ModelMap modelMap)  {
        modelMap.put("item","item4");
        return "tasks";
    }

    @RequestMapping("/tasksg.html")
    public String examinator(ModelMap modelMap)  {
        modelMap.put("item","item6");
        return "tasksg";
    }

    @RequestMapping("/cabinet/index.html")
    public String cabinet(ModelMap modelMap)  {
        modelMap.put("item","item7");
        return "cabinet/index1";
    }

    @RequestMapping("/solutions.html")
    public String solutions(ModelMap modelMap)  {
        modelMap.put("item","item8");
        return "solutions";
    }

    @RequestMapping("/kursy_java")
    public String landing(ModelMap modelMap)  {
        modelMap.put("item","item9");
        return "landing/index";
    }

    @RequestMapping("/demo")
    public String demo(ModelMap modelMap)  {
        modelMap.put("item","item10");
        return "demo/index";
    }
}
