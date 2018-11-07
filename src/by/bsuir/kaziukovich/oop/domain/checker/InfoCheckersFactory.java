package by.bsuir.kaziukovich.oop.domain.checker;

import by.bsuir.kaziukovich.oop.domain.checker.bookinfo.BookInfoChecker;
import by.bsuir.kaziukovich.oop.domain.checker.bookinfo.impl.LibraryBookInfoChecker;
import by.bsuir.kaziukovich.oop.domain.checker.userinfo.UserInfoChecker;
import by.bsuir.kaziukovich.oop.domain.checker.userinfo.impl.LibraryUserInfoChecker;

/**
 * Checker factory class
 */
public class InfoCheckersFactory {
    /**
     * Single book info checker instance
     */
    private static BookInfoChecker bookInfoChecker = null;

    /**
     * Single user info checker instance
     */
    private static UserInfoChecker userInfoChecker = null;

    /**
     * Method for generating book info checker instance
     * @return Book info checker instance
     */
    public static BookInfoChecker getBookInfoChecker() {
        if (bookInfoChecker == null) {
            bookInfoChecker = new LibraryBookInfoChecker();
        }
        return bookInfoChecker;
    }

    /**
     * Method for generating user info checker instance
     * @return User info checker instance
     */
    public static UserInfoChecker getUserInfoChecker() {
        if (userInfoChecker == null) {
            userInfoChecker = new LibraryUserInfoChecker();
        }
        return userInfoChecker;
    }

    /**
     * Private constructor to avoid object creation
     */
    private InfoCheckersFactory() { }
}
