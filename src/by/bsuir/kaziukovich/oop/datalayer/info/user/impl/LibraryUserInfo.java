package by.bsuir.kaziukovich.oop.datalayer.info.user.impl;

import by.bsuir.kaziukovich.oop.datalayer.info.user.UserRole;
import by.bsuir.kaziukovich.oop.datalayer.info.user.UserInfo;
import java.util.Objects;

/**
 * Implementation of UserInfo interface
 */
public class LibraryUserInfo implements UserInfo {
    /**
     * Username
     */
    private final String username;

    /**
     * User password digest
     */
    private final String passwordDigest;

    /**
     * User role
     */
    private final UserRole userRole;

    /**
     * Generates string representation of object
     * @return String representation of object
     */
    @Override
    public String toString() {
        return getClass().getName() + "@username: " + username + ", passwordDigest: " + passwordDigest + ", userRole: "
                + userRole.toString();
    }

    /**
     * Checks if current object equals to given
     * @param o Object to check equality to
     * @return True if objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        LibraryUserInfo libraryUserInfo;

        if (o == this) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        libraryUserInfo = (LibraryUserInfo) o;
        return Objects.equals(libraryUserInfo.passwordDigest, passwordDigest)
                && Objects.equals(libraryUserInfo.username, username)
                && Objects.equals(libraryUserInfo.userRole, userRole);
    }

    /**
     * Generates hash of current object
     * @return Hash of current object
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, passwordDigest, userRole);
    }

    /**
     * Getter for user password digest
     * @return User password digest
     */
    @Override
    public String getPasswordDigest() {
        return passwordDigest;
    }

    /**
     * Getter for username
     * @return Username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Getter for user role
     * @return User role
     */
    @Override
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Compares two users by username
     * @param user User to compare to this user
     * @return A negative integer, zero, or a positive integer as this user is less than, equal to, or greater than the specified user
     */
    @Override
    public int compareTo(UserInfo user) {
        return username.compareToIgnoreCase(user.getUsername());
    }

    /**
     * Constructor of user info instance
     * @param username New username
     * @param passwordDigest New user password digest
     * @param userRole New user role
     */
    public LibraryUserInfo(String username, String passwordDigest, UserRole userRole)
    {
        if ((username == null) || (passwordDigest == null))
        {
            throw new IllegalArgumentException("Constructor parameters shouldn't be null");
        }

        this.username = username.toLowerCase();
        this.passwordDigest = passwordDigest;
        this.userRole = userRole;
    }
}
