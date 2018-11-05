package by.bsuir.kaziukovich.oop.logic.user.impl;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user.UserDaoFactory;
import by.bsuir.kaziukovich.oop.logic.Command;
import by.bsuir.kaziukovich.oop.logic.CommandException;
import by.bsuir.kaziukovich.oop.logic.CommandResponse;

/**
 * Command for checking user existance
 */
public class CheckExistCommand implements Command {
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
            UserDaoFactory.getUserDao().get(request[0]);
            return new String[] { CommandResponse.SUCCESS_RESPONSE };
        } catch (ExistanceException e) {
            return new String[] { CommandResponse.FAILURE_RESPONSE };
        }
    }
}
