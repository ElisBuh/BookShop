package api.dao;

import model.Book;

import java.util.List;

public interface IStorageDao {

    void addBook(Book book);
    void delete(Book book);
    List<Book> getBooks();

    void print();
}