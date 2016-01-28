package ua.com.jon.common.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/28/13
 */
@Entity
@Table(name = "SPRINTS")
public class Sprint implements Comparable<Sprint> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    private Boolean active;

    @Column(name = "sprint_type")
    @Enumerated(EnumType.STRING)
    private SprintType type;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "TASK_TEMPLATE_ID")
    private List<TaskTemplate> tasks;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "sprints")
    private Set<User> users = new HashSet<User>();

    @Temporal(TemporalType.DATE)
    private Date endDate;

    public Sprint() {
    }

    public Sprint(Long id, String name, SprintType type, Date endDate, Boolean active) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.endDate = endDate;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SprintType getType() {
        return type;
    }

    public void setType(SprintType type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<TaskTemplate> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskTemplate> tasks) {
        this.tasks = tasks;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sprint sprint = (Sprint) o;

        if (id == null || !id.equals(sprint.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", type=" + type +
                ", tasks=" + tasks +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public int compareTo(Sprint o) {
        if (o == null) {
            return -1;
        }
        return name.compareTo(o.getName());
    }
}
