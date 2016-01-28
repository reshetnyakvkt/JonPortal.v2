package ua.com.jon.common.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.common.domain.User;
import ua.com.jon.common.dto.GraduateResultDTO;
import ua.com.jon.common.dto.GroupDTO;
import ua.com.jon.common.dto.TaskDTO;
import ua.com.jon.common.service.RestService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 20.03.15
 */
@Controller
public class IDERestController {
    private static Logger log = Logger.getLogger(TasksRestController.class);

    @Autowired
    private TasksService taskService;

    @RequestMapping(value = "/user/groups", method = RequestMethod.GET)
    public @ResponseBody List<SprintDTO> getTask(ModelMap model) {
        log.info("/user/groups");
        List<ua.com.jon.cabinet.shared.GroupDTO> groups = taskService.getUserGroups();
        if (groups.isEmpty()) {
            // TODO
        }
        // TODO remove

        Long groupId = groups.get(0).getId();
        List<SprintDTO> sprints = taskService.getSprints(groupId);

        for (SprintDTO sprint : sprints) {
            for (ua.com.jon.cabinet.shared.TaskDTO taskDTO : sprint.getTasks()) {
                taskDTO.setText(taskDTO.getText().substring(0, 10));
            }
        }
        return sprints;
    }

    @RequestMapping(value = "/user/graduate", method = RequestMethod.POST)
    public @ResponseBody GraduateResultDTO graduateTask(@RequestParam String id, @RequestParam String templateId,
                                             @RequestParam String taskCode) {
        String userName = getSpringUserName();
        log.info("/user/graduate " + userName);

        ua.com.jon.cabinet.shared.TaskDTO taskDTO = new ua.com.jon.cabinet.shared.TaskDTO(Long.parseLong(id), "", "",
                "", "", taskCode, "", "", "", Long.parseLong(templateId), "", 0L, 0d);
        String result;
        if (!userName.isEmpty() && userName.equals("anonym")) {
            result = taskService.postForTest(taskDTO, false);
        } else {
            result = taskService.postForTest(taskDTO);
        }
        int endIndex = result.indexOf('\n');
        String mark = result.substring(0, endIndex);
        String details = result.substring(endIndex);

        return new GraduateResultDTO(mark, details);
    }

    public String getSpringUserName() {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        org.springframework.security.core.userdetails.User springUser;
        if (authentication instanceof String) {
            throw new SecurityException("can't grant access to anonymous ");
        }
        springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return springUser.getUsername();
    }
}
