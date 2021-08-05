package com.senla.dao.jpa;

import com.senla.api.dao.IBookDao;
import com.senla.model.Book;
import com.senla.util.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Singleton
public class JpaBookDao extends JpaAbstractDao<Book> implements IBookDao {
    private static final Logger log = LoggerFactory.getLogger(JpaBookDao.class);

    @Override
    protected String query() {
        return "from Book";
    }

    @Override
    protected Class<Book> aClass() {
        return Book.class;
    }

}
