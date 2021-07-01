package api.service;

import model.Book;

import java.time.LocalDate;
import java.util.List;

public interface IStorageService extends AbstractService {

    boolean add(Book book, LocalDate localDate);

    boolean delete(Book book);

    List<Book> BookNotSellMoreNmonth();

    List<Book> getAll();
}
