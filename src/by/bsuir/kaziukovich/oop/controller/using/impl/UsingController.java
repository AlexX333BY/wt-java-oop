package by.bsuir.kaziukovich.oop.controller.using.impl;

import by.bsuir.kaziukovich.oop.controller.Controller;
import by.bsuir.kaziukovich.oop.controller.ControllerResponse;
import by.bsuir.kaziukovich.oop.controller.ProcessException;
import by.bsuir.kaziukovich.oop.controller.impl.RequestSplitter;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandName;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;
import by.bsuir.kaziukovich.oop.logic.command.factories.AccountCommandFactory;
import by.bsuir.kaziukovich.oop.logic.command.factories.AdminCommandFactory;
import by.bsuir.kaziukovich.oop.logic.command.factories.UserCommandFactory;
import java.util.Arrays;

/**
 * Controller for part of program execution when it is using by its purpose
 */
public class UsingController implements Controller {
    /**
     * Processes user string request
     * @param username Username of request sender
     * @param request  Request to process
     * @return Response
     * @throws ProcessException     In case of any processing error
     */
    @Override
    public String[] process(String username, String request) throws ProcessException {
        Command executionCommand;
        String[] splittedRequest;
        String[] result;

        if ((username == null) || (request == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        splittedRequest = RequestSplitter.split(request.trim());
        try {
            if (AccountCommandFactory.getCommand(CommandName.CHECK_ADMIN.toString())
                    .execute(new String[]{username.trim()})[0].equals(CommandResponse.SUCCESS_RESPONSE)) {
                executionCommand = AdminCommandFactory.getCommand(splittedRequest[0]);
            } else {
                executionCommand = UserCommandFactory.getCommand(splittedRequest[0]);
            }
        } catch (CommandException e) {
            throw new ProcessException("Error while acquiring command", e);
        }

        try {
            result = executionCommand.execute(Arrays.copyOfRange(splittedRequest, 1, splittedRequest.length));
        } catch (CommandException e) {
            throw new ProcessException("Error while executing command", e);
        }

        result[0] = (result[0].equals(CommandResponse.SUCCESS_RESPONSE) ? ControllerResponse.SUCCESS_RESPONSE
                : ControllerResponse.FAILURE_RESPONSE);
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
