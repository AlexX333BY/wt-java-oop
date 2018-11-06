package by.bsuir.kaziukovich.oop.logic.digest.impl;

import by.bsuir.kaziukovich.oop.logic.digest.PasswordDigestException;
import by.bsuir.kaziukovich.oop.logic.digest.PasswordDigestGenerator;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Password MD5 digest generator
 */
public class Md5DigestGenerator implements PasswordDigestGenerator {
    /**
     * Generates password digest by MD5 algorithm
     * @param password Password to create digest by
     * @return Digest hexademical string representation
     * @throws PasswordDigestException In case of any digest creation errors
     */
    @Override
    public String generate(String password) throws PasswordDigestException {
        if (password == null) {
            throw new IllegalArgumentException("Password shouldn't be null");
        }

        try {
            return DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new PasswordDigestException("Error acquiring MD5 digest instance", e);
        }
    }

    /**
     * Generates string representation of this object
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return getClass().getName();
    }
}
