package by.bsuir.kaziukovich.oop.datalayer;

public interface UserInfo {
    String getPasswordDigest();

    String getUsername();

    Role getRole();
}
