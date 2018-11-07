package by.bsuir.kaziukovich.oop.logic.mail;

import by.bsuir.kaziukovich.oop.logic.mail.impl.LibraryBookMailer;

/**
 * Factory for acquiring Mailer instances
 */
public class MailerFactory {
    /**
     * Single BookMailer instance
     */
    private static final BookMailer bookMailer = new LibraryBookMailer();

    /**
     * Generates BookMailer instance
     * @return BookMailer instance
     */
    public static BookMailer getBookMailer() {
        return bookMailer;
    }

    /**
     * Private constructor to avoid object creation
     */
    private MailerFactory() { }
}
