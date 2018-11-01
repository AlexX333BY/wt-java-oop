package by.bsuir.kaziukovich.oop.datalayer.readerwriter;

import java.util.List;

/**
 * Interface of reader-writer class of set of I type
 * @param <I> Type of value to be written
 */
public interface InfoReaderWriter<I> {
    /**
     * Reads values by specified path
     * @param path Specified path
     * @return Set of read values
     * @throws InfoReadWriteException In case of error reading by path
     */
    List<I> readFrom(String path) throws InfoReadWriteException;

    /**
     * Writes specified values by specified path
     * @param infoList Set of values to be written
     * @param path Specified path
     * @throws InfoReadWriteException In case of error writing by path
     */
    void writeTo(List<I> infoList, String path) throws InfoReadWriteException;
}
