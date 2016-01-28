package ua.com.jon.cabinet.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.springframework.web.context.request.async.DeferredResult;
import ua.com.jon.cabinet.shared.GroupDTO;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */

public interface TasksServiceAsync {
    void getUserTasks(AsyncCallback<ArrayList<TaskDTO>> async);
    void taskStatusChanged(TaskDTO dto, AsyncCallback<String> async);
    void getSprints(Long selectedGroup, AsyncCallback<ArrayList<SprintDTO>> callback);
    void postForTest(TaskDTO taskDTO, AsyncCallback<String> callback);
    void getCourseRate(Long taskTemplateId, String userName, AsyncCallback<Double> async);

    void getSpringUserName(AsyncCallback<String> userCallback);

    void getUserGroups(AsyncCallback<List<GroupDTO>> callback);

    void getSprintRate(Long groupId, Long taskTemplateId, String userName, AsyncCallback<Double> async);

    void getTasksByUserGroup(Long taskTemplateId, Long selectedGroupId, Long selectedSprintId, AsyncCallback<ArrayList<TaskDTO>> async);

    void setValidationResult(Long id, String statusStr, String result, AsyncCallback<Void> async);

    void dispatchTaskChecking(TaskDTO dto, AsyncCallback<String> async);

    void refreshTasks(List<Long> ids, AsyncCallback<List<TaskDTO>> async);

    void getGroupInfo(Long selectedGroupId, AsyncCallback<List<List<String>>> async) throws Exception;

    void postForTest(TaskDTO taskDTO, boolean isSaveNeed, AsyncCallback<String> async);

    void getCourseRate(Long selectedGroupId, AsyncCallback<Double> async);

    void getSprintRate(Long groupId, Long templateId, AsyncCallback<Double> async);
}

