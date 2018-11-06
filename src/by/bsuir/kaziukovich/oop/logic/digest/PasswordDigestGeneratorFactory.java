package by.bsuir.kaziukovich.oop.logic.digest;

import by.bsuir.kaziukovich.oop.logic.digest.impl.Md5DigestGenerator;

/**
 * Digest generator factory
 */
public class PasswordDigestGeneratorFactory {
    /**
     * Single instance of digest generator
     */
    private final static PasswordDigestGenerator passwordDigestGenerator = new Md5DigestGenerator();

    /**
     * Generates password generator instance
     * @return Password generator instance
     */
    public PasswordDigestGenerator getPasswordDigestGenerator() {
        return passwordDigestGenerator;
    }

    /**
     * Private constructor to avoid object creation
     */
    private PasswordDigestGeneratorFactory() { }
}
