package dao;

import api.dao.IBookDao;
import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {
    private final List<Book> books = new ArrayList<>();
    private static volatile BookDao bookDaoInstance;

    private BookDao() {
    }


    public static BookDao getBookDaoInstance() {
        if (bookDaoInstance == null) {
            bookDaoInstance = new BookDao();
        }
        return bookDaoInstance;
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book getBook(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }


}
