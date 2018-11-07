package by.bsuir.kaziukovich.oop.controller.login.impl;

import by.bsuir.kaziukovich.oop.logger.Logger;
import by.bsuir.kaziukovich.oop.controller.Controller;
import by.bsuir.kaziukovich.oop.controller.ControllerResponse;
import by.bsuir.kaziukovich.oop.controller.ProcessException;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandName;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;
import by.bsuir.kaziukovich.oop.logic.command.factories.AccountCommandFactory;
import by.bsuir.kaziukovich.oop.logic.command.factories.DataCommandFactory;

/**
 * Controller for login part of program execution
 */
public class LoginController implements Controller {
    /**
     * Processes user string request
     * @param username Username of request sender
     * @param request  Request to process - password
     * @return Response
     * @throws ProcessException     In case of any processing error
     */
    @Override
    public String[] process(String username, String request) throws ProcessException {
        String trimmedUsername, trimmedPassword;
        Command executionCommand;
        String[] result;
        boolean shouldUpdateUsers;

        if ((username == null) || (request == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        trimmedUsername = username.trim();
        trimmedPassword = request.trim();
        if ((trimmedUsername.length() == 0) || (trimmedPassword.length() == 0)) {
            throw new IllegalArgumentException("Arguments shouldn't be empty");
        }

        try {
            if (AccountCommandFactory.getCommand(CommandName.CHECK_USER_EXIST.toString())
                    .execute(new String[]{trimmedUsername})[0].equals(CommandResponse.SUCCESS_RESPONSE)) {
                executionCommand = AccountCommandFactory.getCommand(CommandName.CHECK_PASSWORD.toString());
                shouldUpdateUsers = false;
            } else {
                executionCommand = AccountCommandFactory.getCommand(CommandName.ADD_USER.toString());
                shouldUpdateUsers = true;
            }
        } catch (CommandException e) {
            throw new ProcessException("Error while acquiring command", e);
        }

        try {
            result = executionCommand.execute(new String[]{username, request});
        } catch (CommandException e) {
            throw new ProcessException("Error while executing command", e);
        }

        result[0] = (result[0].equals(CommandResponse.SUCCESS_RESPONSE) ? ControllerResponse.SUCCESS_RESPONSE
                : ControllerResponse.FAILURE_RESPONSE);

        if (shouldUpdateUsers) {
            try {
                DataCommandFactory.getCommand(CommandName.UPDATE_USERS.toString()).execute(null);
            } catch (CommandException e) {
                Logger.log(e);
            }
        }

        return result;
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
