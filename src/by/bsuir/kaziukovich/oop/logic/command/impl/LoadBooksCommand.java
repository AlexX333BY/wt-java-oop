package by.bsuir.kaziukovich.oop.logic.command.impl;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.StorageException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.book.BookDaoFactory;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;

/**
 * Command for loading data from file
 */
public class LoadBooksCommand implements Command {
    /**
     * Required arguments count for this command
     */
    public static final byte REQUIRED_ARGUMENTS = 1;

    /**
     * Command execution method
     * @param request Command request data - 1 strings: file path
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length != REQUIRED_ARGUMENTS)) {
            throw new IllegalArgumentException("Request should contain " + REQUIRED_ARGUMENTS + " element");
        }

        try {
            BookDaoFactory.getBookDao().loadFrom(request[0]);
        } catch (StorageException e) {
            throw new CommandException("Error executing Load command", e);
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
