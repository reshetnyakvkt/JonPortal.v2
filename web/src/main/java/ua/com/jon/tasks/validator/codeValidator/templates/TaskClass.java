package ua.com.jon.tasks.validator.codeValidator.templates;


public class TaskClass {

    private String className;
    private String classCode;


    public TaskClass() {
    }

    public TaskClass(String className, String classCode) {
        this.className = className;
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}
