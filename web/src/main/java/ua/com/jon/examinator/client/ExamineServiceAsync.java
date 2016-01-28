package ua.com.jon.examinator.client;

import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ua.com.jon.common.domain.TaskHistory;
import ua.com.jon.examinator.shared.SprintDTO;
import ua.com.jon.examinator.shared.TaskDTO;
import ua.com.jon.examinator.shared.TaskHistoryDto;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */

public interface ExamineServiceAsync {
    void greet(String name, AsyncCallback<String> callback);
    void getUserTasks(AsyncCallback<ArrayList<TaskDTO>> async);
    void taskStatusChanged(TaskDTO dto, AsyncCallback<Void> async);
    void getSprints(AsyncCallback<ArrayList<SprintDTO>> callback);
    void postForTest(TaskDTO taskDTO, String userName, AsyncCallback<String> callback);
    void getTaskHistoryByHash(String hash, AsyncCallback<TaskHistoryDto> callback);
}

