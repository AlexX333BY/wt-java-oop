package by.bsuir.kaziukovich.oop.logic.command.impl;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user.UserDaoFactory;
import by.bsuir.kaziukovich.oop.datalayer.info.user.UserRole;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;

/**
 * Command for updating user role
 */
public class UpdateUserRoleCommand implements Command {
    /**
     * Command required arguments count
     */
    public static final byte REQUIRED_ARGUMENTS = 2;

    /**
     * Command execution method
     * @param request Command request data. 2 required arguments: username and user role
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length != REQUIRED_ARGUMENTS)) {
            throw new IllegalArgumentException(REQUIRED_ARGUMENTS + " arguments required");
        }

        try {
            UserDaoFactory.getUserDao().updateUser(request[0], UserRole.valueOf(request[1].toUpperCase()));
        } catch (ExistanceException e) {
            throw new CommandException("Error executing UpdateUserRole command", e);
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
