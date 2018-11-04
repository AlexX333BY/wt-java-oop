package by.bsuir.kaziukovich.oop.dataaccesslayer.dao;

/**
 * Exception to be thrown in case of data existance errors
 */
public class ExistanceException extends Exception {
    /**
     * Constructor for Exception
     * @param message Message of exception
     */
    public ExistanceException(String message) {
        super(message);
    }
}
