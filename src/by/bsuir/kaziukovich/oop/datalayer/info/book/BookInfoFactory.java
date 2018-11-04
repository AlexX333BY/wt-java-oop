package by.bsuir.kaziukovich.oop.datalayer.info.book;

import by.bsuir.kaziukovich.oop.datalayer.info.book.impl.LibraryBookInfo;

/**
 * Factory for creating UserInfo instances
 */
public class BookInfoFactory {
    /**
     * Creates new book by specified parameters
     * @param title Title of new book
     * @param author Author of new book
     * @param isbn ISBN of new book
     * @param bookType Type of new book
     * @return New book instance
     */
    public static BookInfo createNew(String title, String author, String isbn, BookType bookType) {
        return new LibraryBookInfo(title, author, isbn, bookType);
    }

    /**
     * Empty constructor to block object creation
     */
    private BookInfoFactory() { }
}