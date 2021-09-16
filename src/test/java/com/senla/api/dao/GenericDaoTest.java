package com.senla.api.dao;

import DataTest.BookTestData;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.StatusBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.HsqldbDataConfig;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(classes = HsqldbDataConfig.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Sql(scripts = "classpath:book_shop_initDB_hsqldb.sql")
@Sql(scripts = "classpath:book_shop_populateDB_hsqldb.sql")
public class GenericDaoTest {

    @Autowired
    private GenericDao<Book> genericDao;

    @Test
    public void save() {
        genericDao.save(BookTestData.NEW_BOOK);
        Book book = genericDao.get(BookTestData.NEW_BOOK.getId());
        assertEquals(book,BookTestData.NEW_BOOK);
    }

    @Test
    public void get() {
        Book book = genericDao.get(100000);
        assertEquals(book,BookTestData.BOOK_1);
    }

    @Test
    public void getAll() {
        List<Book> bookList = genericDao.getAll();
        assertEquals(bookList, BookTestData.BOOK_LIST);
    }

    @Test
    public void delete() {
        genericDao.delete(BookTestData.BOOK_1);
        assertThrows(DaoException.class, () -> genericDao.get(BookTestData.BOOK_1.getId()));
    }

    @Test
    public void update() {
        Book book = new Book(BookTestData.BOOK_9);
        book.setStatusBook(StatusBook.INSTOCK);
        assertEquals(book,BookTestData.UPDATE_BOOK_9);
    }
}