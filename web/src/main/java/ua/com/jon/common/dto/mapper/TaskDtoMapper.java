package ua.com.jon.common.dto.mapper;

import ua.com.jon.cabinet.shared.TaskDTO;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.domain.TaskTemplate;
import ua.com.jon.common.domain.TaskType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/25/13
 */
public class TaskDtoMapper {
    public static TaskDTO domainToDto(Task task, Double rate) {

        TaskTemplate taskTemplate = task.getTaskTemplate();
        String taskText = "";
        String taskName = "";
        String className = "";
        Long templateId = 0L;
        String materials = "";
        TaskType taskType = TaskType.CLASS;
        Long groupId = 0L;
        if (task.getTaskTemplate() != null) {
            taskText = taskTemplate.getTaskText();
            taskName = taskTemplate.getName();
            taskType = taskTemplate.getType();
            className = taskTemplate.getClassName();
            templateId = taskTemplate.getId();
            materials = taskTemplate.getMaterials();
            groupId = task.getGroup().getId();
        }

        return new TaskDTO(
                task.getId(),
                taskText,
                taskName,
                task.getStatus().name(),
                task.getResult(),
                task.getCode(),
                taskType.name(),
                task.getUser().getLogin(),
                className,
                templateId,
                materials,
                groupId,
                rate
        );
    }

    public static ua.com.jon.admin.shared.TaskDTO domainToAdminDto(Task task) {

        TaskTemplate taskTemplate = task.getTaskTemplate();
        String taskText = "";
        String taskName = "";
        String className = "";
        Long templateId = 0L;
        String materials = "";
        TaskType taskType = TaskType.CLASS;
        if (task.getTaskTemplate() != null) {
            taskText = taskTemplate.getTaskText();
            taskName = taskTemplate.getName();
            taskType = taskTemplate.getType();
            className = taskTemplate.getSuffix();
            templateId = taskTemplate.getId();
            materials = taskTemplate.getMaterials();
        }

        return new ua.com.jon.admin.shared.TaskDTO(
                task.getId(),
                taskText,
                taskName,
                task.getStatus().name(),
                task.getResult(),
                task.getCode(),
                taskType.name(),
                task.getUser().getLogin(),
                className,
                templateId,
                materials
        );
    }

    public static List<TaskDTO> domainsToDtos(Iterable<Task> tasks, Double rate) {
        List<TaskDTO> taskDTOs = new ArrayList<TaskDTO>();
        for (Task task : tasks) {
            taskDTOs.add(domainToDto(task, rate));
        }
        return taskDTOs;
    }

    public static List<ua.com.jon.admin.shared.TaskDTO> domainsToAdminDtos(List<Task> tasks) {
        List<ua.com.jon.admin.shared.TaskDTO> taskDTOs = new ArrayList<ua.com.jon.admin.shared.TaskDTO>(tasks.size());
        for (Task task : tasks) {
            taskDTOs.add(domainToAdminDto(task));
        }
        return taskDTOs;
    }

    public static List<ua.com.jon.examinator.shared.TaskDTO> domainsToExamineDtos(List<Task> tasks, boolean isClearResult) {
        List<ua.com.jon.examinator.shared.TaskDTO> taskDTOs = new ArrayList<ua.com.jon.examinator.shared.TaskDTO>(tasks.size());
        for (Task task : tasks) {
            taskDTOs.add(domainToExamDto(task, isClearResult));
        }
        return taskDTOs;
    }

    private static ua.com.jon.examinator.shared.TaskDTO domainToExamDto(Task task, boolean isClearResult) {
        return new ua.com.jon.examinator.shared.TaskDTO(
                task.getId(),
                task.getTaskTemplate().getTaskText(),
                task.getTaskTemplate().getName(),
                task.getStatus().name(),
                isClearResult ? "" : task.getResult(),
                task.getCode(),
                task.getTaskTemplate().getType().name(),
                task.getUser().getLogin(),
                task.getTaskTemplate().getSuffix(),
                task.getTaskTemplate().getTestName(),
                task.getTaskTemplate().getId(),
                task.getTaskTemplate().getMaterials(),
                task.getGroup().getId(),
                0.0
        );
    }
}
