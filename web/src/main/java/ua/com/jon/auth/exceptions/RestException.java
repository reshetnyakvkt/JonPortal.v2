package ua.com.jon.auth.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/31/13
 */
public class RestException extends Exception {
    public RestException(String message) {
        super(message);
    }
    public RestException(String message, Exception e) {
        super(message, e);
    }
}
