package com.senla.api.service;

import com.senla.model.Book;
import com.senla.model.Storage;

import java.time.LocalDate;
import java.util.List;

public interface IStorageService {

    boolean add(Book book, LocalDate localDate);

    boolean delete(Book book);

    List<Storage> bookNotSellMoreNmonth(int pageNumber, int pageSize);

    List<Storage> getAll(int pageNumber, int pageSize);
}
