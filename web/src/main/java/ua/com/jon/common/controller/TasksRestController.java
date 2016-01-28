package ua.com.jon.common.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.common.dto.GroupDTO;
import ua.com.jon.common.dto.TaskDTO;
import ua.com.jon.common.service.RestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 8/8/13
 */
@Controller
//@RequestMapping("/tasks")
public class TasksRestController {
    private static Logger log = Logger.getLogger(TasksRestController.class);

    @Autowired
    private RestService restService;

    @Autowired
    private TasksService taskService;

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public String getTask(@PathVariable Long id, ModelMap model) {
        log.info("Id = " + id);
        model.addAttribute("id", id);
        return "rest/task";
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String getTask(ModelMap model) {
        log.info("tasks");
        return "rest/tasks";
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public String putTask(HttpServletRequest request,
                          @RequestParam(value = "id", required = false) Long id,
                          @RequestParam(value = "status", required = false) String status,
//                          @ModelAttribute TaskForTestDTO task,
                          @RequestParam(value = "result", required = false) String result,
                          ModelMap model) {
        log.info("put task, id=" + id + ", status=" + status + ", result=" + /*task.getTestResult()*/result);
/*
        if(task.getTestResult() == null) {
            task.setTestResult("null");
        }
*/
        taskService.setValidationResult(id, status, /*task.getTestResult()*/result);
        model.addAttribute("status", "true");
        return "rest/status";
    }

    @RequestMapping(value = "/tasks/{id}/{status}", method = RequestMethod.POST)
    public String postTask(@PathVariable Long id, @PathVariable String status, ModelMap model) {
        log.info("tasks");
        model.addAttribute("status", "true");
        return "rest/status";
    }

    @RequestMapping(value = "/group/{groupName}", method = RequestMethod.GET)
    public String getTask(@PathVariable String groupName, ModelMap model) {
        log.info("Group = " + groupName);
        GroupDTO groupDTO = restService.getGroupDtoWithTasks(groupName);
        List<ua.com.jon.common.dto.TaskDTO> tasks = groupDTO.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setTemplateDesk("");
        }
        model.addAttribute("group", groupDTO);
        return "rest/tasks";
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public String getActiveGroupsWithTestTasks(ModelMap model) {
        log.debug("-- get active groups ");
//        List<GroupDTO> groupDTOs = restService.getActiveGroupsDtoWithTasks();
        List<GroupDTO> groupDTOs = restService.getActiveGroups();
        for (GroupDTO groupDTO : groupDTOs) {
            List<ua.com.jon.common.dto.TaskDTO> groupTasks = groupDTO.getTasks();
            for (int i = 0; i < groupTasks.size(); i++) {
                if (!groupTasks.get(i).getStatus().equals("TEST")) {
                    groupTasks.remove(i--);
                } else {
                    groupTasks.get(i).setTemplateDesk("");
                }
            }
        }
        model.addAttribute("groups", groupDTOs);
        return "rest/groups";
    }
}
