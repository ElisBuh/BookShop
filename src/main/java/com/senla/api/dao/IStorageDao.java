package com.senla.api.dao;

import com.senla.model.Book;
import com.senla.model.Storage;

import java.util.List;

public interface IStorageDao {

    void add(Storage storage);
    boolean delete(Book book);
    List<Storage> getAll();

}