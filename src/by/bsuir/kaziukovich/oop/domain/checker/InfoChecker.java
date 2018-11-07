package by.bsuir.kaziukovich.oop.domain.checker;

/**
 * Common interface for info checkers
 */
public interface InfoChecker {
    /**
     * Checks whether full entry represents correct info value
     * @param entry Entry to check
     * @param delimiter Delimiter of entry parts
     * @return True if entry represents correct info value, otherwise false
     */
    boolean isEntryCorrect(String entry, String delimiter);
}
