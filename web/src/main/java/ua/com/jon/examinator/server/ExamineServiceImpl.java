package ua.com.jon.examinator.server;


import com.jon.tron.exception.CompilationException;
import com.jon.tron.service.processor.ClassProcessor;
import com.jon.tron.service.processor.Crypt;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import ua.com.jon.auth.domain.SpringUser;
import ua.com.jon.common.domain.Sprint;
import ua.com.jon.common.domain.SprintType;
import ua.com.jon.common.domain.Status;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.domain.TaskHistory;
import ua.com.jon.common.domain.TaskTemplate;
import ua.com.jon.common.dto.mapper.SprintDtoMapper;
import ua.com.jon.common.dto.mapper.TaskHistoryDtoMapper;
import ua.com.jon.common.repository.SprintRepository;
import ua.com.jon.common.repository.TaskHistoryRepository;
import ua.com.jon.common.repository.TaskRepository;
import ua.com.jon.common.repository.TaskTemplateRepository;
import ua.com.jon.examinator.client.ExamineService;
import ua.com.jon.examinator.shared.SprintDTO;
import ua.com.jon.examinator.shared.TaskDTO;
import ua.com.jon.examinator.shared.TaskHistoryDto;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@Service("examineService")
public class ExamineServiceImpl /*extends RemoteServiceServlet*/ implements ExamineService, ServletContextAware {
    private static final Logger log = Logger.getLogger(ExamineServiceImpl.class);
    public static final int TASK_PROCESSING_DELAY = 200;
    private static final int THREADS_NUMBER = 10;
    private static final int SAVING_RATE = 100;
    ExecutorService executor = Executors.newFixedThreadPool(THREADS_NUMBER);

    private long lastTime;

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private SprintRepository sprintRepository;

    @Resource
    private TaskHistoryRepository taskHistoryRepository;

    @Autowired
    private ClassProcessor classProcessor;

    @Resource
    private TaskTemplateRepository templateRepository;

    @Autowired
    private HttpServletRequest request;

    private ServletContext servletContext;

    @Value("${core.jar}")
    private String coreJarName;

    @Override
    public String greet(String name) {
        return null;
    }

    @Override
    public ArrayList<TaskDTO> getUserTasks() {
        log.info("-== getUserTasks() ==-");
        SpringUser springUser = (SpringUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = springUser.getUsername();
        List<Task> tasks = taskRepository.findByUserName(userName);
        ArrayList<TaskDTO> taskDtos = new ArrayList<TaskDTO>();
        for (Task task : tasks) {
//            taskDtos.add(TaskDtoMapper.domainToDto(task));
        }
        log.info("-== " + taskDtos + " ==-");
        return taskDtos;
    }

    @Override
    public void taskStatusChanged(TaskDTO dto) {
        log.info("-== taskStatusChanged() ==-");
        Task task = taskRepository.findOne(dto.getId());
        Status newStatus = Status.valueOf(dto.getStatus());
        task.setStatus(newStatus);
        taskRepository.save(task);

        log.info("-== " + dto + " ==-");
    }

    @Override
    public ArrayList<SprintDTO> getSprints() {
        log.info("-== getSprints() ==-");
        Iterable<Sprint> sprintIterable = sprintRepository.findByType(SprintType.ANONYMOUS);
        ArrayList<SprintDTO> sprints = new ArrayList<SprintDTO>();
        for (Sprint sprint : sprintIterable) {
            List<Task> tasks = taskRepository.findBySprintName(sprint.getName());
            Set<Task> unique = new HashSet<>(tasks);
            tasks.clear();
            tasks = new ArrayList<>(unique);
            unique.clear();
            SprintDTO sprintDTO = SprintDtoMapper.cabinetDtoToExamine(tasks, sprint, true);
            log.info("-== " + sprintDTO.getName() + " ==-");
            sprints.add(sprintDTO);
        }

        return sprints;
    }

    @Override
    public String postForTest(final TaskDTO taskDTO, final String userName) {
//        final PrintStream out = System.out;
//        System.setOut(new PrintStream(new ByteArrayOutputStream()));

        if (System.currentTimeMillis() - lastTime < TASK_PROCESSING_DELAY) {
            return "Предыдущее задание еще не проверено, попробуйте позже";
        }
        if (userName != null && !userName.isEmpty()) {
            createSession(userName);
        }
        log.info("-== Cabinet post task for test: " + taskDTO.getCode());
        URL resource = ExamineServiceImpl.class.getResource("/forbid.policy");
        log.info(resource.getPath());

        Map.Entry<String, String> resultEntry;
//        final TaskTemplate template = templateRepository.findOne(taskDTO.getTaskTemplateId());

        try {
            resultEntry = classProcessor.processClass(taskDTO.getCode(), taskDTO.getTestName(),
                    servletContext.getRealPath("/WEB-INF") + "/lib/" + coreJarName, null);
        } catch (CompilationException e) {
            resultEntry = e.getResult();
        } catch (Exception e) {
            log.error(e);
            return "Ошибка проверки. Обратитесь к разработчикам";
        }
        Integer key = Integer.valueOf(resultEntry.getKey());
        String sha1 = key >= SAVING_RATE ? Crypt.sha1(taskDTO.getCode() + taskDTO.getUserName() +
                String.valueOf(System.currentTimeMillis())) : "";

        String testResult = buildTestResult(userName, resultEntry, key, sha1);

        log.info("Cabinet test result is " + testResult);

        saveTaskHistoryAsync(taskDTO, userName, taskDTO.getTaskTemplateId(), key, sha1, testResult);

        lastTime = System.currentTimeMillis();
        return testResult;
    }

    private String buildTestResult(String userName, Map.Entry<String, String> resultEntry, Integer key, String sha1) {
        String testResult = resultEntry.getKey() + '\n' + resultEntry.getValue();

        if (key >= SAVING_RATE && userName != null && !userName.isEmpty()) {
            testResult = testResult + '\n' + "Код решения: " + sha1;
        } else if (key >= SAVING_RATE && userName != null && userName.isEmpty()) {
            testResult = testResult + '\n' + "Решение не сохранено, не указано имя пользователя";
        } else if (key < SAVING_RATE && userName != null && !userName.isEmpty()) {
            testResult = testResult + '\n' + "Решение не сохранено, набранный бал меньше " + SAVING_RATE;
        }
        return testResult;
    }


    private void saveTaskHistoryAsync(final TaskDTO taskDTO, final String userName, final Long template, final Integer key, final String sha1, final String testResult) {
        if (key > 10 && !userName.isEmpty()) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
//                PrintStream out = System.out;
//                System.setOut(new PrintStream(new ByteArrayOutputStream()));
                    try {
//                    Task task = taskRepository.findOne(taskDTO.getId());
//                    task.setCode(taskDTO.getCode());
/*
                        if(testResult.length() > 750) {
                            testResult = testResult.substring(0, 740);
                        }
*/
                        String result = testResult.length() > 1000 ? testResult.substring(0, 1000) : testResult;
//                    taskRepository.save(task);

                        TaskTemplate template = templateRepository.findOne(taskDTO.getTaskTemplateId());
                        taskHistoryRepository.save(new TaskHistory(taskDTO.getCode(), template, userName, new Date(), result, sha1));

                    } catch (Exception de) {
                        log.error(de);
                    } finally {
//                    System.setOut(out);
                    }
                }
            });
        }
    }

    public TaskHistoryDto getTaskHistoryByHash(String hash) {
        TaskHistory taskHistory = null;
        try {
            taskHistory = taskHistoryRepository.findByHash(hash);
            return TaskHistoryDtoMapper.domainToDto(taskHistory);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("Ошибка получения решений с сервера");
        }
    }

    public void createSession(String username) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            session.setAttribute("username", username);
        }
    }

    public String getUser() {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            return (String) session.getAttribute("username");
        } else {
            return null;
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
