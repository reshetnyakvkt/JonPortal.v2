package ua.com.jon.cabinet.shared;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 1/13/14
 */
public class GroupDTO implements Serializable {
    private static final Long SerialVersionUID = 1L;

    private  Long groupId;
    private String name;

    public GroupDTO() {
        this.name = "";
    }

    public GroupDTO(Long id, String name) {
        this.groupId = id;
        this.name = name;
    }

    public GroupDTO(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "GroupDTO{" +
                "name='" + name + '\'' +
                ", id=" + groupId +
                '}';
    }
}
