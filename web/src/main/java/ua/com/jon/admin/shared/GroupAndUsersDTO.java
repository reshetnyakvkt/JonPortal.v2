package ua.com.jon.admin.shared;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 09.11.13
 */
public class GroupAndUsersDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String name;
    private HashSet<UserDTO> users;
    private String code;

    public GroupAndUsersDTO() {
        this.name = "";
        users = new HashSet<UserDTO>();
    }

    public GroupAndUsersDTO(Long id, String name, HashSet<UserDTO> users, String code) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(HashSet<UserDTO> users) {
        this.users = users;
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

    @Override
    public String toString() {
        return "GroupAndUsersDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                ", code=" + code +
                '}';
    }
}
