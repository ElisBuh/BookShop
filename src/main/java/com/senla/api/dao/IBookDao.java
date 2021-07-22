package com.senla.api.dao;

import com.senla.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookDao {

    void save(Book book);
    Book get(int id);
    List<Book> getAll();
    boolean delete(Book book);
    void set(Book book);


}
