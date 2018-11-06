package by.bsuir.kaziukovich.oop.logic.digest;

/**
 * Exception to be thrown in case of password digest creation error
 */
public class PasswordDigestException extends Exception {
    /**
     * Constructor for exception by message and cause
     * @param message Exception message
     * @param cause   Exception that cause this exception
     */
    public PasswordDigestException(String message, Throwable cause) {
        super(message, cause);
    }
}
