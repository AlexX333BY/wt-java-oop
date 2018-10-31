package by.bsuir.kaziukovich.oop.datalayer.impl;

import by.bsuir.kaziukovich.oop.datalayer.BookInfo;
import by.bsuir.kaziukovich.oop.datalayer.BookType;
import by.bsuir.kaziukovich.oop.datalayer.IllegalIsbnException;
import java.util.Objects;
import java.util.regex.Pattern;

public class LibraryBookInfo implements BookInfo {
    private final String title;
    private final String author;
    private final String isbn;
    private final BookType bookType;

    private final static byte MODERN_ISBN_PARTS = 5;
    private final static byte LEGACY_ISBN_PARTS = 4;

    @Override
    public String getTitle() {
        return new String(title);
    }

    @Override
    public String getAuthor() {
        return new String(author);
    }

    @Override
    public String getIsbn() {
        return new String(isbn);
    }

    @Override
    public BookType getBookType() {
        return bookType;
    }

    @Override
    public String toString() {
        return getClass().getName() + "@isbn: " + isbn + ", author: " + author + ", title: " + title + ", bookType: "
                + bookType.toString();
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(author, bookType, isbn, title);
    }

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

        this.title = new String(title);
        this.author = new String(author);
        this.bookType = bookType;
        this.isbn = new String(isbn);
    }
}
