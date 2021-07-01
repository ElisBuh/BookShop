package api.dao;

import model.Book;

import java.util.List;

public interface IStorageDao {

    void add(Book book);
    boolean delete(Book book);
    List<Book> getAll();

}