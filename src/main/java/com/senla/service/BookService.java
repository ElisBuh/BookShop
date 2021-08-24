package com.senla.service;


import com.senla.api.dao.IBookDao;
import com.senla.api.service.IBookService;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.StatusBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Scope(value = "singleton")
public final class BookService implements IBookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    private final IBookDao bookDao;

    public BookService(IBookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save(String nameBook, String nameAuthor, int price, LocalDate date) {
        log.info("save-BookService");
        try {
            bookDao.save(new Book(nameBook, nameAuthor, date, price, StatusBook.ABSENT));
        } catch (DaoException e) {
            log.info(e.toString());
            throw e;
        }
    }

    @Override
    public List<Book> listSortBooks(TypeSortBook typeSortBook) {
        log.info("SortsBooks on {}", typeSortBook.name());
        try {
            return bookDao.getAll().stream().sorted(comparator(typeSortBook)).collect(Collectors.toList());
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }
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
    public Book get(int id) {
        try {
            log.info("Get_Book_BY_Id: {}", id);
            return bookDao.get(id);
        } catch (DaoException e) {
            log.error("getBook id: {}, {}", id, e.toString());
            throw e;
        }
    }

    @Override
    public List<Book> getAll() {
        log.info("getAll-BookService");
        try {
            return new ArrayList<>(bookDao.getAll());
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }
    }

    public IBookDao bookDao() {
        return bookDao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BookService) obj;
        return Objects.equals(this.bookDao, that.bookDao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookDao);
    }

    @Override
    public String toString() {
        return "BookService[" +
                "bookDao=" + bookDao + ']';
    }

}
