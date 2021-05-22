package api.service;

import model.book.Book;

public interface IStorageService {

    void addBook(Book book);
    void deleteBook(Book book);

    void printStorageBook();
}
