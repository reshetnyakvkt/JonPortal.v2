package ua.com.jon.common.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 27.05.13
 */
@Entity
@Table(name = "TASK_TEMPLATES")
public class TaskTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10000)
    private String taskText;

    private String name;

    @Column(length = 10000)
    private String materials;

    @Column(name = "MODULE_SUFFIX", columnDefinition = "varchar(50) default ''")
    private String suffix;

    @OneToMany(mappedBy = "taskTemplate", cascade = CascadeType.ALL)
    private Set<Task> tasks = new HashSet<Task>();

    @Column
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @Column(length = 50)
    private String testName;

    @Column(columnDefinition = "varchar(50) default ''")
    private String className;

    public TaskTemplate() {
    }

    public TaskTemplate(String name, String taskText) {
        this.name = name;
        this.taskText = taskText;
    }

    public TaskTemplate(Long id, String taskText, String name, TaskType type, String moduleSuffix, String materials,
                        String testName, String className) {
        this.id = id;
        this.taskText = taskText;
        this.name = name;
        this.type = type;
        this.suffix = moduleSuffix;
        this.materials = materials;
        this.testName = testName;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String className) {
        this.suffix = className;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "TaskTemplate{" +
                "id=" + id +
                ", taskText='" + taskText + '\'' +
                ", name='" + name + '\'' +
                ", materials=" + materials +
                ", moduleSuffix='" + suffix + '\'' +
                ", tasks=" + tasks +
                ", type=" + (type==null?"":type.name()) +
                ", testName='" + testName + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
