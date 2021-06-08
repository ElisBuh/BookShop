package service;


import api.dao.IBookDao;
import api.service.IBookService;
import dao.BookDao;
import model.Book;
import model.StatusBook;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class BookService implements IBookService {
    private int id;
    private final IBookDao iBookDao = BookDao.getBookDaoInstance();
    private static volatile BookService bookServiceInstance;

    private BookService() {
        addDataBook();
    }

    private void addDataBook() {
        addBook("War and Peace", "Leo Tolstoy", 25, LocalDate.of(2001, 5, 25));
        addBook("War and Peace3", "Leo Tolstoy1", 38, LocalDate.of(2011, 1, 22));
        addBook("War and Peace1", "Leo Tolstoy3", 52, LocalDate.of(2041, 2, 15));
        addBook("War and Peace5", "Leo Tolstoy2", 55, LocalDate.of(2061, 4, 23));
        addBook("War and Peace4", "Leo Tolstoy5", 21, LocalDate.of(2012, 10, 1));
        addBook("War and Peace6", "Leo Tolstoy4", 26, LocalDate.of(2021, 6, 12));
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
        iBookDao.addBook(new Book(id, nameBook, nameAuthor, date, price, StatusBook.ABSENT));
    }

    @Override
    public List<Book> listSortBooks(TypeSortBook typeSortBook) {
        return iBookDao.getBooks().stream().sorted(comparator(typeSortBook)).collect(Collectors.toList());
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
            return iBookDao.getBook(id);
        } catch (NullPointerException e) {
            System.err.println("Такой книги нет");
        }
        return null;
    }

    @Override
    public List<Book> getListBooks() {
        return new ArrayList<>(iBookDao.getBooks());
    }

}
