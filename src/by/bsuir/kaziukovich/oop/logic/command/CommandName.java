package by.bsuir.kaziukovich.oop.logic.command;

/**
 * Command names
 */
public enum CommandName {
    /**
     * Loads all books data from file
     */
    LOAD_BOOKS,

    /**
     * Updates all books data in file
     */
    UPDATE_BOOKS,

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
    GET_ALL_BOOKS,

    /**
     * Searches for book
     */
    SEARCH_BOOK,

    /**
     * Loads all users data from file
     */
    LOAD_USERS,

    /**
     * Updates all users data in file
     */
    UPDATE_USERS,

    /**
     * Adds new user
     */
    ADD_USER,

    /**
     * Updates user password digest
     */
    UPDATE_USER_PASSWORD,

    /**
     * Updates user role
     */
    UPDATE_USER_ROLE,

    /**
     * Deletes user
     */
    DELETE_USER,

    /**
     * Checks user password
     */
    CHECK_PASSWORD,

    /**
     * Checks if user is administrator
     */
    CHECK_ADMIN,

    /**
     * Checks if user exists
     */
    CHECK_USER_EXIST
}
