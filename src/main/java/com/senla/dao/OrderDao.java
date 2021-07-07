package com.senla.dao;

import com.senla.api.dao.IOrderDao;
import com.senla.exceptions.DaoException;
import com.senla.model.Order;
import com.senla.util.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class OrderDao implements IOrderDao {
    private static final Logger log = LoggerFactory.getLogger(OrderDao.class);

    private final List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public void delete(Order order) {
        orders.remove(order);

    }

    @Override
    public List<Order> gelAll() {
        return new ArrayList<>(orders);
    }

    @Override
    public Order get(int id) {
        log.info("Get_Order_By_ID: {}", id);
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElseThrow(() -> new DaoException("id:" + id + "-not found"));
    }

    @Override
    public void set(Order order) {
        orders.stream()
                .filter(e -> e.getId() == order.getId())
                .mapToInt(orders::indexOf)
                .forEach(i -> orders.set(i, order));
    }
}
