package com.senla.api.dao;

import com.senla.model.Book;
import com.senla.model.Storage;

public interface IStorageDao extends GenericDao<Storage> {
    Storage findStorageOnBook(Book book);

}