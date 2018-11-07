package by.bsuir.kaziukovich.oop.dataaccess.dao;

/**
 * Exception to throw in case if data was never loaded
 */
public class NeverLoadedException extends Exception {
    /**
     * Exception constructor by message
     * @param message Message of exception
     */
    public NeverLoadedException(String message) {
        super(message);
    }
}
