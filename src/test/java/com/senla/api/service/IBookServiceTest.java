package com.senla.api.service;

import com.senla.model.Book;
import com.senla.model.StatusBook;
import com.senla.util.config.DataConfig;
import com.senla.util.init.SpringMvcDispatcherSerlvetIntitializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.Config;
import util.HsqldbDataConfig;

import java.time.LocalDate;

import static org.junit.Assert.*;
@ContextConfiguration(classes = HsqldbDataConfig.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
//@Sql(scripts = "classpath:book_shop_populateDB_hsqldb.sql", config = @SqlConfig(encoding = "UTF-8"))
@Sql(scripts = "classpath:book_shop_initDB_hsqldb.sql")
@Sql(scripts = "classpath:book_shop_populateDB_hsqldb.sql")
public class IBookServiceTest {

    @Autowired
    private IBookService service;

    private static final Book book = new Book(100000,"Война и Мир","Лев Толстой", LocalDate.of(2020,1,30), 25, StatusBook.ABSENT);

//    public IBookServiceTest(IBookService service) {
//        this.service = service;
//    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void save() {
    }

    @Test
    public void get() {
        Book book2 = service.get(100000);
        Assert.assertEquals(book, book2);
    }

    @Test
    public void listSortBooks() {
    }

    @Test
    public void getAll() {
    }
}