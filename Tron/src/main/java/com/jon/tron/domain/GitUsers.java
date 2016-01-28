package com.jon.tron.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 25.01.16
 */
public class GitUsers implements Serializable {

    List<GitUser> users = new ArrayList<GitUser>();

    public List<GitUser> getUsers() {
        return users;
    }

    public void setUsers(List<GitUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "GitUsers{" +
                "users=" + users +
                '}';
    }
}
