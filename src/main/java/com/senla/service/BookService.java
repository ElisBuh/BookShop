package com.senla.service;


import com.senla.api.dao.IBookDao;
import com.senla.api.service.IBookService;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.StatusBook;
import com.senla.util.annotation.InjectByType;
import com.senla.util.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class BookService implements IBookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private int id;

    @InjectByType
    private IBookDao bookDao;

    @Override
    public void save(String nameBook, String nameAuthor, int price, LocalDate date) {
        id++;
        bookDao.save(new Book(id, nameBook, nameAuthor, date, price, StatusBook.ABSENT));
    }

    @Override
    public List<Book> listSortBooks(TypeSortBook typeSortBook) {
        return bookDao.getAll().stream().sorted(comparator(typeSortBook)).collect(Collectors.toList());
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
        return new ArrayList<>(bookDao.getAll());
    }

    @Override
    public <T> void set(List<T> list) {
        if (list.size() > 0) {
            log.info("Десериализация Book");
            Book book = (Book) list.get(list.size() - 1);
            id = book.getId();
            list.stream().map(e -> (Book) e).forEach(bookDao::save);
        }
    }
}
