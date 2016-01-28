package ua.com.jon.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 12.01.13
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class UserController {
    public static final String REQUEST_METHOD_VIEW_NAME = "userView";

    @RequestMapping(value = "/requestmethod", method = RequestMethod.GET)
    public String requestMethodGet(Model model) {
        model.addAttribute("method", "get");
        return REQUEST_METHOD_VIEW_NAME;
    }

    @RequestMapping(value = "/requestmethod", method = RequestMethod.POST)
    public String requestMethodPost(Model model) {
        model.addAttribute("method", "post");
        return REQUEST_METHOD_VIEW_NAME;
    }



}
