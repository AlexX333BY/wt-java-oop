package by.bsuir.kaziukovich.oop.logic.command.factories;

import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandName;
import by.bsuir.kaziukovich.oop.logic.command.impl.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Administrator commands factory static class
 */
public class AdminCommandFactory {
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

        result.put(CommandName.ADD_BOOK, new AddBookCommand());
        result.put(CommandName.ADD_USER, new AddUserCommand());
        result.put(CommandName.DELETE_BOOK, new DeleteBookCommand());
        result.put(CommandName.DELETE_USER, new DeleteUserCommand());
        result.put(CommandName.GET_ALL_BOOKS, new GetAllBooksCommand());
        result.put(CommandName.UPDATE_BOOK, new UpdateBookCommand());
        result.put(CommandName.UPDATE_BOOKS, new UpdateBooksCommand());
        result.put(CommandName.UPDATE_USER_PASSWORD, new UpdateUserPasswordCommand());
        result.put(CommandName.UPDATE_USER_ROLE, new UpdateUserRoleCommand());
        result.put(CommandName.UPDATE_USERS, new UpdateUsersCommand());
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
    private AdminCommandFactory() { }
}
