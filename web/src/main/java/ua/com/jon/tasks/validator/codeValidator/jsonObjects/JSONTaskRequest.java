package ua.com.jon.tasks.validator.codeValidator.jsonObjects;


import ua.com.jon.tasks.validator.codeValidator.templates.TaskClass;

import java.util.ArrayList;

public class JSONTaskRequest {

    private String taskName;
    private ArrayList<TaskClass> taskClasses;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public ArrayList<TaskClass> getTaskClasses() {
        return taskClasses;
    }

    public void setTaskClasses(ArrayList<TaskClass> taskClasses) {
        this.taskClasses = taskClasses;
    }

    @Override
    public String toString() {
        return "JSONTaskRequest{" +
                "taskName='" + taskName + '\'' +
                ", taskClasses=" + taskClasses +
                '}';
    }
}
