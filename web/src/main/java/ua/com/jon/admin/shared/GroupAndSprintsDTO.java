package ua.com.jon.admin.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 07.03.15
 */
public class GroupAndSprintsDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Long groupId;
    private String name;
    private List<SprintDTO> sprints = new ArrayList<>();

    public GroupAndSprintsDTO() {
    }

    public GroupAndSprintsDTO(Long groupId, String name, List<SprintDTO> sprints) {
        this.groupId = groupId;
        this.name = name;
        this.sprints = sprints;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SprintDTO> getSprints() {
        return sprints;
    }

    public void setSprints(List<SprintDTO> sprints) {
        this.sprints = sprints;
    }

    @Override
    public String toString() {
        return "GroupAndSprintsDTO{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                '}';
    }
}
