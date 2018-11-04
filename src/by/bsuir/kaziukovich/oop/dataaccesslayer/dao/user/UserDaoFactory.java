package by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user.impl.LibraryUserDao;

/**
 * Factory for generating instances of UserDao
 */
public class UserDaoFactory {
    /**
     * Single UserDao instance
     */
    private static UserDao userDao;

    /**
     * Generates and returns UserDao instance
     * @return UserDao instance
     */
    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new LibraryUserDao();
        }
        return userDao;
    }

    /**
     * Empty constructor to avoid object creation
     */
    private UserDaoFactory() { }
}
