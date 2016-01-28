package ua.com.jon.common.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 10.09.14
 */
public class UserDTO implements Serializable {
    private Long id;
    private String userName;
    private Boolean admin = false;
    private Boolean ignore = false;

    public UserDTO() {

    }

    public UserDTO(Long id, String userName, Boolean admin, Boolean ignore) {
        this.id = id;
        this.userName = userName;
        this.admin = admin;
        this.ignore = ignore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(Boolean ignore) {
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", admin='" + admin + '\'' +
                ", ignore=" + ignore +
                '}';
    }
}
