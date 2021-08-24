package com.senla.dao;

import com.senla.api.dao.IOrderDao;
import com.senla.model.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = "singleton")
public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    @Override
    protected Class<Order> aClass() {
        return Order.class;
    }
}
