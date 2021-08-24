package com.senla.dao;

import com.senla.api.dao.IBookDao;
import com.senla.model.Book;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository
@Scope(value = "singleton")
public class BookDao extends AbstractDao<Book> implements IBookDao {
    @Override
    protected Class<Book> aClass() {
        return Book.class;
    }

}
