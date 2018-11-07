package by.bsuir.kaziukovich.oop.logic.command.impl;

import by.bsuir.kaziukovich.oop.dataaccess.dao.book.BookDaoFactory;
import by.bsuir.kaziukovich.oop.domain.info.book.BookInfo;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;
import java.util.List;

/**
 * Command for acquiring all books
 */
public class GetAllBooksCommand implements Command {
    /**
     * Optional arguments count for command
     */
    public final static byte OPTIONAL_ARGUMENTS = 1;

    /**
     * Required arguments count for command
     */
    public static final byte REQUIRED_ARGUMENTS = 1;

    /**
     * Default lines delimiter
     */
    public static final String DEFAULT_DELIMITER = "\n";

    /**
     * Command execution method
     * @param request Command request data. 1 optional argument: delimiter
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        String delimiter = DEFAULT_DELIMITER;
        List<BookInfo> books = BookDaoFactory.getBookDao().getAll();
        String[] result = new String[books.size() + 1];
        BookInfo curBook;

        if ((request != null) && (request.length == REQUIRED_ARGUMENTS + OPTIONAL_ARGUMENTS)) {
            delimiter = request[0];
        }
        result[0] = CommandResponse.SUCCESS_RESPONSE;

        for (int i = 1; i < result.length; i++) {
            curBook = books.get(i - 1);
            result[i] = "Author: " + curBook.getAuthor() + delimiter + "Title: " + curBook.getTitle() + delimiter
                    + "ISBN: " +  curBook.getIsbn() + delimiter + "Book type: " + curBook.getBookType().toString();
        }

        return result;
    }

    /**
     * Generates string representation of this object
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return getClass().getName();
    }
}
