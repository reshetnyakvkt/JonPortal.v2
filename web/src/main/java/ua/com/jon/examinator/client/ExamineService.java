package ua.com.jon.examinator.client;

import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ua.com.jon.examinator.shared.SprintDTO;
import ua.com.jon.examinator.shared.TaskDTO;
import ua.com.jon.examinator.shared.TaskHistoryDto;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@RemoteServiceRelativePath("/service/examineService")
public interface ExamineService extends RemoteService {
    String greet(String name);
    ArrayList<TaskDTO> getUserTasks();
    void taskStatusChanged(TaskDTO dto);
    ArrayList<SprintDTO> getSprints();
    String postForTest(TaskDTO taskDTO, String userName);
    TaskHistoryDto getTaskHistoryByHash(String hash);
}
