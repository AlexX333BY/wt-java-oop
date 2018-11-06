package by.bsuir.kaziukovich.oop.logic.command.user;

import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.user.impl.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User commands factory static class
 */
public class UserCommandFactory {
    /**
     * Command type and implementation accordance map
     */
    private static Map<UserCommandName, Command> commands = createCommandMap();

    /**
     * Method for getting command type and implementation accordance map
     * @return Command type and implementation accordance map
     */
    private static Map<UserCommandName, Command> createCommandMap() {
        Map<UserCommandName, Command> result = new HashMap<>();

        result.put(UserCommandName.ADD_USER, new AddUserCommand());
        result.put(UserCommandName.CHECK_PASSWORD, new CheckPasswordCommand());
        result.put(UserCommandName.DELETE_USER, new DeleteUserCommand());
        result.put(UserCommandName.LOAD_DATA, new LoadDataCommand());
        result.put(UserCommandName.UPDATE_DATA, new UpdateDataCommand());
        result.put(UserCommandName.CHECK_ADMIN, new CheckAdminCommand());
        result.put(UserCommandName.CHECK_EXIST, new CheckExistCommand());
        result.put(UserCommandName.UPDATE_USER_PASSWORD, new UpdateUserPasswordCommand());
        result.put(UserCommandName.UPDATE_USER_ROLE, new UpdateUserRoleCommand());
        return result;
    }

    /**
     * Method for acquiring command implementation by its name
     * @param commandName Name of command
     * @return Command implementation or null if no command with such name exists
     */
    public static Command getCommand(String commandName) {
        return commands.get(UserCommandName.valueOf(commandName.toUpperCase()));
    }

    /**
     * Private constructor to avoid object creation
     */
    private UserCommandFactory() { }
}
