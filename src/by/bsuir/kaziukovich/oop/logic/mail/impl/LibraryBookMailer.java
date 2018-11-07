package by.bsuir.kaziukovich.oop.logic.mail.impl;

import by.bsuir.kaziukovich.oop.datalayer.info.book.BookInfo;
import by.bsuir.kaziukovich.oop.logger.Logger;
import by.bsuir.kaziukovich.oop.logic.mail.BookMailer;
import by.bsuir.kaziukovich.oop.logic.mail.MailerException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Mailer implementation for BookInfoClass
 */
public class LibraryBookMailer implements BookMailer {
    /**
     * Generates string representation of book to send by mail
     * @param book Book to create representation by
     * @return String representation of book to send by mail
     */
    private String createStringRepresentation(BookInfo book) {
        return String.format("Author: %s\nTitle: %s\nISBN: %s\nBook type: %s", book.getAuthor(), book.getTitle(),
                book.getIsbn(), book.getBookType().toString());
    }

    /**
     * Mails to single destination
     * @param destination       Mail destination
     * @param subject           Mail subject
     * @param mainMessageObject Mail message object
     * @param additionalMessage Additional text message, can be null
     */
    @Override
    public void mailTo(String destination, String subject, BookInfo mainMessageObject, String additionalMessage)
            throws MailerException {
        ArrayList<String> destinations = new ArrayList<>();

        destinations.add(destination);
        mailTo(destinations, subject, mainMessageObject, additionalMessage);
    }

    /**
     * Mails to multiple destinations
     * @param destinations      Mail destination
     * @param subject           Mail subject
     * @param mainMessageObject Mail message object
     * @param additionalMessage Additional text message, can be null
     */
    @Override
    public void mailTo(List<String> destinations, String subject, BookInfo mainMessageObject, String additionalMessage)
            throws MailerException {
        Properties properties = System.getProperties();
        String messageText;
        MimeMessage message;
        String hostname;

        if ((destinations == null) || (subject == null) || (mainMessageObject == null)) {
            throw new IllegalArgumentException("All but additionalMessage arguments shouldn't be null");
        }

        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            hostname = "";
            Logger.log(e);
        }

        properties.setProperty("mail.smtp.host", hostname);
        message = new MimeMessage(Session.getDefaultInstance(properties));
        messageText = createStringRepresentation(mainMessageObject);
        if (additionalMessage != null) {
            messageText = additionalMessage + "\n\n" + messageText;
        }

        try {
            message.setSubject("Book addition");
            message.setText(messageText);
        } catch (MessagingException e) {
            throw new MailerException("Error sending mails with subject " + subject, e);
        }

        for (String destination : destinations) {
            try {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(destination));
                Transport.send(message);
            } catch (MessagingException e) {
                Logger.log(new MailerException("Error sending mail to user " + destination, e));
            }
        }
    }
}
