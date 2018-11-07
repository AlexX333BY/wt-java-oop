package by.bsuir.kaziukovich.oop.logic.command.impl;

import by.bsuir.kaziukovich.oop.dataaccess.dao.NeverLoadedException;
import by.bsuir.kaziukovich.oop.dataaccess.dao.StorageException;
import by.bsuir.kaziukovich.oop.dataaccess.dao.book.BookDaoFactory;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;

/**
 * Command for updating data in file
 */
public class UpdateBooksCommand implements Command {
    /**
     * Command execution method
     * @param request Command request data. Not used
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        try {
            BookDaoFactory.getBookDao().updateData();
        } catch (StorageException | NeverLoadedException e) {
            throw new CommandException("Error executing Update command", e);
        }

        return new String[] {CommandResponse.SUCCESS_RESPONSE };
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
