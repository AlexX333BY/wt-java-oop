package by.bsuir.kaziukovich.oop.controller;

/**
 * Exception to be thrown in case of trying to process request with another access level
 */
public class IllegalRoleException extends Exception {
    /**
     * Creates exception with message
     * @param message Message of exception
     */
    public IllegalRoleException(String message) {
        super(message);
    }
}
