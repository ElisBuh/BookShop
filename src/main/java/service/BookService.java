package service;


import api.dao.IBookDao;
import api.service.IBookService;
import dao.BookDao;
import exceptions.DaoException;
import model.Book;
import model.StatusBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class BookService implements IBookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private int id;
    private final IBookDao bookDao = BookDao.getBookDaoInstance();
    private static volatile BookService bookServiceInstance;

    private BookService() {
    }


    public static BookService getBookServiceInstance() {
        if (bookServiceInstance == null) {
            bookServiceInstance = new BookService();
        }
        return bookServiceInstance;
    }

    @Override
    public void addBook(String nameBook, String nameAuthor, int price, LocalDate date) {
        id++;
        bookDao.addBook(new Book(id, nameBook, nameAuthor, date, price, StatusBook.ABSENT));
    }

    @Override
    public List<Book> listSortBooks(TypeSortBook typeSortBook) {
        return bookDao.getBooks().stream().sorted(comparator(typeSortBook)).collect(Collectors.toList());
    }

    private Comparator<Book> comparator(TypeSortBook typeSortBook) {
        if (typeSortBook.equals(TypeSortBook.NAME_BOOK)) {
            return Comparator.comparing(Book::getNameBook);
        } else if (typeSortBook.equals(TypeSortBook.DATE)) {
            return Comparator.comparing(Book::getDate);
        } else if (typeSortBook.equals(TypeSortBook.PRICE)) {
            return Comparator.comparing(Book::getPrice);
        } else if (typeSortBook.equals(TypeSortBook.IN_STOCK)) {
            return Comparator.comparing(Book::getStatusBook);
        } else return null;
    }

    @Override
    public Book getBook(int id) {
        try {
            log.info("Get_Book_BY_Id: {}", id);
            return bookDao.getBook(id);
        } catch (DaoException e) {
            log.error("getBook id: {}, {}", id, e.toString());
            throw e;
        }
    }

    @Override
    public List<Book> getListBooks() {
        return new ArrayList<>(bookDao.getBooks());
    }

    @Override
    public <T> void set(List<T> list) {
        if (list.size() > 0) {
            log.info("Десериализация Book");
            Book book = (Book) list.get(list.size() - 1);
            id = book.getId();
            list.stream().map(e -> (Book) e).forEach(bookDao::addBook);
        }
    }
}
