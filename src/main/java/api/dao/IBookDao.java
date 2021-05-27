package api.dao;

import model.Book;

import java.util.List;

public interface IBookDao {

    void addBook(Book book);
    Book getBook(int id);
    List<Book> getBooks();

}
