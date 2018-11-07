package by.bsuir.kaziukovich.oop.dataaccesslayer.dao.book;

import by.bsuir.kaziukovich.oop.dataaccesslayer.dao.book.impl.LibraryBookDao;

/**
 * Factory for generating instances of BookDao
 */
public class BookDaoFactory {
    /**
     * Single BookDao instance
     */
    private static final BookDao bookDao = new LibraryBookDao();

    /**
     * Generates and returns BookDao instance
     * @return BookDao instance
     */
    public static BookDao getBookDao() {
        return bookDao;
    }

    /**
     * Empty constructor to avoid object creation
     */
    private BookDaoFactory() { }
}
