package by.bsuir.kaziukovich.oop.dataaccesslayer.dao;

/**
 * Exception to be thrown when data update is impossible (e.g. read-write error)
 */
public class StorageException extends Exception {
    /**
     * Constructor for Exception
     * @param message Message of exception
     * @param cause Cause of exception
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
