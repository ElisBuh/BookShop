package api.dao;

import model.book.Book;

public interface IStorageDao {

    void addBook(Book book);
    void delete(Book book);

    void print();
}