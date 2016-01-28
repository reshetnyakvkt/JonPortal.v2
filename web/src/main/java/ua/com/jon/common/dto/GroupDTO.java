package ua.com.jon.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 1/15/14
 */
public class GroupDTO implements Serializable {
    private List<TaskDTO> tasks = new ArrayList<TaskDTO>();
    private List<UserDTO> users = new ArrayList<UserDTO>();
    private Long id;
    private String name;
    private boolean status;
    private String repository;
    private String code;

    public GroupDTO() {
    }

    public GroupDTO(Long id, List<TaskDTO> tasks, List<UserDTO> users, String groupName, boolean groupStatus, String repository, String code) {
        this.id = id;
        this.tasks = tasks;
        this.users = users;
        this.name = groupName;
        this.status = groupStatus;
        this.repository = repository;
        this.code = code;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "tasks=" + tasks +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", repository='" + repository + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
