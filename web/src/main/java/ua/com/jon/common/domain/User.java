package ua.com.jon.common.domain;

import ua.com.jon.auth.domain.UserRole;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Date: 01.01.13
 * Time: 23:58
 */
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "LOGIN", unique = true, length = 30)
    private String login;

    @Column(name = "PASSWORD", length = 30)
    private String password;

    @Column(name = "MAIL", length = 30)
    private String mail;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "REGISTER_DATE", nullable = false)
    private Date regDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Task> tasks = new HashSet<Task>();

    @ManyToMany
    //@JoinColumn(name = "GROUP_ID")
    private Set<Group> groups = new HashSet<Group>();

    @ManyToMany
    private Set<Sprint> sprints = new HashSet<Sprint>();

    @ElementCollection(targetClass=UserRole.class, fetch= FetchType.EAGER)
    @Column(length=32, nullable=false )
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<UserRole>();

    @Column(name="IGNORE_STATISTIC", length=1, nullable=false, columnDefinition="bit(1) default 0")
    private Boolean ignore = false;

    public User() {
    }

    public User(Long id, String login, String password, Date regDate, Set<Group> groups, Set<UserRole> roles, boolean ignore) {
        this(login, password, regDate, groups, roles, ignore);
        this.id = id;
    }

    public User(String login, String password, Date regDate, Set<Group> groups, Set<UserRole> roles, boolean ignore) {
        this.login = login;
        this.password = password;
        this.regDate = regDate;
        this.groups = groups;
        this.roles = roles;
        this.ignore = ignore;
    }

    public Boolean isAdmin() {
        return roles.contains(UserRole.ROLE_ADMIN);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

/*    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }*/

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> role) {
        this.roles = role;
    }

    public Set<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
    }

    public Boolean getIgnore() {
        return ignore;
    }

    public void setIgnore(Boolean ignore) {
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", regDate=" + regDate +
                ", groups=" + groups.size() +
                ", roles=" + roles.size() +
                ", ignore=" + ignore +
                '}';
    }
}
