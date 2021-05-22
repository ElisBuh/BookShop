package api.dao;

import model.order.Order;

import java.util.ArrayList;

public interface IOrderDao {

    void addOrder(Order order);
    void deleteOrder(Order order);
    ArrayList<Order> printOrder();
    Order getOrder(int id);
    void setOrder(Order order);

}
