package ua.com.jon.auth.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 26.04.14
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class GitHubUser implements Serializable {

    private String login;

    public GitHubUser(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "login='" + login + '\'' +
                '}';
    }
}
