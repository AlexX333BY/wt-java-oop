package by.bsuir.kaziukovich.oop.datalayer.impl;

import by.bsuir.kaziukovich.oop.datalayer.BookInfo;
import by.bsuir.kaziukovich.oop.datalayer.BookType;
import by.bsuir.kaziukovich.oop.datalayer.IllegalIsbnException;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Implementation of BookInfo interface
 */
public class LibraryBookInfo implements BookInfo {
    /**
     * Book title
     */
    private final String title;

    /**
     * Book author
     */
    private final String author;

    /**
     * Book ISBN
     */
    private final String isbn;

    /**
     * Book type
     */
    private final BookType bookType;

    /**
     * Parts of modern ISBN
     */
    private final static byte MODERN_ISBN_PARTS = 5;

    /**
     * Parts of legacy ISBN
     */
    private final static byte LEGACY_ISBN_PARTS = 4;

    /**
     * Getter for book title
     * @return Book title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Getter for book author
     * @return Book author
     */
    @Override
    public String getAuthor() {
        return author;
    }

    /**
     * Getter for book ISBN
     * @return Book ISBN
     */
    @Override
    public String getIsbn() {
        return isbn;
    }

    /**
     * Getter for book type
     * @return Book type
     */
    @Override
    public BookType getBookType() {
        return bookType;
    }

    /**
     * Generates string representation of object
     * @return String representation of object
     */
    @Override
    public String toString() {
        return getClass().getName() + "@isbn: " + isbn + ", author: " + author + ", title: " + title + ", bookType: "
                + bookType.toString();
    }

    /**
     * Checks if current object equals to given
     * @param o Object to check equality to
     * @return True if objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        LibraryBookInfo libraryBookInfo;

        if (o == this) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        libraryBookInfo = (LibraryBookInfo) o;
        return Objects.equals(libraryBookInfo.author, author) && Objects.equals(libraryBookInfo.bookType, bookType)
                && Objects.equals(libraryBookInfo.isbn, isbn) && Objects.equals(libraryBookInfo.title, title);
    }

    /**
     * Generates hash of current object
     * @return Hash of current object
     */
    @Override
    public int hashCode() {
        return Objects.hash(author, bookType, isbn, title);
    }

    /**
     * Checks whether given ISBN is of modern or legacy type
     * @param isbn Given ISBN
     * @return True if ISBN if of one of types, otherwise false
     */
    private static boolean isIsbnCorrect(String isbn) {
        String[] splittedIsbn = isbn.split(Pattern.quote("-"));
        byte indent = 0;
        int isbnLength = 0;

        if ((splittedIsbn.length != MODERN_ISBN_PARTS) && (splittedIsbn.length != LEGACY_ISBN_PARTS)) {
            return false;
        }
        if (splittedIsbn.length == MODERN_ISBN_PARTS) {
            if (!splittedIsbn[0].matches("^97[89]$")) {
                return false;
            }
            indent = 1;
        }

        for (String s : splittedIsbn) {
            isbnLength += s.length();
        }

        return (isbnLength == 13)
                && splittedIsbn[indent].matches("^\\d{1,5}$")
                && splittedIsbn[1 + indent].matches("^\\d{2,7}$")
                && splittedIsbn[2 + indent].matches("^\\d{1,6}$")
                && splittedIsbn[3 + indent].matches("^[0-9X]$");
    }

    /**
     * Constructor of LibraryBookInfo object
     * @param title New book title
     * @param author New book author
     * @param isbn New book ISBN
     * @param bookType New book type
     */
    LibraryBookInfo(String title, String author, String isbn, BookType bookType)
    {
        if ((title == null) || (isbn == null) || (author == null))
        {
            throw new IllegalArgumentException("Constructor parameters shouldn't be null");
        }
        if (!isIsbnCorrect(isbn))
        {
            throw new IllegalIsbnException(isbn +  " isn't modern nor legacy ISBN");
        }

        this.title = title;
        this.author = author;
        this.bookType = bookType;
        this.isbn = isbn;
    }
}
