package by.bsuir.kaziukovich.oop.logic.command.impl;

import by.bsuir.kaziukovich.oop.dataaccess.dao.user.UserDaoFactory;
import by.bsuir.kaziukovich.oop.domain.info.book.BookInfo;
import by.bsuir.kaziukovich.oop.domain.info.book.BookInfoFactory;
import by.bsuir.kaziukovich.oop.domain.info.book.BookType;
import by.bsuir.kaziukovich.oop.domain.info.user.UserInfo;
import by.bsuir.kaziukovich.oop.domain.info.user.UserRole;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;
import by.bsuir.kaziukovich.oop.logic.mail.MailerException;
import by.bsuir.kaziukovich.oop.logic.mail.MailerFactory;
import java.util.ArrayList;

public class SuggestBookCommand implements Command {
    /**
     * Required arguments count for this command
     */
    public static final byte REQUIRED_ARGUMENTS = 4;

    /**
     * Command execution method
     * @param request Command request data. 4 strings required: author, title, ISBN, book type
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        ArrayList<String> admins = new ArrayList<>();
        BookInfo newBookInfo;

        if ((request == null) || (request.length != REQUIRED_ARGUMENTS)) {
            throw new IllegalArgumentException(REQUIRED_ARGUMENTS + "arguments required");
        }

        newBookInfo = BookInfoFactory.createNew(request[1], request[0], request[2],
                BookType.valueOf(request[3].toUpperCase()));

        for (UserInfo user : UserDaoFactory.getUserDao().getAll()) {
            if (user.getUserRole() == UserRole.ADMIN) {
                admins.add(user.getUsername());
            }
        }

        try {
            if (admins.size() > 0) {
                MailerFactory.getBookMailer().mailTo(admins, "Book addition", newBookInfo,
                        "User suggests adding new book");
            }
        } catch (MailerException e) {
            throw new CommandException("Error executing SuggestBook command", e);
        }

        return new String[] { CommandResponse.SUCCESS_RESPONSE };
    }

    /**
     * Generates string representation of this object
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return getClass().getName();
    }
}
