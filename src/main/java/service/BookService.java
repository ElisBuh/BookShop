package service;


import api.dao.IBookDao;
import api.service.IBookService;
import dao.BookDao;
import model.book.Book;
import model.book.StatusBook;

public class BookService implements IBookService {
    private int id;
    private IBookDao iBookDao = new BookDao();

    @Override
    public void addBook(String nameBook, String nameAuthor) {
        id++;
        iBookDao.addBook(new Book(id, nameBook, nameAuthor, StatusBook.ABSENT));
    }

    @Override
    public Book getBook(int id) {
        return iBookDao.getBook(id);
    }

    @Override
    public void print() {
        iBookDao.print();
    }
}
