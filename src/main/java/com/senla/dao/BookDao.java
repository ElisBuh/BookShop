package com.senla.dao;

import com.senla.api.dao.IBookDao;
import com.senla.model.Book;
import com.senla.util.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Singleton
public class BookDao extends AbstractDao<Book> implements IBookDao {
    private static final Logger log = LoggerFactory.getLogger(BookDao.class);

//    @Override
//    protected String query() {
//        return "from Book";
//    }

    @Override
    protected Class<Book> aClass() {
        return Book.class;
    }

}
