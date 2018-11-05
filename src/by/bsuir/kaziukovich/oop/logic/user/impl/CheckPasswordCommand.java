package by.bsuir.kaziukovich.oop.logic.user.impl;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user.UserDaoFactory;
import by.bsuir.kaziukovich.oop.logic.Command;
import by.bsuir.kaziukovich.oop.logic.CommandException;
import by.bsuir.kaziukovich.oop.logic.CommandResponse;

/**
 * Command to check user password digest propriety
 */
public class CheckPasswordCommand implements Command {
    /**
     * Required arguments count for this command
     */
    public static final byte REQUIRED_ARGUMENTS = 2;

    /**
     * Command execution method
     * @param request Command request data. 2 arguments required: username and password digest
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length != REQUIRED_ARGUMENTS)) {
            throw new IllegalArgumentException(REQUIRED_ARGUMENTS + " arguments required");
        }

        try {
            return new String[] { UserDaoFactory.getUserDao().get(request[0]).getPasswordDigest().equals(request[1])
                    ? CommandResponse.SUCCESS_RESPONSE : CommandResponse.FAILURE_RESPONSE };
        } catch (ExistanceException e) {
            throw new CommandException("Error executing CheckPassword command", e);
        }
    }
}