package by.bsuir.kaziukovich.oop.logic.user.impl;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.NeverLoadedException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.StorageException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user.UserDaoFactory;
import by.bsuir.kaziukovich.oop.logic.Command;
import by.bsuir.kaziukovich.oop.logic.CommandException;
import by.bsuir.kaziukovich.oop.logic.CommandResponse;

/**
 * Command for updating data in source
 */
public class UpdateDataCommand implements Command {
    /**
     * Command execution method
     * @param request Command request data. Ignored
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        try {
            UserDaoFactory.getUserDao().updateData();
        } catch (StorageException | NeverLoadedException e) {
            throw new CommandException("Error executing Update command", e);
        }

        return new String[] { CommandResponse.SUCCESS_RESPONSE };
    }
}
