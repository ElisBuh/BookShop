package api.dao;

import model.book.Book;

public interface IBookDao {

    void addBook(Book book);
    Book getBook(int id);

    void print();
}
