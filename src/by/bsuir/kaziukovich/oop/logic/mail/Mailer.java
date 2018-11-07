package by.bsuir.kaziukovich.oop.logic.mail;

import java.util.List;

/**
 * Mailer interface for mailing concrete objects
 * @param <T> Concrete mail objects
 */
public interface Mailer<T> {
    /**
     * Mails to single destination
     * @param destination Mail destination
     * @param subject Mail subject
     * @param mainMessageObject Mail message object
     * @param additionalMessage Additional text message, can be null
     */
    void mailTo(String destination, String subject, T mainMessageObject, String additionalMessage)
            throws MailerException;

    /**
     * Mails to multiple destinations
     * @param destinations Mail destination
     * @param subject Mail subject
     * @param mainMessageObject Mail message object
     * @param additionalMessage Additional text message, can be null
     */
    void mailTo(List<String> destinations, String subject, T mainMessageObject, String additionalMessage)
            throws MailerException;
}
