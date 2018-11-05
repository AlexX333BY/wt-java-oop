package by.bsuir.kaziukovich.oop.logic.user.impl;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user.UserDaoFactory;
import by.bsuir.kaziukovich.oop.logic.Command;
import by.bsuir.kaziukovich.oop.logic.CommandException;
import by.bsuir.kaziukovich.oop.logic.CommandResponse;

/**
 * Command for updating user password digest
 */
public class UpdateUserPasswordCommand implements Command {
    /**
     * Command required arguments count
     */
    public static final byte REQUIRED_ARGUMENTS = 2;

    /**
     * Command execution method
     * @param request Command request data. 2 required arguments: username and password digest
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length != REQUIRED_ARGUMENTS)) {
            throw new IllegalArgumentException(REQUIRED_ARGUMENTS + " arguments required");
        }

        try {
            UserDaoFactory.getUserDao().updateUser(request[0], request[1]);
        } catch (ExistanceException e) {
            throw new CommandException("Error executing UpdateUser command", e);
        }

        return new String[] { CommandResponse.SUCCESS_RESPONSE };
    }
}
