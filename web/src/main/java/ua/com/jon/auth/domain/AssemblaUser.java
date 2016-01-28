package ua.com.jon.auth.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/30/13
 */
@XStreamAlias("user")
public class AssemblaUser {
    private String id;
    private String login;
    private String name;
    private String picture;
    private String email;
    private String organization;
    private String phone;
    private String im;
    private String im2;

    public AssemblaUser() {
    }

    public AssemblaUser(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", email='" + email + '\'' +
                ", organization='" + organization + '\'' +
                ", phone='" + phone + '\'' +
                ", im='" + im + '\'' +
                ", im2='" + im2 + '\'' +
                '}';
    }
}
