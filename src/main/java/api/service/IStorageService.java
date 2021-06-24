package api.service;

import model.Book;

import java.time.LocalDate;
import java.util.List;

public interface IStorageService extends AbstractService {

    boolean addBook(Book book, LocalDate localDate);

    boolean deleteBook(Book book);

    List<Book> BookNotSellMoreNmonth();

    List<Book> getStorageBooks();
}
