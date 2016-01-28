package ua.com.jon.tasks.validator.sourceCompiler;


import java.util.ArrayList;

public class CompilationResult {

    private boolean isSuccess;

    private ArrayList<CompilationError> compilationErrors =new ArrayList<CompilationError>();

    private ClassFileManager classFileManager;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public ArrayList<CompilationError> getCompilationErrors() {
        return compilationErrors;
    }

    public void setCompilationErrors(ArrayList<CompilationError> compilationErrors) {
        this.compilationErrors = compilationErrors;
    }

    public void addCompilationError(Long line, String message) {
        compilationErrors.add(new CompilationError(line, message));
    }

    ClassFileManager getClassFileManager() {
        return classFileManager;
    }

    void setClassFileManager(ClassFileManager classFileManager) {
        this.classFileManager = classFileManager;
    }

    public Class getClassByName(String className) throws Exception {
        if (!isSuccess) throw new Exception("Project not compiled");
        return classFileManager.getClassByName(className).loadClass(className);
    }
}
