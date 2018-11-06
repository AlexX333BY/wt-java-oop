package by.bsuir.kaziukovich.oop.logic.command.user;

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
    CHECK_EXIST
}
