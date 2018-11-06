package by.bsuir.kaziukovich.oop.controller.using.impl;

import by.bsuir.kaziukovich.oop.controller.Controller;
import by.bsuir.kaziukovich.oop.controller.ProcessException;

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
        return new String[0];
    }
}
