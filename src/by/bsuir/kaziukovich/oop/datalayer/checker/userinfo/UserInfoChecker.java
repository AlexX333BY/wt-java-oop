package by.bsuir.kaziukovich.oop.datalayer.checker.userinfo;

import by.bsuir.kaziukovich.oop.datalayer.checker.InfoChecker;

/**
 * Interface for checking if user info parts are correct
 */
public interface UserInfoChecker extends InfoChecker {
    /**
     * Checks whether username is correct
     * @param username Username
     * @return True if username is correct, otherwise false
     */
    boolean isUsernameCorrect(String username);

    /**
     * Checks whether user password digest is correct
     * @param passwordDigest User password digest
     * @return True if user password digest is correct, otherwise false
     */
    boolean isPasswordDigestCorrect(String passwordDigest);

    /**
     * Checks whether user role is correct
     * @param userRole User role
     * @return True if user role is correct, otherwise false
     */
    boolean isUserRoleCorrect(String userRole);
}
