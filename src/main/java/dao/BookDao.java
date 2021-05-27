package dao;

import api.dao.IBookDao;
import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {
    private final List<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book getBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    @Override
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }


}
