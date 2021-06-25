package dao;

import api.dao.IStorageDao;
import exceptions.DaoException;
import exceptions.ServiceException;
import model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StorageDao implements IStorageDao {
    private static final Logger log = LoggerFactory.getLogger(StorageDao.class);

    private final List<Book> bookStorage = new ArrayList<>();
    private static volatile StorageDao storageDaoInstance;

    private StorageDao() {
    }

    public static StorageDao getStorageDaoInstance() {
        if (storageDaoInstance == null) {
            storageDaoInstance = new StorageDao();
        }
        return storageDaoInstance;
    }

    @Override
    public List<Book> getBooks() {
        return new ArrayList<>(bookStorage);
    }

    @Override
    public void addBook(Book book) {
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
