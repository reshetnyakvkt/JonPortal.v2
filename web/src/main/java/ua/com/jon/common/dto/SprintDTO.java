package ua.com.jon.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 20.03.15
 */
public class SprintDTO {
    private Long id;
    private String name;
    private String type;
    private List<TaskDTO> tasks = new ArrayList<>();

    public SprintDTO() {
    }

    public SprintDTO(Long id) {
        this.id = id;
    }

    public SprintDTO(String name) {
        this.name = name;
    }

    public SprintDTO(Long id, String name, String type, List<TaskDTO> tasks) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", tasks=" + tasks +
                '}';
    }
}
