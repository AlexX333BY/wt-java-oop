package by.bsuir.kaziukovich.oop.datalayer;

import java.util.List;

public interface InfoReaderWriter<I> {
    List<I> readFrom(String path);
    void writeTo(List<I> infoList, String path);
}
