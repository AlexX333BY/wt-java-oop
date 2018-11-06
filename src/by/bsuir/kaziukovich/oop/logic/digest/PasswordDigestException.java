package by.bsuir.kaziukovich.oop.logic.digest;

import by.bsuir.kaziukovich.oop.logic.command.CommandException;

/**
 * Exception to be thrown in case of password digest creation error
 */
public class PasswordDigestException extends CommandException {
    /**
     * Constructor for exception by message and cause
     * @param message Exception message
     * @param cause   Exception that cause this exception
     */
    public PasswordDigestException(String message, Throwable cause) {
        super(message, cause);
    }
}
