package by.bsuir.kaziukovich.oop.domain.info.user;

import by.bsuir.kaziukovich.oop.domain.info.user.impl.LibraryUserInfo;

/**
 * Factory for creating UserInfo instances
 */
public class UserInfoFactory {
    /**
     * Creates UserInfo instance
     * @param username Instance username
     * @param passwordDigest Instance password
     * @param userRole Instance UserRole
     * @return New UserInfo instance
     */
    public static UserInfo createNew(String username, String passwordDigest, UserRole userRole) {
        return new LibraryUserInfo(username, passwordDigest, userRole);
    }

    /**
     * Empty constructor to block object creation
     */
    private UserInfoFactory() { }
}
