package api.service;

import model.Book;

import java.time.LocalDate;
import java.util.List;

public interface IStorageService {

    void addBook(Book book, LocalDate localDate);
    void deleteBook(Book book);
    void printBookNotSellMoreSixMonth();

    List<Book> getStorageBooks();
}
