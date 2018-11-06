package by.bsuir.kaziukovich.oop.datalayer.checker.userinfo.impl;

import by.bsuir.kaziukovich.oop.datalayer.checker.userinfo.UserInfoChecker;
import by.bsuir.kaziukovich.oop.datalayer.info.user.UserRole;
import java.util.Objects;
import java.util.regex.Pattern;

public class LibraryUserInfoChecker implements UserInfoChecker {
    /**
     * Username pattern
     */
    private final Pattern usernamePattern;

    /**
     * Password digest pattern
     */
    private final Pattern passwordDigestPattern;

    /**
     * Parts that should be included in entry while checking
     */
    private final static byte ENTRY_PARTS = 3;

    /**
     * Checks whether username is correct
     * @param username Username
     * @return True if username is correct, otherwise false
     */
    @Override
    public boolean isUsernameCorrect(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username shouldn't be null");
        }
        return usernamePattern.matcher(username.trim()).matches();
    }

    /**
     * Checks whether user password digest is correct
     * @param passwordDigest User password digest
     * @return True if user password digest is correct, otherwise false
     */
    @Override
    public boolean isPasswordDigestCorrect(String passwordDigest) {
        if (passwordDigest == null) {
            throw new IllegalArgumentException("Password digest shouldn't be null");
        }
        return passwordDigestPattern.matcher(passwordDigest.trim()).matches();
    }

    /**
     * Checks whether user role is correct
     * @param userRole User role
     * @return True if user role is correct, otherwise false
     */
    @Override
    public boolean isUserRoleCorrect(String userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException("User role shouldn't be null");
        }

        try {
            UserRole.valueOf(userRole);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Checks whether full entry represents correct info value
     * @param entry     Entry to check
     * @param delimiter Delimiter of entry parts
     * @return True if entry represents correct info value, otherwise false
     */
    @Override
    public boolean isEntryCorrect(String entry, String delimiter) {
        String[] splittedEntry;

        if ((entry == null) || (delimiter == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        splittedEntry = entry.split(delimiter);
        return (splittedEntry.length == ENTRY_PARTS) && isUsernameCorrect(splittedEntry[0])
                && isPasswordDigestCorrect(splittedEntry[1]) && isUserRoleCorrect(splittedEntry[2]);
    }

    /**
     * Checks whether current object equals specified object
     * @param o Specified object
     * @return True if objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        LibraryUserInfoChecker libraryUserInfoChecker;

        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        libraryUserInfoChecker = (LibraryUserInfoChecker) o;
        return Objects.equals(usernamePattern, libraryUserInfoChecker.usernamePattern)
                && Objects.equals(passwordDigestPattern, libraryUserInfoChecker.passwordDigestPattern);
    }

    /**
     * Generates hash code of this object
     * @return Hash code of this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(usernamePattern, passwordDigestPattern);
    }

    /**
     * Generates string representation of this object
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return getClass().getName() + "@usernamePattern: " + usernamePattern.toString() +", passwordDigestPattern: "
                + passwordDigestPattern.toString();
    }

    /**
     * Constructor with field initializers
     */
    public LibraryUserInfoChecker() {
        usernamePattern = Pattern.compile("^[\\w@!#$%&'*+\\-/=?^`{|}~.]+$]");
        passwordDigestPattern = Pattern.compile("^[a-fA-F0-9]+$");
    }
}
