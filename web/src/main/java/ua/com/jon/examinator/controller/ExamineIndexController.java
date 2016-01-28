package ua.com.jon.examinator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/5/13
 */

@Controller
public class ExamineIndexController {
                                        //
    @RequestMapping(value = "/examine/index.html", method = RequestMethod.GET)
    public String indexGWT(Model model) {
        return "examine/index";
    }

}
