package by.bsuir.kaziukovich.oop.logic.command.impl;

import by.bsuir.kaziukovich.oop.dataaccess.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccess.dao.user.UserDaoFactory;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;
import by.bsuir.kaziukovich.oop.logic.digest.PasswordDigestException;
import by.bsuir.kaziukovich.oop.logic.digest.PasswordDigestGeneratorFactory;

/**
 * Command to check user password propriety
 */
public class CheckPasswordCommand implements Command {
    /**
     * Required arguments count for this command
     */
    public static final byte REQUIRED_ARGUMENTS = 2;

    /**
     * Command execution method
     * @param request Command request data. 2 arguments required: username and password
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length != REQUIRED_ARGUMENTS)) {
            throw new IllegalArgumentException(REQUIRED_ARGUMENTS + " arguments required");
        }

        try {
            return new String[] { UserDaoFactory.getUserDao().get(request[0]).getPasswordDigest()
                    .equals(PasswordDigestGeneratorFactory.getPasswordDigestGenerator().generate(request[1]))
                    ? CommandResponse.SUCCESS_RESPONSE : CommandResponse.FAILURE_RESPONSE };
        } catch (ExistanceException | PasswordDigestException e) {
            throw new CommandException("Error executing CheckPassword command", e);
        }
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
