package by.bsuir.kaziukovich.oop.logic.digest;

/**
 * Password digest generator interface
 */
public interface PasswordDigestGenerator {
    /**
     * Generates password digest by some algorithm
     * @param password Password to create digest by
     * @return Digest hexademical string representation
     * @throws PasswordDigestException In case of any digest creation errors
     */
    String generate(String password) throws PasswordDigestException;
}
