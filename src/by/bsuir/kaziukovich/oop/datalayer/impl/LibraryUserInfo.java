package by.bsuir.kaziukovich.oop.datalayer.impl;

import by.bsuir.kaziukovich.oop.datalayer.UserRole;
import by.bsuir.kaziukovich.oop.datalayer.UserInfo;
import java.util.Objects;

public class LibraryUserInfo implements UserInfo {
    private final String username;
    private final String passwordDigest;
    private final UserRole userRole;

    @Override
    public String toString() {
        return getClass().getName() + "@username: " + username + ", passwordDigest: " + passwordDigest + ", userRole: "
                + userRole.toString();
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(username, passwordDigest, userRole);
    }

    @Override
    public String getPasswordDigest() {
        return passwordDigest;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public UserRole getUserRole() {
        return userRole;
    }

    LibraryUserInfo(String username, String passwordDigest, UserRole userRole)
    {
        if ((username == null) || (passwordDigest == null))
        {
            throw new IllegalArgumentException("Constructor parameters shouldn't be null");
        }

        this.username = username;
        this.passwordDigest = passwordDigest;
        this.userRole = userRole;
    }
}
