package by.bsuir.kaziukovich.oop.controller.book;

/**
 * Command names for books data
 */
public enum BookCommandName {
    /**
     * Command for loading data from file
     */
    LOAD_DATA,

    /**
     * Updates data in file
     */
    UPDATE_DATA,

    /**
     * Adds new book
     */
    ADD_BOOK,

    /**
     * Updates book with new info
     */
    UPDATE_BOOK,

    /**
     * Deletes book
     */
    DELETE_BOOK,

    /**
     * Returns all book strings
     */
    GET_ALL
}
