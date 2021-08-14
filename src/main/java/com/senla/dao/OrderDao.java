package com.senla.dao;

import com.senla.api.dao.IOrderDao;
import com.senla.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OrderDao extends AbstractDao<Order> implements IOrderDao {
    private static final Logger log = LoggerFactory.getLogger(OrderDao.class);

//    @Override
//    protected String query() {
//        return "from Order";
//    }

    @Override
    protected Class<Order> aClass() {
        return Order.class;
    }
}
