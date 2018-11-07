package by.bsuir.kaziukovich.oop.data.readerwriter;

import by.bsuir.kaziukovich.oop.data.readerwriter.bookinfo.BookInfoReaderWriter;
import by.bsuir.kaziukovich.oop.data.readerwriter.userinfo.UserInfoReaderWriter;
import by.bsuir.kaziukovich.oop.data.readerwriter.bookinfo.impl.LibraryBookInfoFileReaderWriter;
import by.bsuir.kaziukovich.oop.data.readerwriter.userinfo.impl.LibraryUserInfoFileReaderWriter;

/**
 * Factory class for creating InfoReaderWriter instances
 */
public class ReaderWriterFactory {
    /**
     * Single instance of UserInfoReaderWriter
     */
    private static UserInfoReaderWriter userInfoReaderWriter;

    /**
     * Single instance of BookInfoReaderWriter
     */
    private static BookInfoReaderWriter bookInfoReaderWriter;

    /**
     * Generates instance of UserInfoReaderWriter implementation class
     * @return Instance of UserInfoReaderWriter implementation class
     */
    public static UserInfoReaderWriter getUserInfoReaderWriter() {
        if (userInfoReaderWriter == null) {
            userInfoReaderWriter = new LibraryUserInfoFileReaderWriter();
        }
        return userInfoReaderWriter;
    }

    /**
     * Generates instance of BookInfoReaderWriter implementation class
     * @return Instance of BookInfoReaderWriter implementation class
     */
    public static BookInfoReaderWriter getBookInfoReaderWriter() {
        if (bookInfoReaderWriter == null) {
            bookInfoReaderWriter = new LibraryBookInfoFileReaderWriter();
        }
        return bookInfoReaderWriter;
    }

    /**
     * Private constructor to avoid object creation
     */
    private ReaderWriterFactory() { }
}
