package api.dao;

import model.book.Book;

import java.util.ArrayList;

public interface IStorageDao {

    void addBook(Book book);
    void delete(Book book);
    ArrayList<Book> getBooks();

    void print();
}