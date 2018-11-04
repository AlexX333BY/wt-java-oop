package by.bsuir.kaziukovich.oop.dataaccesslayer.dao;

import java.util.List;

/**
 * Common interface for Dao objects
 */
public interface Dao<I> {
    /**
     * Updates data in storage
     * @throws StorageException In case if update is impossible (e.g. read-write error)
     */
    void updateData() throws StorageException;

    /**
     * Loads data by specified path
     * @param path Path to load data from
     * @throws StorageException In case if loading is impossible (e.g. read-write error)
     */
    void loadFrom(String path) throws StorageException;

    /**
     * Returns all data structs
     * @return All data structs
     */
    List<I> getAll();
}
