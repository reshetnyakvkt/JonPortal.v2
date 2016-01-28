package ua.com.jon.admin.service;

import com.jon.tron.service.processor.Crypt;
import com.jon.tron.service.reflect.ReflectionUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.jon.admin.client.AdminService;
import ua.com.jon.admin.shared.*;
import ua.com.jon.auth.domain.AssemblaSpace;
import ua.com.jon.auth.domain.AssemblaUser;
import ua.com.jon.auth.domain.UserRole;
import ua.com.jon.auth.service.AuthService;
import ua.com.jon.common.dao.GroupDAO;
import ua.com.jon.common.domain.Group;
import ua.com.jon.common.domain.Sprint;
import ua.com.jon.common.domain.SprintType;
import ua.com.jon.common.domain.Status;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.domain.TaskTemplate;
import ua.com.jon.common.domain.User;
import ua.com.jon.common.dto.mapper.GroupAndUsersDtoMapper;
import ua.com.jon.common.dto.mapper.GroupDtoMapper;
import ua.com.jon.common.dto.mapper.SpaceDtoMapper;
import ua.com.jon.common.dto.mapper.SprintDtoMapper;
import ua.com.jon.common.dto.mapper.TaskDtoMapper;
import ua.com.jon.common.repository.GroupRepository;
import ua.com.jon.common.repository.SprintRepository;
import ua.com.jon.common.repository.TaskRepository;
import ua.com.jon.common.repository.TaskTemplateRepository;
import ua.com.jon.common.repository.UserRepository;
//import ua.com.jon.utils.GitHubClient;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 15.06.13
 * Time: 20:33
 */

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    private static final Logger log = Logger.getLogger(AdminServiceImpl.class);

    @Resource
    private GroupRepository groupRepository;

    @Resource
    private TaskTemplateRepository taskTemplateRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private SprintRepository sprintRepository;

    @Autowired
    private AuthService AuthService;

    @Autowired
    private GroupDAO groupDAO;

    @Value( "${tests.package}" )
    private String testsPackage;

    @Override
    public List<TaskTemplateDTO> getTaskTemplates() {
        List<TaskTemplateDTO> taskTemplates = new ArrayList<TaskTemplateDTO>();
        TaskTemplateDTO task1 = new TaskTemplateDTO("Задание 1", "Текст задания 1", "CLASS");
        TaskTemplateDTO task2 = new TaskTemplateDTO("Задание 2", "Текст задания 2", "CLASS");
        TaskTemplateDTO task3 = new TaskTemplateDTO("Задание 3", "Текст задания 3", "CLASS");
        TaskTemplateDTO task4 = new TaskTemplateDTO("Задание 4", "Текст задания 4", "CLASS");
        TaskTemplateDTO task5 = new TaskTemplateDTO("Задание 5", "Текст задания 4", "CLASS");

        taskTemplates.add(task1);
        taskTemplates.add(task2);
        taskTemplates.add(task3);
        taskTemplates.add(task4);
        taskTemplates.add(task5);

        return taskTemplates;
    }

    @Override
    public List<GroupDTO> getGroupsAndTasks() {
        log.info("getGroupsAndTasks()");
        ArrayList<GroupDTO> groupDTOs = new ArrayList<GroupDTO>();
        Iterable<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            ArrayList<TaskTemplate> tasks = taskTemplateRepository.findByGroupName(group.getName());
            GroupDTO groupDTO = GroupDtoMapper.domainToDto(group, tasks);
            groupDTOs.add(groupDTO);

        }
        log.info("= " + groupDTOs.size() + " =");
        return groupDTOs;

    }

    @Override
    public ArrayList<SpaceDTO> getSpaces() {
        log.info("-- getSpaces()");
//        List<AssemblaSpace> assemblaSpaces = authService.getSpaces();
        List<AssemblaSpace> assemblaSpaces = AuthService.getSpaces();
        ArrayList<SpaceDTO> spaceDTOs = new ArrayList<SpaceDTO>(assemblaSpaces.size());
        for (AssemblaSpace space : assemblaSpaces) {
            List<AssemblaUser> usersInSpace = AuthService.getUsersBySpace(space.getName());
            spaceDTOs.add(SpaceDtoMapper.spaceToDto(null, space, usersInSpace, ""));
        }
        log.info("-- getSpaces(), return space to client: " + spaceDTOs);
        return spaceDTOs;
    }

    @Override
    @Transactional
    public String createGroup(SpaceDTO spaceDto) throws Exception {
        log.info("-- createGroup() " + spaceDto);
        try {
//            for (UserDTO userDTO : spaceDto.getUsers()) {
            Set<String> nameSet = userToNamesWithTrim(spaceDto.getUsers());
            List<User> users = new ArrayList<User>();
            if (nameSet != null && !nameSet.isEmpty()) {
                users = userRepository.findByNames(nameSet);
                for (User user : users) {
                    nameSet.remove(user.getLogin());
                }
            }
            String sha1 = null;
            if (spaceDto != null && !spaceDto.getName().isEmpty()) {
                sha1 = Crypt.sha1(spaceDto.getName() + String.valueOf(System.currentTimeMillis()));
            }

            Group group = createAndSaveGroup(spaceDto, nameSet, users, sha1);
//            userRepository.save(users);
            saveUsersToGroup(users, group);
            //userRepository.save(users);
            log.info("-- created group " + group);
            return sha1;
//            }
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    private void saveUsersToGroup(List<User> users, Group group) {
        for (User user : users) {
            List<Group> tmpGroups = groupRepository.findByUsersIn(user.getLogin());
            user.getGroups().addAll(tmpGroups);
            user.getGroups().add(group);
        }
        group.getUsers().addAll(users);
        groupRepository.save(group);
    }

    private Group createAndSaveGroup(SpaceDTO spaceDto, Set<String> nameSet, List<User> users, String code) {
        Group group = new Group(spaceDto.getName(), new Date(), true, new HashSet<User>(), spaceDto.getRepositoryUrl(), code);
        Set<Group> groups = new HashSet<Group>();
        groups.add(group);
        Set<UserRole> roles = new HashSet<UserRole>();
        for (String userName : nameSet) {
            users.add(new User(userName, null, new Date(), groups, roles, false));
        }
        groupRepository.save(group);
        return group;
    }

    @Override
    public void saveSprints(List<SprintDTO> newSprints) {
        log.info("-- saveSprints() " + newSprints);
        List<SprintDTO> sprintDTOs = trimSprintsAndConvertToSet(newSprints);
        Set<Long> ids = getIdsFromSprintsWithoutNull(sprintDTOs);
        List<Sprint> sprints = new ArrayList<Sprint>();
        if(!ids.isEmpty()) {
            sprints = sprintRepository.findByIds(ids);
        }

        List<Sprint> sprintsToSave = SprintDtoMapper.convertSprintDtosToEntity(sprints, sprintDTOs);
        sprintRepository.save(sprintsToSave);
        log.info("-- saved sprints " + sprintsToSave);
    }

    @Override
    public void saveGroups(ArrayList<GroupAndUsersDTO> newGroup) {
         log.info("-- saveGroup " + newGroup);

        // TODO save group
    }

    private Set<Long> getIdsFromSprintsWithoutNull(List<SprintDTO> sprintDTOs) {
        Set<Long> keys = new HashSet<Long>();
        for(SprintDTO sprintDTO : sprintDTOs){
            if(sprintDTO.getId() !=  null){
               keys.add(sprintDTO.getId());
            }
        }
        return keys;
    }

    private Set<String> userToNamesWithTrim(ArrayList<UserDTO> users) {
        Set<String> userNames = new HashSet<String>(users.size());
        for (UserDTO user : users) {
            userNames.add(user.getName().trim());
        }
        return userNames;
    }

    private List<SprintDTO> trimSprintsAndConvertToSet(List<SprintDTO> sprints) {
        List<SprintDTO> sprintDTOs = new ArrayList<SprintDTO>(sprints.size());
        for (SprintDTO sprint : sprints) {
            String trimmedName = sprint.getName().trim();
            sprint.setName(trimmedName);
            sprintDTOs.add(sprint);
        }
        return sprintDTOs;
    }

    @Override
    public ArrayList<SprintDTO> getSprintsAndTasks() {
        log.info("-- getSprintsAndTasks() ");

        Iterable<Sprint> sprintIterable = sprintRepository.findAll();
        ArrayList<SprintDTO> sprints = new ArrayList<SprintDTO>();
        for (Sprint sprint : sprintIterable) {
//            List<Task> tasks = taskRepository.findBySprintName(sprint.getName());
            sprints.add(SprintDtoMapper.domainToDto(sprint));
        }
        log.info("-- return sprint to client " + sprints);
        return sprints;
    }

    @Override
    public void sprintTypeChanged(SprintDTO dto) {
        log.info("-- sprintTypeChanged: " + dto);
        Sprint sprint = sprintRepository.findOne(dto.getId());
        SprintType newType = SprintType.valueOf(dto.getType());
        sprint.setType(newType);
        sprintRepository.save(sprint);

        log.info("Changed sprint is " + dto);
    }

    @Override
    public ArrayList<String> getAvailableTestNames() {
        try {
            return ReflectionUtil.findAllTests(testsPackage);
        } catch (IOException e) {
            log.error("Get tests error ", e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            log.error("No tests found ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TaskDTO> getTasksByGroup(Long groupId, Long sprintId) {
        log.info("-- getTasksByGroup: " + groupId);
        Pageable topOne = new PageRequest(0, 1);
        List<User> users = userRepository.findByGroupIdIgnore(groupId, topOne);
        Group group = groupRepository.findOne(groupId);
        List<Task> tasks = taskRepository.findByUserAndGroup(users.get(0).getId(), group.getId());
        return TaskDtoMapper.domainsToAdminDtos(tasks);
    }

    @Override
    public ArrayList<GroupAndUsersDTO> getGroupsAndUsers() {
        Set<Group> groups = new HashSet<Group>();
        groups.addAll(groupRepository.findAllGroupsAndUsers());
        List<Group> groupsList = new ArrayList<Group>(groups.size());
        groupsList.addAll(groups);
        return GroupAndUsersDtoMapper.domainsToDtos(groupsList);
    }

    @Override
    @Transactional
    public void deleteGroup(Long id) {
        Group group = groupRepository.findOne(id);
        List<User> outGroupUsers = new ArrayList<User>(group.getUsers());
        for (User user : group.getUsers()) {
            user.getGroups().remove(group);
            Iterator<Task> taskIterator = user.getTasks().iterator();
            while (taskIterator.hasNext()) {
                Task task = taskIterator.next();
                if (group.equals(task.getGroup())) {
                    taskIterator.remove();
                    task.setGroup(null);
                    taskRepository.delete(task);
                }
            }
        }
        group.getUsers().clear();
        userRepository.save(outGroupUsers);
        groupRepository.save(group);
        groupRepository.delete(id);
        log.info("-- Deleting group " + id + " was successfuly");
    }

    @Override
    @Transactional
    public void deleteUserFromGroup(Long groupId, Long userId) {
        Group group = groupRepository.findOne(groupId);
        for (User user : group.getUsers()) {
            if (user.getId().equals(userId)) {
                group.getUsers().remove(user);
                user.getGroups().remove(group);
                userRepository.save(user);
                groupRepository.save(group);
                return;
            }
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }

    @Override
    @Transactional
    public void addStudentToGroup(String groupName, String userName) {
        Group group = groupRepository.findGroupAndUsersByName(groupName);
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            Set<Group> groups = new HashSet<Group>();
            groups.add(group);
            HashSet<UserRole> roles = new HashSet<UserRole>();
            user = new User(userName, null, new Date(), groups, roles, false);
        }
        group.getUsers().add(user);
        user.getGroups().add(group);
        groupRepository.save(group);
    }

    @Override
    public ArrayList<SpaceDTO> getGitHubRepos() {
        ArrayList<SpaceDTO> repos = null;
/*
        try {
            repos = GitHubClient.getRepositoriesAndCollaborators();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        return repos;
    }

    @Override
    public List<TaskDTO> getTasksBySprintAndTemplate(Long sprintId, Long templateId) {
        ArrayList<TaskDTO> tasks = new ArrayList<>();
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("t1");
        taskDTO.setUserName("u1");
        taskDTO.setResult("20");
        taskDTO.setCode("    @Override\n" +
                "    public ArrayList<TaskTemplateDTO> getTemplatesAndTasks(Long groupId, Long sprintId) {\n" +
                "        ArrayList<TaskTemplateDTO> templates = new ArrayList<>();\n" +
                "        templates.add(new TaskTemplateDTO(\"tt1\"));\n" +
                "        return templates;\n" +
                "    }");
        tasks.add(taskDTO);
        TaskDTO taskDTO1 = new TaskDTO();
        taskDTO1.setName("t2");
        taskDTO1.setUserName("u2");
        taskDTO1.setResult("100");
        tasks.add(taskDTO1);

        return tasks;
    }

    @Override
    public ArrayList<TaskTemplateDTO> getTemplatesAndTasks(Long groupId, Long sprintId) {
        ArrayList<TaskTemplateDTO> templates = new ArrayList<>();
        templates.add(new TaskTemplateDTO("tt1"));
        return templates;
    }

    @Override
    public List<GroupAndSprintsDTO> getGroupsAndSprints() {
        List<GroupAndSprintsDTO> activeGroupsAndSprints = null;
        try {
            activeGroupsAndSprints = groupDAO.findActiveGroupsAndSprints();
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
        return activeGroupsAndSprints;
/*
        ArrayList<GroupAndSprintsDTO> groups = new ArrayList<>();
        GroupAndSprintsDTO group = new GroupAndSprintsDTO(1L, "g1", new ArrayList<>());
        SprintDTO sprintDTO = new SprintDTO("s1");
        sprintDTO.getTasks().add(new TaskTemplateDTO("tt1"));
        group.getSprints().add(sprintDTO);
        groups.add(group);
        return groups;
*/
    }

    @Override
    @Transactional
    public void saveGroup(GroupAndUsersDTO newGroup) {
        log.info("-- Saving group " + newGroup);
        Group group = groupRepository.findOne(newGroup.getId());
        //group.getUsers()

        Set<UserDTO> users =  newGroup.getUsers();
        Set<Long> userIds = new HashSet<Long>(users.size());
        Set<User> newUsers = new HashSet<User>();
        Set<Group> userGroups = new HashSet<Group>();
        userGroups.add(group);
        Set<UserRole> roles = new HashSet<UserRole>();
        for (UserDTO user : users) {
            if (user.getId() == null) {
                User newUser = new User(user.getName(), null, new Date(), userGroups, roles, false);
                newUsers.add(newUser);
                userRepository.save(newUser);
            }
            userIds.add(user.getId());
        }
        Iterable<User> userEntities = userRepository.findAll(userIds);
        for (User user : userEntities) {
            for (UserDTO userDTO : users) {
                if (user.getId().equals(userDTO.getId())) {
                    user.setLogin(userDTO.getName());
                }
            }
            newUsers.add(user);
        }

        group.setUsers(newUsers);
        groupRepository.save(group);
/*
        userRepository.save(userEntities);*/

    }


    @Override
    public void postTasksByNames(GroupDTO groupDto, ArrayList<Long> taskIds, SprintDTO sprintDto) {
        log.info("-- Post tasks " + taskIds + " to groupDto " + groupDto);
        Sprint sprint = sprintRepository.findOne(sprintDto.getId());
        Group group = groupRepository.findOne(groupDto.getId());
        try {
            //trimListElements(taskNames);
            List<User> usersInGroup = userRepository.findByGroupName(group.getName());
            Iterable<TaskTemplate> taskTemplates = taskTemplateRepository.findAll(taskIds);

            for (TaskTemplate taskTemplate : taskTemplates) {
                for (User user : usersInGroup) {
                    Task task = new Task(user, taskTemplate, sprint, Status.NEW, "", "", group);
                    taskRepository.save(task);
                    //log.info("-- Posted task " + task);
                }
                //Task groupTask = new Task(null, taskTemplate, sprint, Status.NEW, "", "", group);
                //taskRepository.save(groupTask);
            }
        } catch (Exception e) {
            log.error("Post tasks error " + e);
            throw new RuntimeException(e);
        }
    }

    private void trimListElements(ArrayList<String> taskNames) {
        for (int i = 0; i < taskNames.size(); i++) {
            taskNames.set(i, taskNames.get(i).trim());
        }
    }
}