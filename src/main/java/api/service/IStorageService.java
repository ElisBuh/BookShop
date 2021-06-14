package api.service;

import model.Book;

import java.time.LocalDate;
import java.util.List;

public interface IStorageService {

    boolean addBook(Book book, LocalDate localDate);

    boolean deleteBook(Book book);

    List<Book> printBookNotSellMoreSixMonth();

    List<Book> getStorageBooks();
}
