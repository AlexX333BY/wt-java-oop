package by.bsuir.kaziukovich.oop.datalayer.info.user;

/**
 * Interface of class representing user information
 */
public interface UserInfo extends Comparable<UserInfo> {
    /**
     * Getter for user password digest
     * @return User password digest
     */
    String getPasswordDigest();

    /**
     * Getter for username
     * @return Username
     */
    String getUsername();

    /**
     * Getter for user role
     * @return User role
     */
    UserRole getUserRole();
}
