package api.service;

import model.Book;
import service.TypeSortBook;


import java.time.LocalDate;
import java.util.List;


public interface IBookService extends AbstractService {

    void save(String nameBook, String nameAuthor, int price, LocalDate date);

    Book get(int id);

    List<Book> listSortBooks(TypeSortBook typeSortBook);

    List<Book> getAll();
}
