package com.senla.api.service;

import DataTest.BookTestData;
import DataTest.StorageTestData;
import com.senla.model.Book;
import com.senla.model.Storage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.HsqldbDataConfig;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = HsqldbDataConfig.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Sql(scripts = "classpath:book_shop_initDB_hsqldb.sql")
@Sql(scripts = "classpath:book_shop_populateDB_hsqldb.sql")
public class IStorageServiceTest {

    @Autowired
    private IStorageService storageService;

    @Autowired
    private IBookService bookService;

    @Test
    public void add() {
        storageService.add(BookTestData.BOOK_9, LocalDate.of(2020, 1, 20));
        Book book = bookService.get(BookTestData.BOOK_9.getId());
        assertEquals(book, BookTestData.NEW_BOOK_STORAGE);
        List<Storage> storageList = storageService.getAll(1, 10);
        assertEquals(storageList, StorageTestData.ADD_STORAGE_LIST);
    }

    @Test
    public void bookNotSellMoreNmonth() {
        List<Storage> storageList = storageService.bookNotSellMoreNmonth(1, 10);
        assertEquals(storageList, StorageTestData.STORAGE_LIST);
    }

    @Test
    public void getAll() {
        List<Storage> storageList = storageService.getAll(1, 10);
        assertEquals(storageList, StorageTestData.STORAGE_LIST);
    }

    @Test
    public void delete() {
        Book book = new Book(BookTestData.BOOK_6);
        storageService.delete(book);
        List<Storage> storageList = storageService.getAll(1, 10);
        assertEquals(storageList, StorageTestData.DELETE_STORAGE_LIST);
    }
}
