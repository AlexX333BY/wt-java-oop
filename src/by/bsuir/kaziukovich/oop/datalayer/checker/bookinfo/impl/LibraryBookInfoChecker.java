package by.bsuir.kaziukovich.oop.datalayer.checker.bookinfo.impl;

import by.bsuir.kaziukovich.oop.datalayer.checker.bookinfo.BookInfoChecker;
import by.bsuir.kaziukovich.oop.datalayer.info.book.BookType;

import java.util.Objects;
import java.util.regex.Pattern;

public class LibraryBookInfoChecker implements BookInfoChecker {
    /**
     * Book title pattern
     */
    private final Pattern titlePattern;

    /**
     * Book author pattern
     */
    private final Pattern authorPattern;

    /**
     * Parts of modern ISBN
     */
    private final static byte MODERN_ISBN_PARTS = 5;

    /**
     * Parts of legacy ISBN
     */
    private final static byte LEGACY_ISBN_PARTS = MODERN_ISBN_PARTS - 1;

    /**
     * Parts that should be included in entry while checking
     */
    private final static byte ENTRY_PARTS = 4;

    /**
     * Checks if title is correct
     * @param title Book title
     * @return True if title is correct, otherwise false
     */
    @Override
    public boolean isTitleCorrect(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title shouldn't be null");
        }
        return titlePattern.matcher(title.trim()).matches();
    }

    /**
     * Checks if author is correct
     * @param author Book author
     * @return True if author is correct, otherwise false
     */
    @Override
    public boolean isAuthorCorrect(String author) {
        if (author == null) {
            throw new IllegalArgumentException("Author shouldn't be null");
        }
        return authorPattern.matcher(author.trim()).matches();
    }

    /**
     * Checks if ISBN is correct
     * @param isbn Book ISBN
     * @return True if ISBN is correct, otherwise false
     */
    @Override
    public boolean isIsbnCorrect(String isbn) {
        String[] splittedIsbn;
        byte indent = 0;
        int isbnLength = 0;

        if (isbn == null) {
            throw new IllegalArgumentException("ISBN shouldn't be null");
        }

        splittedIsbn = isbn.trim().split(Pattern.quote("-"));

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
     * Checks if book type is correct
     * @param bookType Book type
     * @return True if book type is correct, otherwise false
     */
    @Override
    public boolean isBookTypeCorrect(String bookType) {
        if (bookType == null) {
            throw new IllegalArgumentException("Book type shouldn't be null");
        }

        try {
            BookType.valueOf(bookType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Checks whether full entry represents correct info value
     * @param entry     Entry to check
     * @param delimiter Delimiter of entry parts
     * @return True if entry represents correct info value, otherwise false
     */
    @Override
    public boolean isEntryCorrect(String entry, String delimiter) {
        String[] splittedEntry;

        if ((entry == null) || (delimiter == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        splittedEntry = entry.split(delimiter);
        return (splittedEntry.length == ENTRY_PARTS) && isAuthorCorrect(splittedEntry[0])
                && isTitleCorrect(splittedEntry[1]) && isIsbnCorrect(splittedEntry[2])
                && isBookTypeCorrect(splittedEntry[3]);
    }

    /**
     * Checks whether current object equals specified object
     * @param o Specified object
     * @return True if objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        LibraryBookInfoChecker libraryBookInfoChecker;

        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        libraryBookInfoChecker = (LibraryBookInfoChecker) o;
        return Objects.equals(titlePattern, libraryBookInfoChecker.titlePattern)
                && Objects.equals(authorPattern, libraryBookInfoChecker.authorPattern);
    }

    /**
     * Generates hash code of this object
     * @return Hash code of this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(titlePattern, authorPattern);
    }

    /**
     * Generates string representation of this object
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return getClass().getName() + "@titlePattern: " + titlePattern.toString() +", authorPattern: "
                + authorPattern.toString();
    }

    /**
     * Constructor with field initializers
     */
    public LibraryBookInfoChecker() {
        authorPattern = Pattern.compile("^[a-zA-Z\\s]+$");
        titlePattern = Pattern.compile("^[\\w\\s]+$");
    }
}
