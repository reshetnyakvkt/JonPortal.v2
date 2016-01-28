package ua.com.jon.common.dto.mapper;

import ua.com.jon.common.domain.TaskHistory;
import ua.com.jon.examinator.shared.TaskHistoryDto;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 13.06.14
 */
public class TaskHistoryDtoMapper {
    public static TaskHistoryDto domainToDto(TaskHistory task) {
        return new TaskHistoryDto(
                task.getId(),
                task.getCode(),
                task.getTaskTemplate().getName(),
                task.getUserName(),
                task.getDate(),
                task.getResult(),
                task.getHash()
        );
    }
}
