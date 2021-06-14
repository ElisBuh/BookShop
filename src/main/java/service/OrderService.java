package service;

import api.dao.IOrderDao;
import api.service.IOrderService;
import api.service.IRequestService;
import dao.OrderDao;
import model.Book;
import model.StatusBook;
import model.Order;
import model.StatusOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class OrderService implements IOrderService {
    private int idOrder;
    private final IRequestService requestService;
    private final IOrderDao orderDao = OrderDao.getOrderDaoInstance();
    private static volatile OrderService orderServiceInstance;

    private OrderService() {
        this.requestService = RequestService.getRequestServiceInstance();
    }

    public static OrderService getOrderServiceInstance() {
        if (orderServiceInstance == null) {
            orderServiceInstance = new OrderService();
        }
        return orderServiceInstance;
    }

    @Override
    public void creatOrder(String nameClient, Book book) {
        if (book.getStatusBook().equals(StatusBook.INSTOCK)) {
            idOrder++;
            Order order = new Order(idOrder, nameClient, book, StatusOrder.NEW);
            orderDao.addOrder(order);
        } else {
            if (requestService.isRequest(book)) {
                requestService.changeCountRequest(book);
            } else {

                requestService.addRequest(book);
            }
        }

    }

    @Override
    public void cancelOrder(int id) {
        changeStatusOrder(id, StatusOrder.CANCEL);

    }

    @Override
    public void changeStatusOrder(int id, StatusOrder statusOrder) {
        Order order = orderDao.getOrder(id);
        if (statusOrder.equals(StatusOrder.COMPLETED)) {
            Book book = order.getBook();
            order.setDateComplete(LocalDate.now());
            if (requestService.isRequest(book)) {
                System.out.println("Статус изменить нельзя, так как книги нет на складе");
                return;
            }
        }
        order.setStatus(statusOrder);
        orderDao.setOrder(order);

    }

    @Override
    public List<Order> listSortOrder(TypeSortOrder typeSortOrder) {
        return orderDao.orders().stream()
                .filter(predicate(typeSortOrder))
                .sorted(comparator(typeSortOrder))
                .collect(Collectors.toList());

    }

    private Predicate<Order> predicate(TypeSortOrder typeSortOrder) {
        if (typeSortOrder == TypeSortOrder.DATA_COMPLETE) {
            return order -> order.getStatusOrder() == StatusOrder.COMPLETED;
        } else return order -> true;
    }

    private Comparator<Order> comparator(TypeSortOrder typeSortOrder) {
        if (typeSortOrder.equals(TypeSortOrder.DATA_COMPLETE)) {
            return Comparator.comparing(Order::getDateComplete);
        } else if (typeSortOrder.equals(TypeSortOrder.COST)) {
            return Comparator.comparing(Order::getCost);
        } else if (typeSortOrder.equals(TypeSortOrder.STATUS)) {
            return Comparator.comparing(Order::getStatusOrder);
        }
        return null;
    }

    @Override
    public List<Order> listOrderCompleteForPeriodForTime(LocalDate localDateStart, LocalDate localDateEnd) {
        return orderDao.orders().stream()
                .filter(order -> order.getStatusOrder() == StatusOrder.COMPLETED)
                .filter(order -> TimeUtil.isBetweenHalfOpen(order.getDateComplete(), localDateStart, localDateEnd))
                .sorted(Comparator.comparing(order -> order.getBook().getNameBook()))
                .collect(Collectors.toList());
    }

    @Override
    public int AmountOfMoneyForPeriodForTime(LocalDate localDateStart, LocalDate localDateEnd) {
        return orderDao.orders().stream().filter(order -> order.getStatusOrder() == StatusOrder.COMPLETED)
                .filter(order -> TimeUtil.isBetweenHalfOpen(order.getDateComplete(), localDateStart, localDateEnd))
                .mapToInt(Order::getCost)
                .sum();
    }

    @Override
    public int countCompleteOrders(LocalDate localDateStart, LocalDate localDateEnd) {
        return (int) orderDao.orders().stream()
                .filter(order -> order.getStatusOrder() == StatusOrder.COMPLETED)
                .filter(order -> TimeUtil.isBetweenHalfOpen(order.getDateComplete(), localDateStart, localDateEnd))
                .count();
    }

    @Override
    public void deleteOrder(int id) {
        orderDao.deleteOrder(orderDao.getOrder(id));
    }

    @Override
    public Order getOrder(int id) {
        return orderDao.getOrder(id);
    }

    @Override
    public List<Order> ListOrders() {
        return new ArrayList<>(orderDao.orders());

    }

}
