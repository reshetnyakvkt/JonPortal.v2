package ua.com.jon.cabinet.server;

import org.springframework.web.context.request.async.DeferredResult;
import ua.com.jon.cabinet.shared.GroupDTO;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 12.06.15
 */
public interface ExtTasksService {
    String dispatchTaskChecking(TaskDTO dto, DeferredResult<String> result);
    String taskStatusChanged(TaskDTO dto, DeferredResult<String> res);

    double getSprintRate(Long groupId, Long templateId);

    double getCourseRate(Long groupId);

    String getSpringUserName();

    List<GroupDTO> getUserGroups();

    ArrayList<SprintDTO> getSprints(Long id);
}
