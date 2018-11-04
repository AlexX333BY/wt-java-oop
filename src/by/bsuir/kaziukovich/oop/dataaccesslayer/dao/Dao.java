package by.bsuir.kaziukovich.oop.dataaccesslayer.dao;

/**
 * Common interface for Dao objects
 */
public interface Dao {
    /**
     * Updates data in storage
     * @throws InfoUpdateException In case if update is impossible (e.g. read-write error)
     */
    void updateData() throws InfoUpdateException;
}
