package dao;

import api.dao.IOrderDao;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    private final List<Order> orders = new ArrayList<>();

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
        for (Order order : orders) {
            if (order.getId() == id) return order;
        }
        return null;
    }

    @Override
    public void setOrder(Order order) {
        for (Order e : orders) {
            if (e.getId() == order.getId()) {
                int i = orders.indexOf(e);
                orders.set(i, order);
            }
        }
    }
}
