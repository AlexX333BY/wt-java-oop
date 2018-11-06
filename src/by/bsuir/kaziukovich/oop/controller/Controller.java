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
     * @throws IllegalRoleException In case of trying to process request wtih another access level
     */
    String[] process(String username, String request) throws ProcessException, IllegalRoleException;
}
