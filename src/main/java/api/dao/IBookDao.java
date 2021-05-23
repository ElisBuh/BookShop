package api.dao;

import model.book.Book;

import java.util.ArrayList;

public interface IBookDao {

    void addBook(Book book);
    Book getBook(int id);
    ArrayList<Book> getBooks();

    void print();
}
