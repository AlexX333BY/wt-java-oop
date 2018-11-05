package by.bsuir.kaziukovich.oop.logic;

/**
 * Command interface
 */
public interface Command {
    /**
     * Command execution method
     * @param request Command request data
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    String[] execute(String[] request) throws CommandException;
}
