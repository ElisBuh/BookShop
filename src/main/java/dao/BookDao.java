package dao;

import api.dao.IBookDao;
import model.book.Book;

import java.util.ArrayList;

public class BookDao implements IBookDao {
    private final ArrayList<Book> books = new ArrayList<>();

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
    public ArrayList<Book> getBooks() {
        return books;
    }

    @Override
    public void print() {
        books.forEach(System.out::println);
    }
}
