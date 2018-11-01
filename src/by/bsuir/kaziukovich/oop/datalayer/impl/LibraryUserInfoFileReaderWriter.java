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

/**
 * Class implementing InfoReaderWriter interface for UserInfo type, reading info from file
 */
public class LibraryUserInfoFileReaderWriter implements UserInfoReaderWriter {
    /**
     * Pattern to check input string
     */
    private final Pattern userInfoPattern;

    /**
     * Group name of username for regex
     */
    private final String usernameGroupName;

    /**
     * Group name of user password digest for regex
     */
    private final String passwordDigestGroupName;

    /**
     * Group name of user role for regex
     */
    private final String userRoleGroupName;

    /**
     * Delimiter of line entries
     */
    private final char delimiter;

    /**
     * Reads lines from specified path
     * @param path Specified path
     * @return Set of UserInfo's generated by read lines
     * @throws InfoReadException In case of file read error
     */
    @Override
    public List<UserInfo> readFrom(String path) throws InfoReadException {
        List<UserInfo> result = new ArrayList<>();
        List<String> lines;
        Matcher userPatternMatcher;

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
            userPatternMatcher = userInfoPattern.matcher(line);
            if (userPatternMatcher.matches()) {
                try {
                    result.add(new LibraryUserInfo(userPatternMatcher.group(usernameGroupName),
                            userPatternMatcher.group(passwordDigestGroupName),
                            UserRole.valueOf(userPatternMatcher.group(userRoleGroupName))));
                } catch (IllegalArgumentException ignored) { }
            }
        }
        return result;
    }

    /**
     * Writes strings to specified path generated by specified list
     * @param infoList Set of values to be written
     * @param path Specified path
     * @throws InfoWriteException In case of file write error
     */
    @Override
    public void writeTo(List<UserInfo> infoList, String path) throws InfoWriteException {
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
            InfoWriteException infoWriteException = new InfoWriteException("Error writing to file " + path);

            infoWriteException.addSuppressed(ioException);
            throw infoWriteException;
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
        return delimiter == libraryUserInfoFileReaderWriter.delimiter &&
                Objects.equals(userInfoPattern, libraryUserInfoFileReaderWriter.userInfoPattern) &&
                Objects.equals(usernameGroupName, libraryUserInfoFileReaderWriter.usernameGroupName) &&
                Objects.equals(passwordDigestGroupName, libraryUserInfoFileReaderWriter.passwordDigestGroupName) &&
                Objects.equals(userRoleGroupName, libraryUserInfoFileReaderWriter.userRoleGroupName);
    }

    /**
     * Generates hash of current object
     * @return Hash of current object
     */
    @Override
    public int hashCode() {
        return Objects.hash(userInfoPattern, usernameGroupName, passwordDigestGroupName, userRoleGroupName, delimiter);
    }

    /**
     * Generates string representation of object
     * @return String representation of object
     */
    @Override
    public String toString() {
        return getClass().getName() + "@userInfoPattern: " + userInfoPattern.toString() + ", usernameGroupName: "
                + usernameGroupName + ", passwordDigestGroupName: " + passwordDigestGroupName + ", userRoleGroupName: "
                + userRoleGroupName + ", delimiter: " + delimiter;
    }

    /**
     * Constructor for ReaderWriter instance
     */
    public LibraryUserInfoFileReaderWriter() {
        usernameGroupName = "username";
        passwordDigestGroupName = "digest";
        userRoleGroupName = "role";
        delimiter = ':';
        userInfoPattern = Pattern.compile("^(?<" + usernameGroupName + ">\\w+)" + delimiter + "(?<"
                + passwordDigestGroupName + ">\\w+)" + delimiter + "(?<" + userRoleGroupName + ">\\w+)$");
    }
}
