package by.bsuir.kaziukovich.oop.logic.command.impl;

import by.bsuir.kaziukovich.oop.dataaccess.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccess.dao.book.BookDaoFactory;
import by.bsuir.kaziukovich.oop.domain.info.book.BookType;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;

/**
 * Command for updating book
 */
public class UpdateBookCommand implements Command {
    /**
     * Required arguments count for command
     */
    public static final byte REQUIRED_ARGUMENTS = 4;

    /**
     * Command execution method
     * @param request Command request data. 4 strings required: ISBN, author, title, book type
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length != REQUIRED_ARGUMENTS)) {
            throw new IllegalArgumentException(REQUIRED_ARGUMENTS + "arguments required");
        }

        try {
            BookDaoFactory.getBookDao().updateBook(request[0], request[2], request[1],
                    BookType.valueOf(request[3].toUpperCase()));
        } catch (ExistanceException e) {
            throw new CommandException("Error executing UpdateBook command", e);
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
