package com.jon.tron.service.compiler;

/**
 * Created with IntelliJ IDEA.
 * User: Julia
 * Date: 05.09.13
 * Time: 21:15
 * To change this template use File | Settings | File Templates.
 */
public class CompilationResult {
    private boolean isSuccess;

    public CompilationResult(boolean success) {
        isSuccess = success;
    }

    public Class getClassByName(String className) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
