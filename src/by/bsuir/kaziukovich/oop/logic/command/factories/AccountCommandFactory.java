package by.bsuir.kaziukovich.oop.logic.command.factories;

import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandName;
import by.bsuir.kaziukovich.oop.logic.command.impl.AddUserCommand;
import by.bsuir.kaziukovich.oop.logic.command.impl.CheckAdminCommand;
import by.bsuir.kaziukovich.oop.logic.command.impl.CheckPasswordCommand;
import by.bsuir.kaziukovich.oop.logic.command.impl.CheckUserExistCommand;
import java.util.HashMap;
import java.util.Map;

/**
 * Account info commands factory static class
 */
public class AccountCommandFactory {
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

        result.put(CommandName.CHECK_USER_EXIST, new CheckUserExistCommand());
        result.put(CommandName.ADD_USER, new AddUserCommand());
        result.put(CommandName.CHECK_PASSWORD, new CheckPasswordCommand());
        result.put(CommandName.CHECK_ADMIN, new CheckAdminCommand());
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
    private AccountCommandFactory() { }
}
