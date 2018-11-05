package by.bsuir.kaziukovich.oop.controller;

/**
 * Command interface
 */
public interface Command {
    /**
     * Interface for string commands
     * @param request Command request data
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    String[] execute(String[] request) throws CommandException;
}
