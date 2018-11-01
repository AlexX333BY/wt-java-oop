package by.bsuir.kaziukovich.oop.datalayer;

/**
 * Interface of class representing user information
 */
public interface UserInfo {
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
