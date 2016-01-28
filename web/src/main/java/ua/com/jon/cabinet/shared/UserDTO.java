package ua.com.jon.cabinet.shared;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 19.04.14
 */
public class UserDTO implements Serializable {

    private String login;
    private Boolean isAdmin;
    private HashMap<String, Integer> marks;
    private HashMap<String, Boolean> presents;

    public UserDTO() {
    }

    public UserDTO(String login, HashMap<String, Integer> marks, HashMap<String, Boolean> presents) {
        this.login = login;
        this.marks = marks;
        this.presents = presents;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public HashMap<String, Integer> getMarks() {
        return marks;
    }

    public void setMarks(HashMap<String, Integer> marks) {
        this.marks = marks;
    }

    public HashMap<String, Boolean> getPresents() {
        return presents;
    }

    public void setPresents(HashMap<String, Boolean> presents) {
        this.presents = presents;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", marks=" + marks +
                ", presents=" + presents +
                '}';
    }
}
