package by.bsuir.kaziukovich.oop.datalayer;

import by.bsuir.kaziukovich.oop.datalayer.impl.LibraryBookInfoFileReaderWriter;
import by.bsuir.kaziukovich.oop.datalayer.impl.LibraryUserInfoFileReaderWriter;

public class ReaderWriterFactory {
    private static UserInfoReaderWriter userInfoReaderWriter;
    private static BookInfoReaderWriter bookInfoReaderWriter;

    public static UserInfoReaderWriter getUserInfoReaderWriter() {
        if (userInfoReaderWriter == null) {
            userInfoReaderWriter = new LibraryUserInfoFileReaderWriter();
        }
        return userInfoReaderWriter;
    }

    public static BookInfoReaderWriter getBookInfoReaderWriter() {
        if (bookInfoReaderWriter == null) {
            bookInfoReaderWriter = new LibraryBookInfoFileReaderWriter();
        }
        return bookInfoReaderWriter;
    }

    private ReaderWriterFactory() { }
}
