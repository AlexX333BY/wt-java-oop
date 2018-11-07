package by.bsuir.kaziukovich.oop.data.readerwriter;

import java.io.IOException;

/**
 * Exception to be thrown in case of info read error
 */
public class InfoReadWriteException extends IOException {
    /**
     * Constructor to create object with message and cause exception
     * @param message Message of exception
     * @param cause Cause exception
     */
    public InfoReadWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
