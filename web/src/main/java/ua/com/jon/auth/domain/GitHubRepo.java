package ua.com.jon.auth.domain;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 26.04.14
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class GitHubRepo implements Serializable {

    private String name;
    private Set<GitHubUser> users;

    public GitHubRepo(String name, Set<GitHubUser> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GitHubUser> getUsers() {
        return users;
    }

    public void setUsers(Set<GitHubUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "GitHubRepo{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
