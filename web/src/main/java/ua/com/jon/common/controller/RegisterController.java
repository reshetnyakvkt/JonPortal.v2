package ua.com.jon.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.jon.common.dto.GroupDTO;
import ua.com.jon.common.service.RegisterService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 25.07.14
 */
@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @RequestMapping("/register.html")
    public String register(ModelMap modelMap)  {
        List<GroupDTO> activeGroups = registerService.getActiveGroups();
        modelMap.put("groups", activeGroups);
        return "register";
    }
}
