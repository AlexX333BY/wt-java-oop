package by.bsuir.kaziukovich.oop.datalayer.readerwriter;

import java.io.IOException;

/**
 * Exception to be thrown in case of info read error
 */
public class InfoReadException extends IOException {
    /**
     * Constructor to create object with message
     * @param message Message of exception
     */
    public InfoReadException(String message) {
        super(message);
    }
}
