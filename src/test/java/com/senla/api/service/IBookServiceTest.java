package com.senla.api.service;

import DataTest.BookTestData;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.service.TypeSortBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.HsqldbDataConfig;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@ContextConfiguration(classes = HsqldbDataConfig.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Sql(scripts = "classpath:book_shop_initDB_hsqldb.sql")
@Sql(scripts = "classpath:book_shop_populateDB_hsqldb.sql")
public class IBookServiceTest {

    @Autowired
    private IBookService bookService;


    @Test
    public void save() {
        bookService.save("newBook", "newAuthor", 10, LocalDate.of(2000, 1, 1));
        Book book = bookService.get(100009);
        assertEquals(book, BookTestData.NEW_BOOK);
    }

    @Test
    public void get() {
        Book book = bookService.get(100000);
        assertEquals(book, BookTestData.BOOK_1);
    }

    @Test
    public void getNotBook() {
        assertThrows(DaoException.class, () -> bookService.get(1));
    }

    @Test
    public void listSortBooks() {
        assertEquals(
                bookService.listSortBooks(1, 10, TypeSortBook.NAME_BOOK),
                BookTestData.BOOK_LIST.stream()
                        .sorted(Comparator.comparing(Book::getNameBook))
                        .collect(Collectors.toList()));
        assertEquals(
                bookService.listSortBooks(1, 10, TypeSortBook.DATE),
                BookTestData.BOOK_LIST.stream()
                        .sorted(Comparator.comparing(Book::getDate))
                        .collect(Collectors.toList()));
        assertEquals(
                bookService.listSortBooks(1, 10, TypeSortBook.PRICE),
                BookTestData.BOOK_LIST.stream()
                        .sorted(Comparator.comparing(Book::getPrice))
                        .collect(Collectors.toList()));
        assertEquals(
                bookService.listSortBooks(1, 10, TypeSortBook.IN_STOCK),
                BookTestData.BOOK_LIST.stream()
                        .sorted(Comparator.comparing(Book::getStatusBook))
                        .collect(Collectors.toList()));
    }

    @Test
    public void getAll() {
        List<Book> books = bookService.getAll(1, 10);
        assertEquals(books, BookTestData.BOOK_LIST);
    }
}