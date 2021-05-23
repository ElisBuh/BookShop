package dao;

import api.dao.IStorageDao;
import model.book.Book;

import java.util.ArrayList;

public class StorageDao implements IStorageDao {
    private final ArrayList<Book> bookStorage = new ArrayList<>();

    @Override
    public ArrayList<Book> getBooks() {
        return bookStorage;
    }

    @Override
    public void addBook(Book book) {
        bookStorage.add(book);
    }

    @Override
    public void delete(Book book) {
        bookStorage.remove(book);
    }

    @Override
    public void print() {
        bookStorage.forEach(System.out::println);
    }
}
