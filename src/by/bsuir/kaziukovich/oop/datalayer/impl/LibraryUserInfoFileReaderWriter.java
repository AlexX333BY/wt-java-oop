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

public class LibraryUserInfoFileReaderWriter implements UserInfoReaderWriter {
    private final Pattern userInfoPattern;
    private final String usernameGroupName;
    private final String passwordDigestGroupName;
    private final String userRoleGroupName;
    private final char delimiter;

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

    @Override
    public int hashCode() {
        return Objects.hash(userInfoPattern, usernameGroupName, passwordDigestGroupName, userRoleGroupName, delimiter);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@userInfoPattern: " + userInfoPattern.toString() + ", usernameGroupName: "
                + usernameGroupName + ", passwordDigestGroupName: " + passwordDigestGroupName + ", userRoleGroupName: "
                + userRoleGroupName + ", delimiter: " + delimiter;
    }

    public LibraryUserInfoFileReaderWriter() {
        usernameGroupName = "username";
        passwordDigestGroupName = "digest";
        userRoleGroupName = "role";
        delimiter = ':';
        userInfoPattern = Pattern.compile("^(?<" + usernameGroupName + ">\\w+)" + delimiter + "(?<"
                + passwordDigestGroupName + ">\\w+)" + delimiter + "(?<" + userRoleGroupName + ">\\w+)$");
    }
}
