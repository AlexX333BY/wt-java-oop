package by.bsuir.kaziukovich.oop.controller.login.impl;

import by.bsuir.kaziukovich.oop.controller.Controller;
import by.bsuir.kaziukovich.oop.controller.ControllerResponse;
import by.bsuir.kaziukovich.oop.controller.IllegalRoleException;
import by.bsuir.kaziukovich.oop.controller.ProcessException;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;
import by.bsuir.kaziukovich.oop.logic.command.user.UserCommandFactory;
import by.bsuir.kaziukovich.oop.logic.command.user.UserCommandName;

public class LoginController implements Controller {
    /**
     * Processes user string request
     * @param username Username of request sender
     * @param request  Request to process - password
     * @return Response
     * @throws ProcessException     In case of any processing error
     * @throws IllegalRoleException In case of trying to process request with another access level
     */
    @Override
    public String[] process(String username, String request) throws ProcessException, IllegalRoleException {
        String trimmedUsername, trimmedPassword;

        if ((username == null) || (request == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        trimmedUsername = username.trim();
        trimmedPassword = request.trim();
        if ((trimmedUsername.length() == 0) || (trimmedPassword.length() == 0)) {
            throw new IllegalArgumentException("Arguments shouldn't be empty");
        }

        try {
            if (UserCommandFactory.getCommand(UserCommandName.CHECK_EXIST.toString())
                    .execute(new String[]{trimmedUsername})[0].equals(CommandResponse.SUCCESS_RESPONSE)) {
                String[] result = UserCommandFactory.getCommand(UserCommandName.CHECK_PASSWORD.toString())
                        .execute(new String[]{trimmedUsername, trimmedPassword});

                result[0] = (result[0].equals(CommandResponse.SUCCESS_RESPONSE) ? ControllerResponse.SUCCESS_RESPONSE
                        : ControllerResponse.FAILURE_RESPONSE);
                return result;
            } else {
                String[] result = UserCommandFactory.getCommand(UserCommandName.ADD_USER.toString())
                        .execute(new String[]{trimmedUsername, trimmedPassword});

                result[0] = (result[0].equals(CommandResponse.SUCCESS_RESPONSE) ? ControllerResponse.SUCCESS_RESPONSE
                        : ControllerResponse.FAILURE_RESPONSE);
                return result;
            }
        } catch (CommandException e) {
            throw new ProcessException("Error while login", e);
        }
    }
}
