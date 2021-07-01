package dao;

import api.dao.IBookDao;
import exceptions.DaoException;
import model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {
    private static final Logger log = LoggerFactory.getLogger(BookDao.class);

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
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public Book get(int id) {
        log.info("Get_Book_By_ID: {}", id);
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new DaoException("id:" + id + "-not found"));

    }

    @Override
    public boolean delete(Book book) {
        return books.remove(book);
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(books);
    }

}
