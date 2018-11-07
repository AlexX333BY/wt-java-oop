package by.bsuir.kaziukovich.oop.logger;

import java.io.PrintStream;

/**
 * Class to log program exceptions
 */
public class Logger {
    /**
     * Stream to log errors
     */
    private static PrintStream errorStream = System.err;

    /**
     * Logs exception
     * @param throwable Exception to log
     */
    public static void log(Throwable throwable) {
        throwable.printStackTrace(errorStream);
    }

    /**
     * Sets error stream
     */
    public static void setErrorStream(PrintStream stream) {
        errorStream = stream;
    }

    /**
     * Private constructor to avoid object creation
     */
    private Logger() { }
}
