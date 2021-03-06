package by.bsuir.kaziukovich.oop.data.readerwriter.userinfo.impl;

import by.bsuir.kaziukovich.oop.data.readerwriter.InfoReadWriteException;
import by.bsuir.kaziukovich.oop.domain.checker.InfoCheckersFactory;
import by.bsuir.kaziukovich.oop.domain.checker.userinfo.UserInfoChecker;
import by.bsuir.kaziukovich.oop.domain.info.user.UserInfo;
import by.bsuir.kaziukovich.oop.domain.info.user.UserInfoFactory;
import by.bsuir.kaziukovich.oop.domain.info.user.UserRole;
import by.bsuir.kaziukovich.oop.data.readerwriter.userinfo.UserInfoReaderWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class implementing InfoReaderWriter interface for UserInfo type, reading info from file
 */
public class LibraryUserInfoFileReaderWriter implements UserInfoReaderWriter {
    /**
     * Delimiter of line entries
     */
    private final String delimiter;

    /**
     * Reads lines from specified path
     * @param path Specified path
     * @return Set of UserInfo's generated by read lines
     * @throws InfoReadWriteException In case of file read error
     */
    @Override
    public List<UserInfo> readFrom(String path) throws InfoReadWriteException {
        UserInfoChecker userInfoChecker = InfoCheckersFactory.getUserInfoChecker();
        List<UserInfo> result = new ArrayList<>();
        List<String> lines;
        String[] splittedLine;

        if (path == null) {
            throw new IllegalArgumentException("Path shouldn't be null");
        }

        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException ioException) {
            throw new InfoReadWriteException("Error reading file " + path, ioException);
        }

        for (String line : lines) {
            if (userInfoChecker.isEntryCorrect(line, delimiter)) {
                splittedLine = line.split(delimiter);
                result.add(UserInfoFactory.createNew(splittedLine[0], splittedLine[1],
                        UserRole.valueOf(splittedLine[2])));
            }
        }
        return result;
    }

    /**
     * Writes strings to specified path generated by specified list
     * @param infoList Set of values to be written
     * @param path Specified path
     * @throws InfoReadWriteException In case of file write error
     */
    @Override
    public void writeTo(List<UserInfo> infoList, String path) throws InfoReadWriteException {
        List<String> userLines = new ArrayList<>();

        if ((infoList == null) || (path == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        for (UserInfo userInfo : infoList) {
            userLines.add(userInfo.getUsername() + delimiter + userInfo.getPasswordDigest() + delimiter
                    + userInfo.getUserRole().toString());
        }

        try {
            Files.write(Paths.get(path), userLines);
        } catch (IOException ioException) {
            throw new InfoReadWriteException("Error writing to file " + path, ioException);
        }
    }

    /**
     * Checks if current object equals to given
     * @param o Object to check equality to
     * @return True if objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        LibraryUserInfoFileReaderWriter libraryUserInfoFileReaderWriter;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        libraryUserInfoFileReaderWriter = (LibraryUserInfoFileReaderWriter) o;
        return Objects.equals(delimiter, libraryUserInfoFileReaderWriter.delimiter);
    }

    /**
     * Generates hash of current object
     * @return Hash of current object
     */
    @Override
    public int hashCode() {
        return Objects.hash(delimiter);
    }

    /**
     * Generates string representation of object
     * @return String representation of object
     */
    @Override
    public String toString() {
        return getClass().getName() + "@delimiter: " + delimiter;
    }

    /**
     * Constructor for ReaderWriter instance
     */
    public LibraryUserInfoFileReaderWriter() {
        delimiter = ":";
    }
}
