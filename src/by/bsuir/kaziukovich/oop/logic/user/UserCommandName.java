package by.bsuir.kaziukovich.oop.logic.user;

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
     * Checks user password digest
     */
    CHECK_PASSWORD_DIGEST
}
