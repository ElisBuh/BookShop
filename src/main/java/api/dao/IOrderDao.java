package api.dao;

import model.Order;

import java.util.List;

public interface IOrderDao extends AbstractDao {

    void addOrder(Order order);
    void deleteOrder(Order order);
    List<Order> orders();
    Order getOrder(int id);
    void setOrder(Order order);


}
