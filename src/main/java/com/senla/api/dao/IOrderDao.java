package com.senla.api.dao;

import com.senla.model.Order;

import java.util.List;

public interface IOrderDao {

    void save(Order order);
    void delete(Order order);
    List<Order> gelAll();
    Order get(int id);
    void set(Order order);


}
