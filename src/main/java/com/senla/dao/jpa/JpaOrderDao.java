package com.senla.dao.jpa;

import com.senla.api.dao.IOrderDao;
import com.senla.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JpaOrderDao extends JpaAbstractDao<Order> implements IOrderDao {
    private static final Logger log = LoggerFactory.getLogger(JpaOrderDao.class);

    @Override
    protected String query() {
        return "from Order";
    }

    @Override
    protected Class<Order> aClass() {
        return Order.class;
    }
}
