package by.bsuir.kaziukovich.oop.logic.command.user.impl;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user.UserDaoFactory;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;

/**
 * Command for deleting user
 */
public class DeleteUserCommand implements Command {
    /**
     * Required arguments count for this command
     */
    public static final byte REQUIRED_COMMANDS = 1;

    /**
     * Command execution method
     * @param request Command request data. 1 argument required: username
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length != REQUIRED_COMMANDS)) {
            throw new IllegalArgumentException(REQUIRED_COMMANDS + " arguments required");
        }

        try {
            UserDaoFactory.getUserDao().deleteUser(request[0]);
        } catch (ExistanceException e) {
            throw new CommandException("Error executing DeleteUser command", e);
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
