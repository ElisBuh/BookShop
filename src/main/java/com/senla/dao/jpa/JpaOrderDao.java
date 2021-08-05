package com.senla.dao.jpa;

import com.senla.api.dao.IOrderDao;
import com.senla.exceptions.DaoException;
import com.senla.model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JpaOrderDao implements IOrderDao {
    private static final Logger log = LoggerFactory.getLogger(JpaOrderDao.class);

    @Override
    public void save(Order order) {
        log.info("Save Order: {} To BD", order.toString());
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            session.close();
        } catch (DaoException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Order order) {
        log.info("Delete order: {}", order.toString());
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
            session.close();
        } catch (DaoException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> gelAll() {
        log.info("getAll-OrderDao");
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            return session.createQuery("from Order", Order.class).list();
        } catch (DaoException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Order get(int id) {
        log.info("Get_Order_By_ID: {}", id);
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Order order = session.get(Order.class, id);
            if (order == null) {
                throw new DaoException("Такого заказа нет");
            } else return session.get(Order.class, id);
        } catch (DaoException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void set(Order order) {
        log.info("Set_Order_By_ID: {}", order.toString());
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
            session.close();
        } catch (DaoException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }
}
