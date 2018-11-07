package by.bsuir.kaziukovich.oop.consoleview.impl;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to print string entries by pages
 */
public class ConsolePagePrinter {
    /**
     * Console lines count
     */
    private static byte consoleLinesCount = 24;

    /**
     * Sets console lines count
     * @param newLinesCount New console lines count
     */
    public static void setLinesCount(byte newLinesCount) {
        if (newLinesCount < 2) {
            throw new IllegalArgumentException("There should be at least 2 lines");
        }
        consoleLinesCount = newLinesCount;
    }

    /**
     * Prints string entries by pages
     * @param entries String entries
     */
    public static void PrintByPages(String[] entries) {
        ArrayList<String> lines = new ArrayList<>();
        byte linesCount = (byte) (consoleLinesCount - 1);

        if (entries == null) {
            throw new IllegalArgumentException("There should be at least 1 line to print");
        }

        for (String entry : entries) {
            lines.addAll(Arrays.asList(entry.split("\n")));
            lines.add("");
        }

        for (int i = 0; i < lines.size(); ++i) {
            System.out.println(lines.get(i));
            if (i % linesCount == linesCount - 1) {
                System.out.print("Press enter to show next page...");
                ConsoleScanner.getString();
            }
        }

        System.out.println("Pages ended.");
    }

    /**
     * Private constructor to avoid object creation
     */
    private ConsolePagePrinter() { }
}
