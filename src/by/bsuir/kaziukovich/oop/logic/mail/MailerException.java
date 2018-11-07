package by.bsuir.kaziukovich.oop.logic.mail;

/**
 * Exception to be thrown in case of mailing exception
 */
public class MailerException extends Exception {
    /**
     * Exception constructor by message and cause
     * @param message Exception message
     * @param cause Exception cause
     */
    public MailerException(String message, Throwable cause) {
        super(message, cause);
    }
}
