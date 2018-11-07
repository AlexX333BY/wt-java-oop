package by.bsuir.kaziukovich.oop.dataaccess.dao.book;

import by.bsuir.kaziukovich.oop.dataaccess.dao.Dao;
import by.bsuir.kaziukovich.oop.dataaccess.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.domain.info.book.BookInfo;
import by.bsuir.kaziukovich.oop.domain.info.book.BookType;

/**
 * Interface for BookDao implementation
 */
public interface BookDao extends Dao<BookInfo> {
    /**
     * Creates and adds new book struct
     * @param title Title of new book
     * @param author Author of new book
     * @param isbn ISBN of new book
     * @param bookType Type of new book
     * @throws ExistanceException In case of book existance with same ISBN
     */
    void addNewBook(String title, String author, String isbn, BookType bookType) throws ExistanceException;

    /**
     * Deletes book with specified ISBN
     * @param isbn ISBN to delete by
     * @throws ExistanceException In case of non existance of book with specified ISBN
     */
    void deleteBook(String isbn) throws ExistanceException;

    /**
     * Updates book by specified parameters. Some of parameters can be null, but not all
     * @param isbn Updating book ISBN
     * @param title New title of book
     * @param author New author of book
     * @param bookType New book type
     * @throws ExistanceException In case of non existance of book with specified ISBN
     */
    void updateBook(String isbn, String title, String author, BookType bookType) throws ExistanceException;

    /**
     * Returns book struct by ISBN
     * @param isbn ISBN to search for
     * @return Book struct with specified ISBN
     * @throws ExistanceException In case of non existance of book with specified ISBN
     */
    BookInfo get(String isbn) throws ExistanceException;
}
