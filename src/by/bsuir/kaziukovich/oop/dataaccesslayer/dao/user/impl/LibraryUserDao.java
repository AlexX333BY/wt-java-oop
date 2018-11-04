package by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user.impl;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.NeverLoadedException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.StorageException;
import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.user.UserDao;
import by.bsuir.kaziukovich.oop.datalayer.info.user.UserInfo;
import by.bsuir.kaziukovich.oop.datalayer.info.user.UserInfoFactory;
import by.bsuir.kaziukovich.oop.datalayer.info.user.UserRole;
import by.bsuir.kaziukovich.oop.datalayer.readerwriter.InfoReadWriteException;
import by.bsuir.kaziukovich.oop.datalayer.readerwriter.ReaderWriterFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDao implementation
 */
public class LibraryUserDao implements UserDao {
    /**
     * List of users info structs
     */
    private List<UserInfo> users;

    /**
     * Path where users were loaded
     */
    private String path;

    /**
     * Creates and adds new user struct with base role
     * @param username Username of new user
     * @param password Password of new user
     * @throws ExistanceException In case of existance of user with same name
     */
    @Override
    public void addNewUser(String username, String password) throws ExistanceException {
        UserInfo newUser;

        if ((username == null) || (password == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        newUser = UserInfoFactory.createNew(username, password, UserRole.USER);
        for (UserInfo user : users) {
            if (user.compareTo(newUser) == 0) {
                throw new ExistanceException("User with username" + username + "already exists");
            }
        }

        users.add(newUser);
    }

    /**
     * Deletes user with specified username
     * @param username Username to delete by
     * @throws ExistanceException In case of non existance of user with specified name
     */
    @Override
    public void deleteUser(String username) throws ExistanceException {
        if (username == null) {
            throw new IllegalArgumentException("Username shouldn't be null");
        }

        users.remove(get(username));
    }

    /**
     * Updates user with specified parameters
     * @param username Username to update by
     * @param password New user password
     * @param userRole New user role
     * @throws ExistanceException In case of non existance of user with specified name
     */
    @Override
    public void updateUser(String username, String password, UserRole userRole) throws ExistanceException {
        UserInfo userToUpdate;

        if (username == null) {
            throw new IllegalArgumentException("Username shouldn't be null");
        }
        if ((password == null) && (userRole == null)) {
            throw new IllegalArgumentException("At least one user parameter shoudln't be null");
        }

        userToUpdate = get(username);
        deleteUser(username);
        users.add(UserInfoFactory.createNew(username, password == null ? userToUpdate.getPasswordDigest() : password,
                userRole == null ? userToUpdate.getUserRole() : userRole));
    }

    /**
     * Updates user with password
     * @param username Username to update by
     * @param password New user password
     * @throws ExistanceException In case of non existance of user with specified name
     */
    @Override
    public void updateUser(String username, String password) throws ExistanceException {
        updateUser(username, password, null);
    }

    /**
     * Updates user with new role
     * @param username Username to update by
     * @param userRole New user role
     * @throws ExistanceException In case of non existance of user with specified name
     */
    @Override
    public void updateUser(String username, UserRole userRole) throws ExistanceException {
        updateUser(username, null, userRole);
    }

    /**
     * Returns user struct by username
     * @param username Username to search for
     * @return User struct with specified username
     * @throws ExistanceException In case of non existance of user with specified name
     */
    @Override
    public UserInfo get(String username) throws ExistanceException {
        if (username == null) {
            throw new IllegalArgumentException("Username shouldn't be null");
        }

        for (UserInfo userInfo : users) {
            if (userInfo.getUsername().equals(username)) {
                return userInfo;
            }
        }

        throw new ExistanceException("User with username " + username + "doesn't exist");
    }

    /**
     * Updates data in storage
     * @throws StorageException In case if update is impossible (e.g. read-write error)
     */
    @Override
    public void updateData() throws StorageException, NeverLoadedException {
        if (path == null) {
            throw new NeverLoadedException("Data was never loaded by loadFrom() method. Can't update");
        }

        try {
            ReaderWriterFactory.getUserInfoReaderWriter().writeTo(users, path);
        } catch (InfoReadWriteException e) {
            throw new StorageException("Can't update info by path " + path, e);
        }
    }

    /**
     * Loads data by specified path
     * @param path Path to load data from
     * @throws StorageException In case if loading is impossible (e.g. read-write error)
     */
    @Override
    public void loadFrom(String path) throws StorageException {
        try {
            users = ReaderWriterFactory.getUserInfoReaderWriter().readFrom(path);
            this.path = path;
        } catch (InfoReadWriteException e) {
            throw new StorageException("Error loading data from " + path, e);
        }
    }

    /**
     * Returns all data structs
     * @return All data structs
     */
    @Override
    public List<UserInfo> getAll() {
        return new ArrayList<>(users);
    }

    public LibraryUserDao() {
        path = null;
        users = new ArrayList<>();
    }
}
