package dao;

import api.dao.IOrderDao;
import exceptions.DaoException;
import model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    private static final Logger log = LoggerFactory.getLogger(OrderDao.class);

    private List<Order> orders = new ArrayList<>();
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
        log.info("Get_Order_By_ID: {}", id);
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElseThrow(() -> new DaoException("id:" + id + "-not found"));
    }

    @Override
    public void setOrder(Order order) {
        orders.stream()
                .filter(e -> e.getId() == order.getId())
                .mapToInt(orders::indexOf)
                .forEach(i -> orders.set(i, order));
    }

    @Override
    public <T> void set(List<T> list) {
        this.orders = (List<Order>) list;
    }
}
