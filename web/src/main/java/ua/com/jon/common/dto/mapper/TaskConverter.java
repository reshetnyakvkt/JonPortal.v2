package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.TaskTemplateDTO;
import ua.com.jon.cabinet.shared.TaskDTO;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.domain.TaskTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/29/13
 */
public class TaskConverter {
    public static List<TaskTemplateDTO> convertToTaskTemplate(List<Task> tasks) {
        List<TaskTemplateDTO> templates = new ArrayList<TaskTemplateDTO>();
        for (Task task : tasks) {
            templates.add(TaskTemplateDtoMapper.domainToDto(task.getTaskTemplate()));
        }
        return templates;
    }


}
