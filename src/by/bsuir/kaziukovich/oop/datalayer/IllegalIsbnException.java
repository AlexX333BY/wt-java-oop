package by.bsuir.kaziukovich.oop.datalayer;

/**
 * Exception to be thrown if illegal ISBN is given as argument
 */
public class IllegalIsbnException extends IllegalArgumentException {
    /**
     * Constructor to create object with message
     * @param message Message of exception
     */
    public IllegalIsbnException(String message) {
        super(message);
    }
}
