package by.bsuir.kaziukovich.oop.logic.command.factories;

import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandName;
import by.bsuir.kaziukovich.oop.logic.command.impl.GetAllBooksCommand;
import by.bsuir.kaziukovich.oop.logic.command.impl.SearchBookCommand;
import by.bsuir.kaziukovich.oop.logic.command.impl.SuggestBookCommand;
import java.util.HashMap;
import java.util.Map;

/**
 * User commands factory static class
 */
public class UserCommandFactory {
    /**
     * Command type and implementation accordance map
     */
    private static final Map<CommandName, Command> commands = createCommandMap();

    /**
     * Method for getting command type and implementation accordance map
     * @return Command type and implementation accordance map
     */
    private static Map<CommandName, Command> createCommandMap() {
        Map<CommandName, Command> result = new HashMap<>();

        result.put(CommandName.GET_ALL_BOOKS, new GetAllBooksCommand());
        result.put(CommandName.ADD_BOOK, new SuggestBookCommand());
        result.put(CommandName.SEARCH_BOOK, new SearchBookCommand());
        return result;
    }

    /**
     * Method for acquiring command implementation by its name
     * @param commandName Name of command
     * @return Command implementation or null if no command with such name exists
     */
    public static Command getCommand(String commandName) {
        if (commandName == null) {
            throw new IllegalArgumentException("Command name shouldn't be null");
        }
        return commands.get(CommandName.valueOf(commandName.toUpperCase()));
    }

    /**
     * Private constructor to avoid object creation
     */
    private UserCommandFactory() { }
}
