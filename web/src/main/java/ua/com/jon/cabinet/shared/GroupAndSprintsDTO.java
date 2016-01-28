package ua.com.jon.cabinet.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 07/06/15
 */
public class GroupAndSprintsDTO implements Serializable {
    private static final Long SerialVersionUID = 1L;

    private  Long groupId;
    private String name;
    private List<SprintDTO> sprints;

    public GroupAndSprintsDTO() {
        this.name = "";
        this.sprints = new ArrayList<>();
    }

    public GroupAndSprintsDTO(Long id, String name) {
        this.groupId = id;
        this.name = name;
    }

    public GroupAndSprintsDTO(Long id, String name, List<SprintDTO> sprints) {
        this(id, name);
        this.sprints = sprints;
    }

    public GroupAndSprintsDTO(String name) {
        this.name = name;
    }

    public GroupAndSprintsDTO(GroupDTO userGroup, ArrayList<SprintDTO> sprints) {
        this(userGroup.getId(), userGroup.getName());
        this.sprints = sprints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return groupId;
    }

    public void setId(Long groupId) {
        this.groupId = groupId;
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
                ", sprints=" + sprints +
                '}';
    }
}
