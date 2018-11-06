package by.bsuir.kaziukovich.oop.controller.data.impl;

import by.bsuir.kaziukovich.oop.controller.Controller;
import by.bsuir.kaziukovich.oop.controller.ControllerResponse;
import by.bsuir.kaziukovich.oop.controller.ProcessException;
import by.bsuir.kaziukovich.oop.controller.impl.RequestSplitter;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;
import by.bsuir.kaziukovich.oop.logic.command.factories.DataCommandFactory;
import java.util.Arrays;

/**
 * Controller for data loading and updating part of program
 */
public class DataController implements Controller {
    /**
     * Processes user string request
     * @param username Username of request sender. Ignored
     * @param request  Request to process
     * @return Response
     * @throws ProcessException In case of any processing error
     */
    @Override
    public String[] process(String username, String request) throws ProcessException {
        String[] splittedRequest;

        if (request == null) {
            throw new IllegalArgumentException("Request shouldn't be null");
        }

        splittedRequest = RequestSplitter.split(request.trim());
        try {
            String[] result = DataCommandFactory.getCommand(splittedRequest[0])
                    .execute(Arrays.copyOfRange(splittedRequest, 1, splittedRequest.length));

            result[0] = (result[0].equals(CommandResponse.SUCCESS_RESPONSE) ? ControllerResponse.SUCCESS_RESPONSE
                    : ControllerResponse.FAILURE_RESPONSE);
            return result;
        } catch (CommandException e) {
            throw new ProcessException("Error while executing data command", e);
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
