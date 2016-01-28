package ua.com.jon.tasks.validator.sourceCompiler;


public class CompilationError {

    private Long line;
    private String message;

    public CompilationError(Long line, String message) {
        this.line = line;
        this.message = message;
    }

    public Long getLine() {
        return line;
    }

    public void setLine(Long line) {
        this.line = line;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
