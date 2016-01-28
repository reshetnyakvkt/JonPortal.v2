package ua.com.jon.cabinet.server;

import com.jon.tron.exception.CompilationException;
import com.jon.tron.service.processor.ClassProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.async.DeferredResult;
import ua.com.jon.cabinet.shared.GroupDTO;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;
import ua.com.jon.common.domain.*;
import ua.com.jon.common.dto.mapper.GroupDtoMapper;
import ua.com.jon.common.dto.mapper.SprintDtoMapper;
import ua.com.jon.common.dto.mapper.TaskDtoMapper;
import ua.com.jon.common.repository.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 12.06.15
 */
@Service
public class ExtTaskServiceImpl implements ExtTasksService, ServletContextAware {
    private static final Logger log = Logger.getLogger(ExtTaskServiceImpl.class);

    @Autowired
    private ClassProcessor classProcessor;

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private SprintRepository sprintRepository;

    @Resource
    private TaskTemplateRepository templateRepository;

    @Resource
    private GroupRepository groupRepository;

    private ServletContext servletContext;

    @Value("${core.jar}")
    private String coreJarName;

    @Value("${junit.jar}")
    private String junitJarName;

    private static final int CHECK_DELAY = 2;

//    @Autowired
//    private GroupDAO groupDAO;

    private Map<Map.Entry<TaskDTO, DeferredResult<String>>, Integer> tasksQueue = new ConcurrentHashMap<>();

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public ExtTaskServiceImpl () {
        executor.scheduleWithFixedDelay(() -> {
            try {
                Iterator<Map.Entry<Map.Entry<TaskDTO, DeferredResult<String>>, Integer>> iterator = tasksQueue.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Map.Entry<TaskDTO, DeferredResult<String>>, Integer> entry = iterator.next();
                    TaskDTO taskDTO = entry.getKey().getKey();
                    taskDTO.setClassName(null);
                    taskDTO.setCode(null);
                    taskDTO.setMaterial(null);
                    taskDTO.setText(null);
                    taskDTO.setUserName(null);
                    taskDTO.setName(null);

                    Task task = taskRepository.findOne(taskDTO.getId());
                    entry.setValue(entry.getValue() - CHECK_DELAY);
                    if (task.getStatus() == Status.DONE || task.getStatus() == Status.NEW) {
                        iterator.remove();
                        entry.getKey().getValue().setResult(task.getResult());
                    } else if (entry.getValue() - CHECK_DELAY <= 0) {
                        entry.getKey().getValue().setResult("-\nВремя ожидания ответа от модуля проверки больше минуты");
                        iterator.remove();
                    }
                }
            } catch (Exception ex) {
                log.error("Thread pool executor: catched exception", ex);
            }
        }, 0, CHECK_DELAY, TimeUnit.SECONDS);
    }

    @Override
    public String dispatchTaskChecking(TaskDTO dto, DeferredResult<String> res) {
        String result = "";

        if (dto.getType().equals(TaskType.CLASS.name()) && dto.getStatus().equals("TEST")) {
            log.info("-== Cabinet dispatchTaskChecking: " + dto);
            res.setResult(postForTest(dto));
        } else if (dto.getType().equals(TaskType.SVN.name()) && dto.getStatus().equals("TEST")) {
            result = taskStatusChanged(dto, res);
        }
//        log.info("dispatchTaskChecking " + dto);
        return result;
    }

    @Override
    public String taskStatusChanged(TaskDTO dto, DeferredResult<String> res) {
        Task task = taskRepository.findOne(dto.getId());
        Status newStatus = Status.valueOf(dto.getStatus());
        task.setStatus(newStatus);
        task.setResult("");
        try {
            taskRepository.save(task);
            log.info("taskStatusChanged " + task.getStatus().name());
        } catch (Exception e) {
            log.error("", e);
        }

        log.info("taskStatusChanged " + task.getStatus().name());
        tasksQueue.put(new AbstractMap.SimpleEntry<>(dto, res), 150);
        return "";
    }

    @Override
    public ArrayList<SprintDTO> getSprints(Long groupId) {
        log.info("--- getSprints() ---");
        String userName = getSpringUserName();
        if (groupId == null) {
            return new ArrayList<SprintDTO>();
        }
//        Iterable<Sprint> sprintIterable = sprintRepository.findByUserAndGroup(userName, selectedGroup.getId());
        ArrayList<SprintDTO> sprints = new ArrayList<SprintDTO>();
        User user = userRepository.findByUserName(userName);

        List<Task> userGroupTasks = taskRepository.findByUserAndGroup(user.getId(), groupId);

        Set<Sprint> sprs = new HashSet<>();
        for (Task task : userGroupTasks) {
            sprs.add(task.getSprint());
        }
//        List<Sprint> ids = sprintRepository.findByIds(sprs);
        for (Sprint sprint : sprs) {
            List<Task> tasks = new ArrayList<>();
            for (Task aByUserAndGroup : userGroupTasks) {
                if (aByUserAndGroup.getSprint().getId().equals(sprint.getId())) {
                    tasks.add(aByUserAndGroup);
                }
            }
            //List<Task> tasks = taskRepository.findByUserAndSprintAndGroup(user.getId(), sprint.getId(), selectedGroup.getId());
//            List<Task> tasks = taskRepository.findByUserAndSprintAndGroup(user.getId(), sprint.getId(), selectedGroup.getId());
            sprints.add(SprintDtoMapper.domainToDto(tasks, sprint, 0.0));
        }
        log.info("--- Sent sprint to client " + sprints.size() + " ---");
        return sprints;
    }

    public String postForTest(TaskDTO taskDTO) {
        return postForTest(taskDTO, true);
    }

    public String postForTest(TaskDTO taskDTO, boolean isSaveNeed) {
        log.info("-- Post for test [" + taskDTO.getId() + "] for user - " + getSpringUserName());
        Map.Entry<String, String> resultEntry;
        Task task = null;
        try {
            task = taskRepository.findOne(taskDTO.getId());
            TaskTemplate template = templateRepository.findOne(task.getTaskTemplate().getId());
            String tronCoreJar = servletContext.getRealPath("/WEB-INF") + "/lib/" + coreJarName;
            String junitJar = servletContext.getRealPath("/WEB-INF") + "/lib/" + junitJarName;
            resultEntry = classProcessor.processClass(taskDTO.getCode(), template.getTestName(),
                    tronCoreJar, junitJar);
        } catch (CompilationException e) {
            resultEntry = e.getResult();
        } catch (Exception e) {
            log.error(e);
            return "Ошибка проверки " + e.getMessage() + ". Обратитесь к разработчикам";
        }
        String testResult = resultEntry.getKey() + '\n' + resultEntry.getValue();
        log.info("Cabinet test result is " + testResult);
        if (isSaveNeed) {
            task.setCode(taskDTO.getCode());
/*        if(testResult.length() > 750) {
            testResult = testResult.substring(0, 740);
        }*/
            task.setResult(testResult);

            taskRepository.save(task);
        }
        return testResult;
    }


    private void removeTasksOfCurrentUser(ArrayList<TaskDTO> list, String userName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(userName)) {
                list.remove(i);
            }
        }
    }

    @Override
    public double getSprintRate(Long groupId, Long sprintId) {
        return getSprintRate(groupId, sprintId, getSpringUserName());
    }

//    @Override
    public double getSprintRate(Long groupId, Long sprintId, String userName) {
        log.info("-== getSprintRate: groupId = " + groupId + ", sprintId = " + sprintId + ", userName = " + userName);
        try {
            User user = userRepository.findByUserName(userName);
            List<Task> tasks = taskRepository.findByUserAndSprintAndGroup(user.getId(), sprintId, groupId);
            return calcTasksRate(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
        return 0.0;
    }

    private double calcTasksRate(List<Task> tasks) {
        int doneCount = 0;
        int sumResult = 0;
        for (Task task : tasks) {
            String resultStr = "0";
            if (task.getResult() != null && !task.getResult().isEmpty()) {
                String[] lines = task.getResult().split("\n");
                if (lines.length > 0) {
                    resultStr = lines[0];
                } else {
                    resultStr = task.getResult();
                }
            }
            int result = 0;
            try {
                result = Integer.parseInt(resultStr);
            } catch (Exception e) {
                log.error(e);
            }
            sumResult += result;
            if (result >= 10) {
                doneCount++;
            }
        }
        if (tasks.size() * doneCount == 0) {
            return 0;
        } else {
//            return 100 / tasks.size() * doneCount;
            return sumResult / tasks.size();
        }
    }

    @Override
    public double getCourseRate(Long selectedGroupId) {
        return getCourseRate(selectedGroupId, getSpringUserName());
    }

//    @Override
    public double getCourseRate(Long selectedGroupId, String userName) {
        Iterable<Sprint> sprintIterable = sprintRepository.findByUserAndGroup(userName, selectedGroupId);
        ArrayList<Task> allSprintsTasks = new ArrayList<Task>();
        User user = userRepository.findByUserName(userName);
        for (Sprint sprint : sprintIterable) {
            List<Task> tasks = taskRepository.findByUserAndSprintAndGroup(user.getId(), sprint.getId(), selectedGroupId);
            allSprintsTasks.addAll(tasks);
        }
        return calcTasksRate(allSprintsTasks);
    }

//    @Override
    public String getSpringUserName() {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        org.springframework.security.core.userdetails.User springUser;
        if (authentication instanceof String) {
            throw new SecurityException("can't grant access to anonymous ");
        }
        springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return springUser.getUsername();
    }

    @Override
    public List<GroupDTO> getUserGroups() {
        String userName = getSpringUserName();
        List<Group> groups = groupRepository.findByUsersInOrderByStartDateDesc(userName);
        return GroupDtoMapper.domainToAdminDtos(groups);
    }

//    @Transactional
//    @Override
    public void setValidationResult(Long id, String statusStr, String result) {
        Task task = taskRepository.findOne(id);
        Status status = Status.valueOf(statusStr);
        task.setStatus(status);
        task.setResult(result);
        taskRepository.save(task);
    }

//    @Override
    public List<TaskDTO> refreshTasks(List<Long> ids) {
        Iterable<Task> tasks = taskRepository.findAll(ids);
        List<TaskDTO> taskDTOs = TaskDtoMapper.domainsToDtos(tasks, 0.0);
        for (TaskDTO taskDTO : taskDTOs) {
            taskDTO.setClassName(null);
            taskDTO.setCode(null);
            taskDTO.setMaterial(null);
            taskDTO.setText(null);
            taskDTO.setUserName(null);
            taskDTO.setName(null);
        }

        return taskDTOs;
    }


    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

}
