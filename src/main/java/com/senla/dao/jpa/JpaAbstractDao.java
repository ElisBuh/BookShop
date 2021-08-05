package com.senla.dao.jpa;

import com.senla.api.dao.GenericDao;
import com.senla.exceptions.DaoException;
import com.senla.model.AEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public abstract class JpaAbstractDao<T extends AEntity> implements GenericDao<T> {
    private static final Logger log = LoggerFactory.getLogger(JpaAbstractDao.class);

    @Override
    public T save(T t) {
        log.info("Save {}: To BD", t.toString());
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            session.close();
            return t;
        } catch (DaoException e) {
            log.error(this.getClass().getSimpleName(), e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public T get(Integer id) {
        log.info("Get_{}_By_ID: {}", aClass(), id);
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            T entity = session.get(aClass(), id);
            if (entity == null) {
                throw new DaoException("Такого id нет");
            } else return entity;
        } catch (DaoException e) {
            log.info(this.getClass().getSimpleName(), e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public List<T> getAll() {
        log.info("getAll-{}", this.getClass().getSimpleName());
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            return session.createQuery(query(), aClass()).list();
        } catch (DaoException e) {
            log.error(this.getClass().getSimpleName(), e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(T t) {
        log.info("Delete {}", t.toString());
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            session.close();
            return true;
        } catch (DaoException e) {
            log.error(this.getClass().getSimpleName(), e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public T update(T t) {
        log.info("Update {} To BD", t.toString());
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            session.close();
            return t;
        } catch (DaoException e) {
            log.error(this.getClass().getSimpleName(), e.getMessage());
            throw new DaoException(e);
        }
    }

    protected abstract String query();

    protected abstract Class<T> aClass();
}
