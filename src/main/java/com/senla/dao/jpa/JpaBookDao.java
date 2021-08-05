package com.senla.dao.jpa;

import com.senla.api.dao.IBookDao;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.util.annotation.Singleton;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class JpaBookDao implements IBookDao {
    private static final Logger log = LoggerFactory.getLogger(JpaBookDao.class);


    @Override
    public void save(Book book) {
        log.info("Save Book: {} To BD", book.toString());
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            session.close();
        } catch (DaoException e) {
            log.error("BookDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }

    }

    @Override
    public Book get(int id) {
        log.info("Get_Book_By_ID: {}", id);
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            transaction.commit();
            session.close();
            return book;
        } catch (DaoException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> getAll() {
        log.info("getAll-BookDao");
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<Book> books = session.createQuery("from Book ").list();
            transaction.commit();
            session.close();
            return books;
        } catch (DaoException e) {
            log.error("BookDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Book book) {
        log.info("Delete book: {}", book.toString());
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(book);
            transaction.commit();
            session.close();
            return true;
        } catch (DaoException e) {
            log.error("BookDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void set(Book book) {
        log.info("Set Book: {} To BD", book.toString());
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
            session.close();
        } catch (DaoException e) {
            log.error("BookDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }

    }
}
