package by.bsuir.kaziukovich.oop.controller.user;

/**
 * Command names for users data
 */
public enum UserCommandName {
    /**
     * Command for loading data from file
     */
    LOAD_DATA,

    /**
     * Updates data in file
     */
    UPDATE_DATA,

    /**
     * Adds new user
     */
    ADD_USER,

    /**
     * Updates user info
     */
    UPDATE_USER,

    /**
     * Deletes user
     */
    DELETE_USER,

    /**
     * Returns password digest
     */
    GET_PASSWORD_DIGEST
}
