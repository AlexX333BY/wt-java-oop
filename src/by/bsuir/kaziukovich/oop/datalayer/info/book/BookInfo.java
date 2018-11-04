package by.bsuir.kaziukovich.oop.datalayer.info.book;

/**
 * Interface of class representing book information
 */
public interface BookInfo extends Comparable<BookInfo> {
    /**
     * Getter for book title
     * @return Book title
     */
    String getTitle();

    /**
     * Getter for book author
     * @return Book author
     */
    String getAuthor();

    /**
     * Getter for book ISBN
     * @return Book ISBN
     */
    String getIsbn();

    /**
     * Getter for book type
     * @return Book type
     */
    BookType getBookType();
}
