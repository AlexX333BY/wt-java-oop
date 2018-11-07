package by.bsuir.kaziukovich.oop.dataaccess.dao.book.impl;

import by.bsuir.kaziukovich.oop.dataaccess.dao.ExistanceException;
import by.bsuir.kaziukovich.oop.dataaccess.dao.StorageException;
import by.bsuir.kaziukovich.oop.dataaccess.dao.book.BookDao;
import by.bsuir.kaziukovich.oop.dataaccess.dao.NeverLoadedException;
import by.bsuir.kaziukovich.oop.domain.info.book.BookInfo;
import by.bsuir.kaziukovich.oop.domain.info.book.BookInfoFactory;
import by.bsuir.kaziukovich.oop.domain.info.book.BookType;
import by.bsuir.kaziukovich.oop.data.readerwriter.InfoReadWriteException;
import by.bsuir.kaziukovich.oop.data.readerwriter.ReaderWriterFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BookDao implementation
 */
public class LibraryBookDao implements BookDao {
    /**
     * List of book info structs
     */
    private List<BookInfo> books;

    /**
     * Path where books were loaded
     */
    private String path;

    /**
     * Creates and adds new book struct
     * @param title Title of new book
     * @param author Author of new book
     * @param isbn ISBN of new book
     * @param bookType Type of new book
     * @throws ExistanceException In case of book existance with same ISBN
     */
    @Override
    public void addNewBook(String title, String author, String isbn, BookType bookType) throws ExistanceException {
        BookInfo newBook;

        if ((title == null) || (author == null) || (isbn == null) || (bookType == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        newBook = BookInfoFactory.createNew(title, author, isbn, bookType);
        for (BookInfo book : books) {
            if (book.compareTo(newBook) == 0) {
                throw new ExistanceException("Book with ISBN " + isbn + " already exists");
            }
        }

        books.add(newBook);
    }

    /**
     * Deletes book with specified ISBN
     * @param isbn ISBN to delete by
     * @throws ExistanceException In case of non existance of book with specified ISBN
     */
    @Override
    public void deleteBook(String isbn) throws ExistanceException {
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN shouldn't be null");
        }

        books.remove(get(isbn));
    }

    /**
     * Updates book by specified parameters. Some of parameters can be null, but not all
     * @param isbn Updating book ISBN
     * @param title New title of book
     * @param author New author of book
     * @param bookType New book type
     * @throws ExistanceException In case of non existance of book with specified ISBN
     */
    @Override
    public void updateBook(String isbn, String title, String author, BookType bookType) throws ExistanceException {
        BookInfo bookToUpdate;

        if (isbn == null) {
            throw new IllegalArgumentException("ISBN shouldn't be null");
        }
        if ((title == null) && (author == null) && (bookType == null)) {
            throw new IllegalArgumentException("At least one book parameter shouldn't be null");
        }

        bookToUpdate = get(isbn);
        deleteBook(isbn);
        addNewBook(title == null ? bookToUpdate.getTitle() : title, author == null ? bookToUpdate.getAuthor() : author,
                isbn, bookType == null ? bookToUpdate.getBookType() : bookType);
    }

    /**
     * Returns book struct by ISBN
     * @param isbn ISBN to search for
     * @return Book struct with specified ISBN
     * @throws ExistanceException In case of non existance of book with specified ISBN
     */
    @Override
    public BookInfo get(String isbn) throws ExistanceException {
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN shouldn't be null");
        }

        for (BookInfo bookInfo : books) {
            if (bookInfo.getIsbn().equals(isbn)) {
                return bookInfo;
            }
        }

        throw new ExistanceException("Book with ISBN " + isbn + "doesn't exist");
    }

    /**
     * Updates data in storage
     * @throws StorageException In case if update is impossible (e.g. read-write error)
     */
    @Override
    public void updateData() throws StorageException, NeverLoadedException {
        if (path == null) {
            throw new NeverLoadedException("Data was never loaded by loadFrom() method. Can't update");
        }

        try {
            ReaderWriterFactory.getBookInfoReaderWriter().writeTo(books, path);
        } catch (InfoReadWriteException e) {
            throw new StorageException("Can't update info by path " + path, e);
        }
    }

    /**
     * Loads data by specified path
     * @param path Path to load data from
     * @throws StorageException In case if loading is impossible (e.g. read-write error)
     */
    @Override
    public void loadFrom(String path) throws StorageException {
        try {
            books = ReaderWriterFactory.getBookInfoReaderWriter().readFrom(path);
            this.path = path;
        } catch (InfoReadWriteException e) {
            throw new StorageException("Error loading data from " + path, e);
        }
    }

    /**
     * Returns all data structs
     * @return All data structs
     */
    @Override
    public List<BookInfo> getAll() {
        return new ArrayList<>(books);
    }

    /**
     * Creates string representation of object
     * @return String representation of object
     */
    @Override
    public String toString() {
        return getClass().getName() + "@books: " + books.toString() + ", path: " + path;
    }

    /**
     * Compares current object to another
     * @param o Object to compare with
     * @return True if objects are same, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        LibraryBookDao libraryBookDao;

        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        libraryBookDao = (LibraryBookDao) o;
        return Objects.equals(books, libraryBookDao.books) && Objects.equals(path, libraryBookDao.path);
    }

    /**
     * Generates hash code of object
     * @return Hash code of object
     */
    @Override
    public int hashCode() {
        return Objects.hash(books, path);
    }

    /**
     * Default constructor with initializing fields
     */
    public LibraryBookDao() {
        books = new ArrayList<>();
        path = null;
    }
}
