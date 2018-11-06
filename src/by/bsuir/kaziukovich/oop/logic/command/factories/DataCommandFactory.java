package by.bsuir.kaziukovich.oop.logic.command.factories;

import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandName;
import by.bsuir.kaziukovich.oop.logic.command.impl.LoadBooksCommand;
import by.bsuir.kaziukovich.oop.logic.command.impl.LoadUsersCommand;
import by.bsuir.kaziukovich.oop.logic.command.impl.UpdateBooksCommand;
import by.bsuir.kaziukovich.oop.logic.command.impl.UpdateUsersCommand;
import java.util.HashMap;
import java.util.Map;

/**
 * Data commands factory static class
 */
public class DataCommandFactory {
    /**
     * Command type and implementation accordance map
     */
    private static Map<CommandName, Command> commands = createCommandMap();

    /**
     * Method for getting command type and implementation accordance map
     * @return Command type and implementation accordance map
     */
    private static Map<CommandName, Command> createCommandMap() {
        Map<CommandName, Command> result = new HashMap<>();

        result.put(CommandName.LOAD_BOOKS, new LoadBooksCommand());
        result.put(CommandName.LOAD_USERS, new LoadUsersCommand());
        result.put(CommandName.UPDATE_BOOKS, new UpdateBooksCommand());
        result.put(CommandName.UPDATE_USERS, new UpdateUsersCommand());
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
    private DataCommandFactory() { }
}
