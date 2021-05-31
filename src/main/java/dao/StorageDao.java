package dao;

import api.dao.IStorageDao;
import model.Book;

import java.util.ArrayList;
import java.util.List;

public class StorageDao implements IStorageDao {
    private final List<Book> bookStorage = new ArrayList<>();

    @Override
    public List<Book> getBooks() {
        return new ArrayList<>(bookStorage);
    }

    @Override
    public void addBook(Book book) {
        bookStorage.add(book);
    }

    @Override
    public void delete(Book book) {
        bookStorage.remove(book);
    }

}
