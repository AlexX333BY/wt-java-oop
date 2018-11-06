package by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user.impl.LibraryUserDao;

/**
 * Factory for generating instances of UserDao
 */
public class UserDaoFactory {
    /**
     * Single UserDao instance
     */
    private static UserDao userDao = new LibraryUserDao();

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
