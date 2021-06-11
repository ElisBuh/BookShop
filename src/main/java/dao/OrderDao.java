package dao;

import api.dao.IOrderDao;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    private final List<Order> orders = new ArrayList<>();
    private static volatile OrderDao orderDaoInstance;

    private OrderDao() {
    }

    public static OrderDao getOrderDaoInstance() {
        if (orderDaoInstance == null) {
            orderDaoInstance = new OrderDao();
        }
        return orderDaoInstance;
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orders.remove(order);

    }

    @Override
    public List<Order> orders() {
        return new ArrayList<>(orders);
    }

    @Override
    public Order getOrder(int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void setOrder(Order order) {
        orders.stream()
                .filter(e -> e.getId() == order.getId())
                .mapToInt(orders::indexOf)
                .forEach(i -> orders.set(i, order));
    }
}
