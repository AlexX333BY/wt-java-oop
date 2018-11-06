package by.bsuir.kaziukovich.oop.controller;

/**
 * Controller interface
 */
public interface Controller {
    /**
     * Processes user string request
     * @param username Username of request sender
     * @param request Request to process
     * @return Response
     * @throws ProcessException In case of any processing error
     */
    String[] process(String username, String request) throws ProcessException;
}
