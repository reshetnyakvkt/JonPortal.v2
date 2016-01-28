package ua.com.jon.tasks.validator.codeValidator.jsonObjects;


public class TestMessage {

    private boolean test_success;

    private String message;

    public TestMessage() {
    }

    public TestMessage(boolean test_success, String message) {
        this.test_success = test_success;
        this.message = message;
    }

    public boolean isTest_success() {
        return test_success;
    }

    public void setTest_success(boolean test_success) {
        this.test_success = test_success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
