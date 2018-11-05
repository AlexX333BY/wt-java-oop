package by.bsuir.kaziukovich.oop.controller;

/**
 * Exception to be thrown in case of command execution error
 */
public class CommandException extends Exception {
    /**
     * Constructor for exception by message and cause
     * @param message Exception message
     * @param cause Exception that cause this exception
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
