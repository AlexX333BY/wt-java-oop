package by.bsuir.kaziukovich.oop.dataaccess.dao.user;

import by.bsuir.kaziukovich.oop.dataaccess.dao.user.impl.LibraryUserDao;

/**
 * Factory for generating instances of UserDao
 */
public class UserDaoFactory {
    /**
     * Single UserDao instance
     */
    private static final UserDao userDao = new LibraryUserDao();

    /**
     * Generates and returns UserDao instance
     * @return UserDao instance
     */
    public static UserDao getUserDao() {
        return userDao;
    }

    /**
     * Empty constructor to avoid object creation
     */
    private UserDaoFactory() { }
}
