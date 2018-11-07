package by.bsuir.kaziukovich.oop.domain.checker.bookinfo;

import by.bsuir.kaziukovich.oop.domain.checker.InfoChecker;

/**
 * Interface for checking if book info parts are correct
 */
public interface BookInfoChecker extends InfoChecker {
    /**
     * Checks if title is correct
     * @param title Book title
     * @return True if title is correct, otherwise false
     */
    boolean isTitleCorrect(String title);

    /**
     * Checks if author is correct
     * @param author Book author
     * @return True if author is correct, otherwise false
     */
    boolean isAuthorCorrect(String author);

    /**
     * Checks if ISBN is correct
     * @param isbn Book ISBN
     * @return True if ISBN is correct, otherwise false
     */
    boolean isIsbnCorrect(String isbn);

    /**
     * Checks if book type is correct
     * @param bookType Book type
     * @return True if book type is correct, otherwise false
     */
    boolean isBookTypeCorrect(String bookType);
}
