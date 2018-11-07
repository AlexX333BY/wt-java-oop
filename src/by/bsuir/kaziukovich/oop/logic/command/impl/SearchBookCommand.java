package by.bsuir.kaziukovich.oop.logic.command.impl;

import by.bsuir.kaziukovich.oop.dataaccess.dao.book.BookDaoFactory;
import by.bsuir.kaziukovich.oop.domain.info.book.BookInfo;
import by.bsuir.kaziukovich.oop.logic.command.Command;
import by.bsuir.kaziukovich.oop.logic.command.CommandException;
import by.bsuir.kaziukovich.oop.logic.command.CommandResponse;
import java.util.ArrayList;

/**
 * Command for searching books
 */
public class SearchBookCommand implements Command {
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
     *
     * @param request Command request data. 1 argument required: search part. 1 optional argument: delimiter
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        String delimiter = DEFAULT_DELIMITER;
        ArrayList<String> listResult = new ArrayList<>();
        String[] result;
        String searchPart;

        if ((request == null) || (request.length < REQUIRED_ARGUMENTS)) {
            throw new IllegalArgumentException("At least " + REQUIRED_ARGUMENTS + " arguments required");
        }

        if (request.length == REQUIRED_ARGUMENTS + OPTIONAL_ARGUMENTS) {
            delimiter = request[1];
        }
        searchPart = request[0].toLowerCase();

        for (BookInfo book : BookDaoFactory.getBookDao().getAll()) {
            if ((book.getAuthor().toLowerCase().contains(searchPart))
                    || (book.getBookType().toString().toLowerCase().contains(searchPart))
                    || (book.getIsbn().toLowerCase().contains(searchPart))
                    || (book.getTitle().toLowerCase().contains(searchPart))) {
                listResult.add("Author: " + book.getAuthor() + delimiter + "Title: " + book.getTitle() + delimiter
                        + "ISBN: " +  book.getIsbn() + delimiter + "Book type: " + book.getBookType().toString());
            }
        }

        result = new String[listResult.size() + 1];
        result[0] = CommandResponse.SUCCESS_RESPONSE;
        for (int i = 0; i < listResult.size(); ++i) {
            result[i + 1] = listResult.get(i);
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
