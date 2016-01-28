package ua.com.jon.tasks.validator.codeValidator.templates;


//import com.google.gson.Gson;
import ua.com.jon.tasks.validator.codeValidator.jsonObjects.JSONTaskResponse;
import ua.com.jon.tasks.validator.sourceCompiler.CompilationError;
import ua.com.jon.tasks.validator.sourceCompiler.CompilationResult;
import ua.com.jon.tasks.validator.sourceCompiler.SourceCompiler;

import java.util.ArrayList;


public abstract class CodeValidator {

    private String taskName;
    private String pseudoName;
    private String taskDescription;

    public abstract void testClass(Class clazz, JSONTaskResponse jsonResponse) throws Exception;

    protected ArrayList<TaskClass> taskClasses =new ArrayList<TaskClass>();


    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public ArrayList<TaskClass> getTaskClasses() {
        return taskClasses;
    }

    public void setTaskClasses(ArrayList<TaskClass> taskClasses) {
        this.taskClasses = taskClasses;
    }

    public void addTaskClass(String className, String classCode) {
        TaskClass taskClass=new TaskClass(className,classCode);
        taskClasses.add(taskClass);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPseudoName() {
        return pseudoName;
    }

    public void setPseudoName(String pseudoName) {
        this.pseudoName = pseudoName;
    }



    public String validateCode(ArrayList<TaskClass> taskClasses, String targetClassName) {
        SourceCompiler sourceCompiler = new SourceCompiler();
        CompilationResult compilationResult;
        JSONTaskResponse jsonResponse=new JSONTaskResponse();
        try {
            // компиляция
            compilationResult = sourceCompiler.compileSourceCode(taskClasses);
            jsonResponse.setCompilation_success(compilationResult.isSuccess());
            for (CompilationError compilationError : compilationResult.getCompilationErrors()) {
                jsonResponse.addCompilationError(compilationError.getLine(),compilationError.getMessage());
            }
            // тестирование
            try {
                Class testClass=compilationResult.getClassByName(targetClassName);
                testClass(testClass,jsonResponse);
            } catch (Exception e) {
                jsonResponse.setCompilation_success(false);
                jsonResponse.addCompilationError(1L,e.getMessage());
            }
        } catch (Exception e) {
             e.printStackTrace();
            jsonResponse.setCompilation_success(false);
            jsonResponse.addCompilationError(1L,e.getMessage());
        }
//        Gson gson=new Gson();
//        return gson.toJson(jsonResponse);
        return null;
    }

}
