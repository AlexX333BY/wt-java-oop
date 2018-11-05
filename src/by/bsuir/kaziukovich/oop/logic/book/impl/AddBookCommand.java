package by.bsuir.kaziukovich.oop.logic.book.impl;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.book.BookDaoFactory;
import by.bsuir.kaziukovich.oop.datalayer.info.book.BookType;
import by.bsuir.kaziukovich.oop.logic.Command;
import by.bsuir.kaziukovich.oop.logic.CommandException;
import by.bsuir.kaziukovich.oop.logic.CommandResponse;

/**
 * Command for adding new book
 */
public class AddBookCommand implements Command {
    /**
     * Required arguments count for this command
     */
    public static final byte REQUIRED_ARGUMENTS = 4;

    /**
     * Command execution method
     * @param request Command request data. 4 strings required: title, author, ISBN, book type
     * @return Command response
     * @throws CommandException In case of any command execution error
     */
    @Override
    public String[] execute(String[] request) throws CommandException {
        if ((request == null) || (request.length != REQUIRED_ARGUMENTS)) {
            throw new IllegalArgumentException(REQUIRED_ARGUMENTS + "arguments required");
        }

        try {
            BookDaoFactory.getBookDao().addNewBook(request[0], request[1], request[2],
                    BookType.valueOf(request[3].toUpperCase()));
        } catch (ExistanceException e) {
            throw new CommandException("Error executing AddBook command", e);
        }

        return new String[] { CommandResponse.SUCCESS_RESPONSE };
    }
}
