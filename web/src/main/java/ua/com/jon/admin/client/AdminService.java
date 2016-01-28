package ua.com.jon.admin.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ua.com.jon.admin.shared.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@RemoteServiceRelativePath("/service/adminService")
public interface AdminService extends RemoteService {

    List<TaskTemplateDTO> getTaskTemplates();

    List<GroupDTO> getGroupsAndTasks();

    void postTasksByNames(GroupDTO groupDto, ArrayList<Long> taskIds, SprintDTO sprintDto);

    ArrayList<SpaceDTO> getSpaces();

    String createGroup(SpaceDTO group) throws Exception;

    void saveSprints(List<SprintDTO> newSprints);

    void saveGroups(ArrayList<GroupAndUsersDTO> newSprints);

    ArrayList<SprintDTO> getSprintsAndTasks();

    void sprintTypeChanged(SprintDTO dto);

    ArrayList<String> getAvailableTestNames();

    List<TaskDTO> getTasksByGroup(Long name, Long sprintId);

    ArrayList<GroupAndUsersDTO> getGroupsAndUsers();

    void deleteGroup(Long id);

    void saveGroup(GroupAndUsersDTO newGroup);

    void deleteUserFromGroup(Long groupId, Long userId);

    void deleteUser(Long userId);

    void addStudentToGroup(String groupName, String userName);

    ArrayList<SpaceDTO> getGitHubRepos();

    List<TaskDTO> getTasksBySprintAndTemplate(Long id, Long id1);

    ArrayList<TaskTemplateDTO> getTemplatesAndTasks(Long groupId, Long sprintId);

    List<GroupAndSprintsDTO> getGroupsAndSprints();
}
