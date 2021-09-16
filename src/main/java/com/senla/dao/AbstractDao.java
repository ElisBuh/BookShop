package com.senla.dao;

import com.senla.api.dao.GenericDao;
import com.senla.exceptions.DaoException;
import com.senla.model.AEntity;
import javassist.NotFoundException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public abstract class AbstractDao<T extends AEntity> implements GenericDao<T> {
    protected static final Logger log = LoggerFactory.getLogger(AbstractDao.class);

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public T save(T t) {
        log.info("Save {}: To BD", t.toString());
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            session.close();
            return t;
    }

    @Override
    public T get(Integer id) {
        log.info("Get_{}_By_ID: {}", aClass(), id);
        try {

            Session session = sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = cb.createQuery(aClass());
            Root<T> root = criteria.from(aClass());
            criteria.select(root).where(cb.equal(root.get("id"),id));
            return session.createQuery(criteria).getSingleResult();
//
//            if (entity == null) {
//                throw new DaoException("Такого id нет");
//            } else return entity;
        }catch (NoResultException e){
            throw new DaoException("Такого id нет");
        }
    }

    @Override
    public List<T> getAll(int pageNumber, int pageSize) {
        log.info("getAll-{}", this.getClass().getSimpleName());
//        int pageNumber = 1;
//        int pageSize = 10;
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = criteriaBuilder
                .createQuery(Long.class);
        countQuery.select(criteriaBuilder
                .count(countQuery.from(aClass())));
        Long count = session.createQuery(countQuery)
                .getSingleResult();

        CriteriaQuery<T> criteriaQuery = criteriaBuilder
                .createQuery(aClass());
        Root<T> from = criteriaQuery.from(aClass());
        CriteriaQuery<T> select = criteriaQuery.select(from);

        TypedQuery<T> typedQuery = session.createQuery(select);
        while (pageNumber < count.intValue()) {
            typedQuery.setFirstResult(pageNumber - 1);
            typedQuery.setMaxResults(pageSize);
            log.info("Current page: {}", pageNumber);
            pageNumber += pageSize;
        }
        return typedQuery.getResultList();
    }

    @Override
    public List<T> getAll() {
        log.info("getAll-{}", this.getClass().getSimpleName());
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(aClass());
        Root<T> root = criteria.from(aClass());
        criteria.select(root);
        return session.createQuery(criteria).list();
    }

    @Override
    public boolean delete(T t) {
        log.info("Delete {}", t.toString());
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            session.close();
            return true;
    }

    @Override
    public T update(T t) {
        log.info("Update {} To BD", t.toString());
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            session.close();
            return t;
    }

    protected abstract Class<T> aClass();
}
