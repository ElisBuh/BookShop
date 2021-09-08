package com.senla.api.service;

import com.senla.model.Book;
import com.senla.service.TypeSortBook;

import java.time.LocalDate;
import java.util.List;


public interface IBookService {

    void save(String nameBook, String nameAuthor, int price, LocalDate date);

    Book get(int id);

    List<Book> listSortBooks(int pageNumber, int pageSize, TypeSortBook typeSortBook);

    List<Book> getAll(int pageNumber, int pageSize);
}
