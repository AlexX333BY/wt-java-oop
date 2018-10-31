package by.bsuir.kaziukovich.oop.datalayer.impl;

import by.bsuir.kaziukovich.oop.datalayer.Role;
import by.bsuir.kaziukovich.oop.datalayer.UserInfo;
import java.util.Objects;

public class LibraryUserInfo implements UserInfo {
    private final String username;
    private final String passwordDigest;
    private final Role role;

    @Override
    public String toString() {
        return getClass().getName() + "@username: " + username + ", passwordDigest: " + passwordDigest;
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
                && Objects.equals(libraryUserInfo.username, username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, passwordDigest);
    }

    @Override
    public String getPasswordDigest() {
        return new String(passwordDigest);
    }

    @Override
    public String getUsername() {
        return new String(username);
    }

    @Override
    public Role getRole() {
        return role;
    }

    protected LibraryUserInfo(String username, String passwordDigest, Role role)
    {
        if ((username == null) || (passwordDigest == null))
        {
            throw new IllegalArgumentException("Constructor parameters shouldn't be null");
        }
        this.username = new String(username);
        this.passwordDigest = new String(passwordDigest);
        this.role = role;
    }
}
