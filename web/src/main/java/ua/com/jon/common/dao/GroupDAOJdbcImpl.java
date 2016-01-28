package ua.com.jon.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import ua.com.jon.admin.shared.GroupAndSprintsDTO;
import ua.com.jon.admin.shared.SprintDTO;
import ua.com.jon.admin.shared.TaskTemplateDTO;
import ua.com.jon.common.domain.TaskTemplate;
import ua.com.jon.common.dto.GroupDTO;
import ua.com.jon.common.dto.TaskDTO;
import ua.com.jon.common.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

/**
 * Date: 01.01.13
 * Time: 23:58
 */
@Repository
public class GroupDAOJdbcImpl implements GroupDAO {
    private static Logger log = Logger.getLogger(GroupDAOJdbcImpl.class.getName());
    private static final String GROUPS_QUERY =
            "SELECT u.id, u.login, u.ignore_statistic, " +
                    "g.id, g.name, g.active, g.REPOSITORY_URL, g.code, t.id, t.status, tt.name, tt.testName, tt.module_suffix\n" +
                    "FROM USERS u, GROUPS g, TASKS t, TASK_TEMPLATES tt\n" +
                    "WHERE t.group_id = g.id AND t.user_id = u.id AND t.template_id = tt.id\n" +
                    "AND t.status = 'TEST' AND g.active = 1 AND g.REPOSITORY_URL <> '' AND g.REPOSITORY_URL IS NOT NULL";
    private static final String GROUPS_TASKS_QUERY =
            "SELECT u.id uid, u.login, g.id gid, g.name, s.id sid, s.name, t.id, t.code, t.result, tt.id ttid, tt.name, tt.taskText\n" +
                    "FROM USERS u, GROUPS g, TASKS t, TASK_TEMPLATES tt, SPRINTS s\n" +
                    "WHERE t.group_id = g.id AND t.user_id = u.id AND t.template_id = tt.id AND t.sprint_id = s.id\n" +
                    "AND g.active = 1 AND u.IGNORE_STATISTIC = 0\n" +
                    "ORDER BY g.startDate DESC";

    private static final String GROUP_INFO_QUERY =
            "SELECT u.login, FLOOR(SUM(result) / count(*))\n" +
                    "FROM USERS u, GROUPS g, TASKS t\n" +
                    "WHERE t.group_id = g.id AND t.user_id = u.id\n" +
                    "\tAND u.ignore_statistic = 0 AND t.group_id = ?\n" +
                    "GROUP BY u.id, t.sprint_id\n" +
                    "ORDER BY u.id, t.sprint_id";

    private static final String SPRINTS_COUNT_GROUP = "";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<GroupDTO> findActiveGroupAndTasksAndUsers() {

        return jdbcTemplate.query(GROUPS_QUERY, new ResultSetExtractor<List<GroupDTO>>() {
            @Override
            public List<GroupDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<Long, GroupDTO> groups = new HashMap<Long, GroupDTO>();
                Map<Long, UserDTO> users = new HashMap<Long, UserDTO>();
                int userIdIndex = 1;
                int loginIndex = 2;
                int ignoreIndex = 3;
                int groupIdIndex = 4;
                int gNameIndex = 5;
                int gActiveIndex = 6;
                int repoUrlIndex = 7;
                int codeIndex = 8;
                int taskIdIndex = 9;
                int taskStatusIndex = 10;
                int taskNameIndex = 11;
                int testNameIndex = 12;
                int suffixIndex = 13;
                while (rs.next()) {
                    long groupId = rs.getLong(groupIdIndex);
                    GroupDTO group;
                    UserDTO user;
                    long userId = rs.getLong(userIdIndex);
                    String userName = rs.getString(loginIndex);
                    if (!users.containsKey(userId)) {
                        user = new UserDTO(userId, userName, false,
                                rs.getBoolean(ignoreIndex));
                        users.put(userId, user);
                    } else {
                        user = users.get(userId);
                    }

                    if (!groups.containsKey(groupId)) {
                        group = new GroupDTO(groupId, new ArrayList<TaskDTO>(), new ArrayList<UserDTO>(), rs.getString(gNameIndex), rs.getBoolean(gActiveIndex),
                                rs.getString(repoUrlIndex), rs.getString(codeIndex));
                        groups.put(groupId, group);
                    } else {
                        group = groups.get(groupId);
                    }
                    TaskDTO task = new TaskDTO(rs.getLong(taskIdIndex), rs.getString(taskNameIndex), null, rs.getString(taskStatusIndex),
                            userName, rs.getString(testNameIndex), rs.getString(suffixIndex));
                    group.getTasks().add(task);
                    group.getUsers().add(user);
                }
                return new ArrayList<>(groups.values());
            }
        });
    }

    @Override
    public List<List<String>> findByGroupIdAndUserNotIgnore(Long selectedGroupId) throws Exception {
        return jdbcTemplate.query(GROUP_INFO_QUERY, new ResultSetExtractor<List<List<String>>>() {
            @Override
            public List<List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                final int userNameIndex = 1;
                final int markSprintIndex = 2;

                Map<String, LinkedList<Integer>> users = new HashMap<String, LinkedList<Integer>>();
                int maxSprintsCount = 0;
                while (rs.next()) {
//                    long groupId = rs.getInt(userIdIndex);
                    String userName = rs.getString(userNameIndex);
//                    long sprintId = rs.getLong(sprintIdIndex);
                    int markSprint = rs.getInt(markSprintIndex);

                    LinkedList<Integer> sprints = null;
                    if (!users.containsKey(userName)) {
                        sprints = new LinkedList<Integer>();
                        users.put(userName, sprints);
                    } else {
                        sprints = users.get(userName);
                    }

                    sprints.addLast(markSprint);
                    if (maxSprintsCount < sprints.size()) {
                        maxSprintsCount = sprints.size();
                    }
                }
                LinkedList<List<String>> sprints = new LinkedList<List<String>>();
                for (Map.Entry<String, LinkedList<Integer>> user : users.entrySet()) {
                    LinkedList<String> sprint = new LinkedList<>();
                    int sum = 0;
                    sprints.addLast(sprint);
                    for (Integer mark : user.getValue()) {
                        sum += mark;
                        sprint.addLast(String.valueOf(mark));
                    }
                    int rate = 1;
                    if (maxSprintsCount != 0) {
                        rate = sum / (maxSprintsCount);
                    }
                    sprint.addFirst(String.valueOf(Math.round(rate)));
                    sprint.addFirst(user.getKey());
                }
                correctionSprintsForLastAddedStudents(sprints);
                return sprints;
            }
        }, selectedGroupId);
    }

    @Override
    public List<GroupAndSprintsDTO> findActiveGroupsAndSprints() {
        return jdbcTemplate.query(GROUPS_TASKS_QUERY, new ResultSetExtractor<List<GroupAndSprintsDTO>>() {
            @Override
            public List<GroupAndSprintsDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<Long, GroupAndSprintsDTO> groups = new HashMap<Long, GroupAndSprintsDTO>();
                Map<Long, SprintDTO> sprints = new HashMap<Long, SprintDTO>();
//                Map<Long, TaskTemplateDTO> taskTemplates = new HashMap<Long, TaskTemplateDTO>();
                Map<Long, UserDTO> users = new HashMap<Long, UserDTO>();
                int userIdIndex = 1;
                int loginIndex = 2;
                int groupIdIndex = 3;
                int gNameIndex = 4;
                int sprintIdIndex = 5;
                int sprintNameIndex = 6;
                int taskIdIndex = 7;
                int taskCodeIndex = 8;
                int taskResultIndex = 9;
                int taskTemplateIdIndex = 10;
                int taskTemplateNameIndex = 11;
                int taskTemplateTextIndex = 12;
//                int suffixIndex = 14;
                while (rs.next()) {
                    long groupId = rs.getLong(groupIdIndex);
                    GroupAndSprintsDTO group;

                    String userName = rs.getString(loginIndex);
/*
                    UserDTO user;

                    if (!users.containsKey(userId)) {
                        user = new UserDTO(userId, userName, false, true);
                        users.put(userId, user);
                    } else {
                        user = users.get(userId);
                    }
*/

                    if (!groups.containsKey(groupId)) {
                        group = new GroupAndSprintsDTO(groupId, rs.getString(gNameIndex),new ArrayList<SprintDTO>());
                        groups.put(groupId, group);
                    } else {
                        group = groups.get(groupId);
                    }

                    long sprintId = rs.getLong(sprintIdIndex);
                    SprintDTO sprint = new SprintDTO(sprintId);
                    String sprintName = rs.getString(sprintNameIndex);
                    if (!group.getSprints().contains(sprint)) {
                        sprint = new SprintDTO(sprintId, sprintName, true, null, new ArrayList<>(), null);
                        group.getSprints().add(sprint);
                    } else {
                        sprint = group.getSprints().get(group.getSprints().indexOf(sprint));
                    }

                    long taskTemplateId = rs.getLong(taskTemplateIdIndex);
                    TaskTemplateDTO taskTemplateDTO = new TaskTemplateDTO(taskTemplateId);
                    String taskTemplateName = rs.getString(taskTemplateNameIndex);
                    String taskTemplateText = rs.getString(taskTemplateTextIndex);
                    if (!sprint.getTasks().contains(taskTemplateDTO)) {
                        taskTemplateDTO = new TaskTemplateDTO(taskTemplateId, taskTemplateName, null,
                                null, null, null, null);
                        sprint.getTasks().add(taskTemplateDTO);
                    } else {
                        taskTemplateDTO = sprint.getTasks().get(sprint.getTasks().indexOf(taskTemplateDTO));
                    }

                    String taskResult = rs.getString(taskResultIndex);
                    String taskCode = rs.getString(taskCodeIndex);
                    ua.com.jon.admin.shared.TaskDTO task = new ua.com.jon.admin.shared.TaskDTO(rs.getLong(taskIdIndex),
                            taskTemplateText, taskTemplateName, null, taskResult, taskCode, null, userName, null, taskTemplateId, null);
                    taskTemplateDTO.getTasks().add(task);
//                    sprint.getTasks().add(taskTemplateDTO);
/*
                    if (!sprint.getTasks().contains(taskTemplateDTO)) {
                        sprint.getTasks().add(taskTemplateDTO);
                    }
*/
/*
                    if (!group.getSprints().contains(sprint)) {
                        group.getSprints().add(sprint);
                    }
*/
                }
                return new ArrayList<>(groups.values());
            }
        });
    }

    private void correctionSprintsForLastAddedStudents(LinkedList<List<String>> sprints) {
        int sprintCount = 0;
        for (List<String> sprint : sprints) {
            if (sprint.size() > sprintCount) {
                sprintCount = sprint.size();
            }
            for (List<String> task : sprints) {
                while (task.size() < sprintCount) {
                    task.add(1, "0");
                }
            }
        }
    }
}
