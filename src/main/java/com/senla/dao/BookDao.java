package com.senla.dao;

import com.senla.api.dao.IBookDao;
import com.senla.model.Book;
import org.springframework.stereotype.Repository;


@Repository
public class BookDao extends AbstractDao<Book> implements IBookDao {
    @Override
    protected Class<Book> aClass() {
        return Book.class;
    }

}
