package by.bsuir.kaziukovich.oop.dataaccesslayer.dao;

/**
 * Exception to be thrown when data update is impossible (e.g. read-write error)
 */
public class InfoUpdateException extends Exception {
    /**
     * Constructor for Exception
     * @param message Message of exception
     * @param cause Cause of exception
     */
    public InfoUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
