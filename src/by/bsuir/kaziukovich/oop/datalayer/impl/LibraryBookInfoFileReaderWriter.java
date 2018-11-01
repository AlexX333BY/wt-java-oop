package by.bsuir.kaziukovich.oop.datalayer.impl;

import by.bsuir.kaziukovich.oop.datalayer.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryBookInfoFileReaderWriter implements BookInfoReaderWriter {
    private final Pattern bookInfoPattern;
    private final String authorGroupName;
    private final String titleGroupName;
    private final String bookTypeGroupName;
    private final String isbnGroupName;
    private final char delimiter;

    @Override
    public List<BookInfo> readFrom(String path) throws InfoReadException {
        List<BookInfo> result = new ArrayList<>();
        List<String> lines;
        Matcher bookPatternMatcher;

        if (path == null) {
            throw new IllegalArgumentException("Path shouldn't be null");
        }

        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException ioException) {
            InfoReadException infoReadException = new InfoReadException("Error reading file " + path);
            infoReadException.addSuppressed(ioException);
            throw infoReadException;
        }

        for (String line : lines) {
            bookPatternMatcher = bookInfoPattern.matcher(line);
            if (bookPatternMatcher.matches()) {
                try {
                    result.add(new LibraryBookInfo(bookPatternMatcher.group(titleGroupName),
                            bookPatternMatcher.group(authorGroupName), bookPatternMatcher.group(isbnGroupName),
                            BookType.valueOf(bookPatternMatcher.group(bookTypeGroupName))));
                } catch (IllegalArgumentException ignored) { }
            }
        }
        return result;
    }

    @Override
    public void writeTo(List<BookInfo> infoList, String path) throws InfoWriteException {
        List<String> bookLines = new ArrayList<>();

        if ((infoList == null) || (path == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        for (BookInfo bookInfo : infoList) {
            bookLines.add(bookInfo.getAuthor() + delimiter + bookInfo.getTitle() + delimiter + bookInfo.getIsbn()
                    + delimiter + bookInfo.getBookType().toString());
        }

        try {
            Files.write(Paths.get(path), bookLines);
        } catch (IOException ioException) {
            InfoWriteException infoWriteException = new InfoWriteException("Error writing to file " + path);
            infoWriteException.addSuppressed(ioException);
            throw infoWriteException;
        }
    }

    @Override
    public boolean equals(Object o) {
        LibraryBookInfoFileReaderWriter libraryBookInfoFileReaderWriter;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        libraryBookInfoFileReaderWriter = (LibraryBookInfoFileReaderWriter) o;
        return delimiter == libraryBookInfoFileReaderWriter.delimiter &&
                Objects.equals(bookInfoPattern, libraryBookInfoFileReaderWriter.bookInfoPattern) &&
                Objects.equals(authorGroupName, libraryBookInfoFileReaderWriter.authorGroupName) &&
                Objects.equals(titleGroupName, libraryBookInfoFileReaderWriter.titleGroupName) &&
                Objects.equals(bookTypeGroupName, libraryBookInfoFileReaderWriter.bookTypeGroupName) &&
                Objects.equals(isbnGroupName, libraryBookInfoFileReaderWriter.isbnGroupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookInfoPattern, authorGroupName, titleGroupName, bookTypeGroupName, isbnGroupName,
                delimiter);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@bookInfoPattern: " + bookInfoPattern.toString() + ", authorGroupName: "
                + authorGroupName + ", titleGroupName: " + titleGroupName + ", bookTypeGroupName: "
                + bookTypeGroupName + ", isbnGroupName: " + isbnGroupName + ", delimiter: " + delimiter;
    }

    public LibraryBookInfoFileReaderWriter() {
        authorGroupName = "author";
        titleGroupName = "title";
        bookTypeGroupName = "type";
        isbnGroupName = "isbn";
        delimiter = ':';
        bookInfoPattern = Pattern.compile("^(?<" + authorGroupName + ">[a-zA-Z]+[a-zA-Z\\s]*)"
                + delimiter + "(?<" + titleGroupName + ">[a-zA-Z]+[a-zA-Z\\s]*)"  + delimiter + "(?<" + isbnGroupName
                + ">[\\d\\-X]{10,})" + delimiter + "(?<" + bookTypeGroupName + ">\\w+)$");
    }
}
