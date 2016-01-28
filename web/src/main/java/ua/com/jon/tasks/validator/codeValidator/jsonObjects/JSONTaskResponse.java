package ua.com.jon.tasks.validator.codeValidator.jsonObjects;


import java.util.ArrayList;

public class JSONTaskResponse {


    private boolean compilation_success;

    private ArrayList<CompilationError> compilation_errors=new ArrayList<CompilationError>();

    private ArrayList<TestMessage> test_messages=new ArrayList<TestMessage>();

    public boolean isCompilation_success() {
        return compilation_success;
    }

    public void setCompilation_success(boolean compilation_success) {
        this.compilation_success = compilation_success;
    }

    public ArrayList<CompilationError> getCompilation_errors() {
        return compilation_errors;
    }

    public void setCompilation_errors(ArrayList<CompilationError> compilation_errors) {
        this.compilation_errors = compilation_errors;
    }

    public ArrayList<TestMessage> getTest_messages() {
        return test_messages;
    }

    public void setTest_messages(ArrayList<TestMessage> test_messages) {
        this.test_messages = test_messages;
    }

    public void addCompilationError(Long line, String message) {
        compilation_errors.add(new CompilationError(line,message));
    }

    public void addTestMessage(boolean isSuccess, String message) {
        test_messages.add(new TestMessage(isSuccess,message));
    }
}
