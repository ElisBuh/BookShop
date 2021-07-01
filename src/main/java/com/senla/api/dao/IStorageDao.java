package com.senla.api.dao;

import com.senla.model.Book;

import java.util.List;

public interface IStorageDao {

    void add(Book book);
    boolean delete(Book book);
    List<Book> getAll();

}