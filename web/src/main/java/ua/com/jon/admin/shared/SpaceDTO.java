package ua.com.jon.admin.shared;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/2/13
 */
public class SpaceDTO implements Serializable {
    private Long id;
    private String name;
    private ArrayList<UserDTO> users;
    private String repositoryUrl;

    public SpaceDTO() {
    }

    public SpaceDTO(Long id, String name, ArrayList<UserDTO> users, String repositoryUrl) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.repositoryUrl = repositoryUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserDTO> users) {
        this.users = users;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SpaceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                ", repositoryUrl='" + repositoryUrl + '\'' +
                '}';
    }
}
