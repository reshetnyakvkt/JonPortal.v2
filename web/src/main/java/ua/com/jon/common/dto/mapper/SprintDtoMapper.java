package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.SprintDTO;
import ua.com.jon.common.domain.Sprint;
import ua.com.jon.common.domain.SprintType;
import ua.com.jon.common.domain.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/29/13
 */
public class SprintDtoMapper {
    public static SprintDTO domainToDto(Sprint sprint) {
        return new SprintDTO(
                sprint.getId(),
                sprint.getName(),
                sprint.getActive(),
                sprint.getType().name(),
                TaskTemplateDtoMapper.domainsToDtos(sprint.getTasks()),
                sprint.getEndDate()
        );
    }

    public static SprintDTO domainToDto(Sprint sprint, List<Task> tasks) {
        return new SprintDTO(
                sprint.getId(),
                sprint.getName(),
                sprint.getActive(),
                sprint.getType().name(),
                TaskConverter.convertToTaskTemplate(tasks),
                sprint.getEndDate()
        );
    }

    public static ua.com.jon.cabinet.shared.SprintDTO domainToDto(List<Task> tasks, Sprint sprint, Double rate) {
        return new ua.com.jon.cabinet.shared.SprintDTO(
                sprint.getId(),
                sprint.getName(),
                sprint.getActive(),
                TaskDtoMapper.domainsToDtos(tasks, rate)
        );
    }

    public static ua.com.jon.examinator.shared.SprintDTO cabinetDtoToExamine(List<Task> tasks, Sprint sprint, boolean isClearResult) {
        return new ua.com.jon.examinator.shared.SprintDTO(
                sprint.getName(),
                sprint.getActive(),
                TaskDtoMapper.domainsToExamineDtos(tasks, isClearResult),
                sprint.getType().toString()
        );
    }

    public static List<Sprint> convertSprintDtosToEntity(List<Sprint> sprintList, List<SprintDTO> sprintDtoMap) {
        List<Sprint> sprints = new ArrayList<Sprint>();
        for (SprintDTO sprintDTO : sprintDtoMap) {
            String sprintName = sprintDTO.getName();
            int index = sprintList.indexOf(new Sprint(sprintDTO.getId(), sprintDTO.getName(), SprintType.IT_CENTRE,
                    sprintDTO.getEndDate(), sprintDTO.isActive()));
            Sprint sprint;
            if(index >= 0) {
                sprint = sprintList.get(index);
                sprint.setId(sprintDTO.getId());
                sprint.setName(sprintDTO.getName());
                sprint.setActive(sprintDTO.isActive());
                sprint.setTasks(TaskTemplateDtoMapper.convertTaskDtosToEntity(sprintDTO.getTasks()));
            } else {
                sprint = new Sprint(sprintDTO.getId(), sprintName, SprintType.IT_CENTRE, sprintDTO.getEndDate(),
                        sprintDTO.isActive());
            }
            sprints.add(sprint);
        }
        return sprints;
    }
}
