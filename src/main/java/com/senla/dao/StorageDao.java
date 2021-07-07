package com.senla.dao;

import com.senla.api.dao.IStorageDao;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.util.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class StorageDao implements IStorageDao {
    private static final Logger log = LoggerFactory.getLogger(StorageDao.class);

    private final List<Book> bookStorage = new ArrayList<>();

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(bookStorage);
    }

    @Override
    public void add(Book book) {
        bookStorage.add(book);
    }

    @Override
    public boolean delete(Book book) {
        try {
            log.info("Delete_Book: {}-{}", book.getNameBook(), book.getId());
            return bookStorage.remove(book);
        } catch (NullPointerException e) {
            log.error("deleteBook: {}-{}", book.getNameBook(), book.getId());
            throw new DaoException(book.getNameBook() + "Not found");
        }
    }
}
