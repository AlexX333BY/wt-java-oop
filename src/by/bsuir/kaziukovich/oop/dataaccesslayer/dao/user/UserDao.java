package by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.Dao;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.datalayer.info.user.UserInfo;
import by.bsuir.kaziukovich.oop.datalayer.info.user.UserRole;

/**
 * Interface for UserDao implementation
 */
public interface UserDao extends Dao<UserInfo> {
    /**
     * Creates new user struct with base role
     * @param username Username of new user
     * @param password Password of new user
     * @return New user struct
     * @throws ExistanceException In case of existance of user with same name
     */
    UserInfo createNewUser(String username, String password) throws ExistanceException;

    /**
     * Deletes user with specified username
     * @param username Username to delete by
     * @throws ExistanceException In case of non existance of user with specified name
     */
    void deleteUser(String username) throws ExistanceException;

    /**
     * Updates user with specified parameters
     * @param username Username to update by
     * @param password New user password
     * @param userRole New user role
     * @throws ExistanceException In case of non existance of user with specified name
     */
    void updateUser(String username, String password, UserRole userRole) throws ExistanceException;

    /**
     * Updates user with password
     * @param username Username to update by
     * @param password New user password
     * @throws ExistanceException In case of non existance of user with specified name
     */
    void updateUser(String username, String password) throws ExistanceException;

    /**
     * Updates user with new role
     * @param username Username to update by
     * @param userRole New user role
     * @throws ExistanceException In case of non existance of user with specified name
     */
    void updateUser(String username, UserRole userRole) throws ExistanceException;

    /**
     * Returns user struct by username
     * @param username Username to search for
     * @return User struct with specified username
     * @throws ExistanceException In case of non existance of user with specified name
     */
    UserInfo get(String username) throws ExistanceException;
}
