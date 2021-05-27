package api.service;

import model.Book;

import java.time.LocalDate;

public interface IStorageService {

    void addBook(Book book, LocalDate localDate);
    void deleteBook(Book book);
    void printBookNotSellMoreSixMonth();

    void printStorageBook();
}
