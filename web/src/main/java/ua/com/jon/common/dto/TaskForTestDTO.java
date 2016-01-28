package ua.com.jon.common.dto;

import com.jon.tron.domain.TaskStatus;
import com.jon.tron.domain.TaskTemplate;
import com.jon.tron.domain.User;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/19/13
 */
@XStreamAlias("test")
public class TaskForTestDTO {

    @XStreamAlias("test-result")
    private String testResult;

//    @XStreamAlias("id")
    private Long id;

//    @XStreamAlias("user")
    private User user;

//    @XStreamAlias("task-template")
    private TaskTemplate template;

//    @XStreamAlias("task-status")
//    @XStreamConverter(MyEnumConverter.class)
    private TaskStatus taskStatus;

    public TaskForTestDTO() {
    }

    public TaskForTestDTO(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskForTestDTO(User user, TaskTemplate template, TaskStatus taskStatus, String testResult) {
        this.user = user;
        this.template = template;
        this.taskStatus = taskStatus;
        this.testResult = testResult;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskTemplate getTemplate() {
        return template;
    }

    public void setTemplate(TaskTemplate template) {
        this.template = template;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", user=" + user +
                ", template=" + template +
                ", taskStatus=" + taskStatus +
                ", testResult='" + testResult + '\'' +
                '}';
    }
}
