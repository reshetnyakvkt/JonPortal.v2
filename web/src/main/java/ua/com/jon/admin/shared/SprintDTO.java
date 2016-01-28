package ua.com.jon.admin.shared;

import ua.com.jon.admin.client.components.list.Nameble;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/23/13
 */
public class SprintDTO implements Serializable, Nameble {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String type;
    private boolean active;
    private Date endDate;
    private List<TaskTemplateDTO> tasks = new ArrayList<>();

    public SprintDTO() {
    }

    public SprintDTO(Long id) {
        this.id = id;
    }

    public SprintDTO(String name) {
        this.name = name;
    }

    public SprintDTO(Long id, String name, boolean active, String type, List<TaskTemplateDTO> tasks, Date endDate) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.type = type;
        this.tasks = tasks;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskTemplateDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskTemplateDTO> tasks) {
        this.tasks = tasks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SprintDTO sprintDTO = (SprintDTO) o;

        if (!id.equals(sprintDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "SprintDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", active=" + active +
                ", tasks=" + tasks +
                ", endDate=" + endDate +
                '}';
    }
}
