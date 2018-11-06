package by.bsuir.kaziukovich.oop.logic.command.user.impl;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user.UserDaoFactory;
import by.bsuir.kaziukovich.oop.datalayer.info.user.UserRole;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;

/**
 * Command for checking user administrator rights
 */
public class CheckAdminCommand implements Command {
    /**
     * Command required arguments count
     */
    public static final byte REQUIRED_ARGUMENTS = 1;

    /**
     * Command execution method
     * @param request Command request data. 1 argument required: username
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length != REQUIRED_ARGUMENTS)) {
            throw new IllegalArgumentException(REQUIRED_ARGUMENTS + " arguments required");
        }

        try {
            return new String[] { UserDaoFactory.getUserDao().get(request[0]).getUserRole().equals(UserRole.ADMIN)
                    ? CommandResponse.SUCCESS_RESPONSE : CommandResponse.FAILURE_RESPONSE };
        } catch (ExistanceException e) {
            throw new CommandException("Error executing CheckAdmin command", e);
        }
    }
}
