package ua.com.jon.auth.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@XStreamAlias("users")
public class AssemblaUsers {
    @XStreamImplicit
    List<AssemblaUser> users = new ArrayList<AssemblaUser>();

    public List<AssemblaUser> getUsers() {
        return users;
    }

    public void setUsers(List<AssemblaUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }
}