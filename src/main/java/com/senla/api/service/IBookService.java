package com.senla.api.service;

import com.senla.model.Book;
import com.senla.service.TypeSortBook;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public interface IBookService extends AbstractService {

    void save(String nameBook, String nameAuthor, int price, LocalDate date);

    Book get(int id);

    List<Book> listSortBooks(TypeSortBook typeSortBook) throws SQLException;

    List<Book> getAll() throws SQLException;
}
