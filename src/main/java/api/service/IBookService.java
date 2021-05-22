package api.service;

import model.book.Book;

public interface IBookService {

    void addBook(String nameBook, String nameAuthor);
    Book getBook(int id);

    void print();
}
