package ua.com.jon.admin.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 20.04.13
 * Time: 21:22
 */
public class TaskTemplateDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String text;
    private String type;
    private String suffix;
    private String testName;
    private String materials;
    private String className;

    private List<TaskDTO> tasks = new ArrayList<>();

    public TaskTemplateDTO() {
    }

    public TaskTemplateDTO(String name) {
        this.name = name;
    }

    public TaskTemplateDTO(Long id) {
        this.id = id;
    }

    public TaskTemplateDTO(String name, String text, String type) {
        this.name = name;
        this.text = text;
        this.type = type;
    }

    public TaskTemplateDTO(Long id, String name, String text, String type, String className, String testName, String materials) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.type = type;
        this.suffix = className;
        this.testName = testName;
        this.materials = materials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskTemplateDTO that = (TaskTemplateDTO) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "TaskTemplateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", suffix='" + suffix + '\'' +
                ", testName='" + testName + '\'' +
                ", materials='" + materials + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
