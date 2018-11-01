package by.bsuir.kaziukovich.oop.datalayer.readerwriter;

import java.io.IOException;

/**
 * Exception to be thrown in case of info write error
 */
public class InfoWriteException extends IOException {
    /**
     * Constructor to create object with message
     * @param message Message of exception
     */
    public InfoWriteException(String message) {
        super(message);
    }
}
