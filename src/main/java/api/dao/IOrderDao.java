package api.dao;

import model.Order;

import java.util.List;

public interface IOrderDao {

    void save(Order order);
    void delete(Order order);
    List<Order> gelAll();
    Order get(int id);
    void set(Order order);


}
