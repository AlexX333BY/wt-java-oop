package by.bsuir.kaziukovich.oop.consoleview.impl;

import java.util.Scanner;

/**
 * Class to simplify getting data from console
 */
public class ConsoleScanner {
    /**
     * Console scanner object
     */
    private static final Scanner consoleScanner = new Scanner(System.in);

    /**
     * Get first string from console
     * @return First string
     */
    public static String getString() {
        return consoleScanner.nextLine();
    }

    /**
     * Get first non-empty string from console
     * @return First non-empty string
     */
    public static String getNonEmptyString() {
        String result;

        do {
            result = getString();
        } while (result.isEmpty());
        return result;
    }

    /**
     * Constructor to avoid object creation
     */
    private ConsoleScanner() {}
}
