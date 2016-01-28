package ua.com.jon.quiz.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Reshetnyak Viktor on 21.01.2016.
 * Package quiz.controller
 */
@Controller
public class DashboardController {
    public static final Logger log = Logger.getLogger(DashboardController.class);

    public DashboardController() {
    }

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String logout(HttpServletRequest request) {

        return "/dashboard";
    }
}
