package by.bsuir.kaziukovich.oop.logic.command.book;

import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.book.impl.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Book commands factory static class
 */
public class BookCommandFactory {
    /**
     * Command type and implementation accordance map
     */
    private static Map<BookCommandName, Command> commands = createCommandMap();

    /**
     * Method for getting command type and implementation accordance map
     * @return Command type and implementation accordance map
     */
    private static Map<BookCommandName, Command> createCommandMap() {
        Map<BookCommandName, Command> result = new HashMap<>();

        result.put(BookCommandName.ADD_BOOK, new AddBookCommand());
        result.put(BookCommandName.DELETE_BOOK, new DeleteBookCommand());
        result.put(BookCommandName.GET_ALL, new GetAllBooksCommand());
        result.put(BookCommandName.LOAD_DATA, new LoadDataCommand());
        result.put(BookCommandName.UPDATE_BOOK, new UpdateBookCommand());
        result.put(BookCommandName.UPDATE_DATA, new UpdateDataCommand());
        return result;
    }

    /**
     * Method for acquiring command implementation by its name
     * @param commandName Name of command
     * @return Command implementation or null if no command with such name exists
     */
    public static Command getCommand(String commandName) {
        return commands.get(BookCommandName.valueOf(commandName.toUpperCase()));
    }

    /**
     * Private constructor to avoid object creation
     */
    private BookCommandFactory() { }
}
