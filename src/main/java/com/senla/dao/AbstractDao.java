package com.senla.dao;

import com.senla.api.dao.GenericDao;
import com.senla.exceptions.DaoException;
import com.senla.model.AEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public abstract class AbstractDao<T extends AEntity> implements GenericDao<T> {
    protected static final Logger log = LoggerFactory.getLogger(AbstractDao.class);

    @Override
    public T save(T t) {
        log.info("Save {}: To BD", t.toString());
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            session.close();
            return t;
    }

    @Override
    public T get(Integer id) {
        log.info("Get_{}_By_ID: {}", aClass(), id);
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = cb.createQuery(aClass());
            Root<T> root = criteria.from(aClass());
            criteria.select(root).where(cb.equal(root.get("id"),id));
            T entity =session.createQuery(criteria).getSingleResult();

//            T entity = session.get(aClass(), id);
            if (entity == null) {
                throw new DaoException("Такого id нет");
            } else return entity;
    }

    @Override
    public List<T> getAll() {
        log.info("getAll-{}", this.getClass().getSimpleName());
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = cb.createQuery(aClass());
            Root<T> root = criteria.from(aClass());
            criteria.select(root);
            return session.createQuery(criteria).list();

//            return session.createQuery(query(), aClass()).list();
    }

    @Override
    public boolean delete(T t) {
        log.info("Delete {}", t.toString());
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            session.close();
            return true;
    }

    @Override
    public T update(T t) {
        log.info("Update {} To BD", t.toString());
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            session.close();
            return t;
    }

//    protected abstract String query();

    protected abstract Class<T> aClass();
}
