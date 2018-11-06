package by.bsuir.kaziukovich.oop.controller;

/**
 * Exception to be thrown in case of users request process error
 */
public class ProcessException extends Exception {
    /**
     * Creates exception with message
     * @param message Message of exception
     */
    public ProcessException(String message) {
        super(message);
    }

    /**
     * Creates exception with message and cause
     * @param message Message of exception
     * @param cause Cause of exception
     */
    public ProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
