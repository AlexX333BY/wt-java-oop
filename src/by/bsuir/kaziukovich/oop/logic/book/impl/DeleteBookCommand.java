package by.bsuir.kaziukovich.oop.logic.book.impl;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.book.BookDaoFactory;
import by.bsuir.kaziukovich.oop.logic.Command;
import by.bsuir.kaziukovich.oop.logic.CommandException;
import by.bsuir.kaziukovich.oop.logic.CommandResponse;

/**
 * Command for deleting book
 */
public class DeleteBookCommand implements Command {
    /**
     * Required arguments count for command
     */
    public static final byte REQUIRED_ARGUMENTS = 1;

    /**
     * Command execution method
     * @param request Command request data
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length != REQUIRED_ARGUMENTS)) {
            throw new IllegalArgumentException(REQUIRED_ARGUMENTS + "arguments required");
        }

        try {
            BookDaoFactory.getBookDao().deleteBook(request[0]);
        } catch (ExistanceException e) {
            throw new CommandException("Error executing DeleteBook command", e);
        }

        return new String[] { CommandResponse.SUCCESS_RESPONSE };
    }
}
