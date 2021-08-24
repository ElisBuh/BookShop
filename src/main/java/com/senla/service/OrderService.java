package com.senla.service;

import com.senla.api.dao.IOrderDao;
import com.senla.api.service.IOrderService;
import com.senla.api.service.IRequestService;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.StatusBook;
import com.senla.model.Order;
import com.senla.model.StatusOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public final class OrderService implements IOrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final IRequestService requestService;
    private final IOrderDao orderDao;

    public OrderService(IRequestService requestService, IOrderDao orderDao) {
        this.requestService = requestService;
        this.orderDao = orderDao;
    }


    @Override
    public void creat(String nameClient, Book book) {
        try {
            log.info("creat_Order_BY_Book: {}, id{}", book.getNameBook(), book.getId());
            if (book.getStatusBook().equals(StatusBook.INSTOCK)) {
                Order order = new Order(nameClient, book, StatusOrder.NEW);
                orderDao.save(order);
            } else {
                if (requestService.isRequest(book)) {
                    requestService.changeCountRequest(book);
                } else {

                    requestService.save(book);
                }
            }
        } catch (DaoException e) {
            log.error("creatOrder Client--Book: {}, {}", nameClient, book.getNameBook());
            throw e;
        }

    }

    @Override
    public void cancel(int id) {
        try {
            log.info("Cancel_Order_BY_Id: {}", id);
            changeStatusOrder(id, StatusOrder.CANCEL);
        } catch (DaoException e) {
            log.error("cancelOrder id: {}, {}", id, e.toString());
            throw e;
        }

    }

    @Override
    public void changeStatusOrder(int id, StatusOrder statusOrder) {
        try {
            log.info("Change_Status_Order_BY_Id: {}", id);
            Order order = orderDao.get(id);
            if (statusOrder.equals(StatusOrder.COMPLETED)) {
                Book book = order.getBook();
                order.setDateComplete(LocalDate.now());
                if (requestService.isRequest(book)) {
                    System.out.println("Статус изменить нельзя, так как книги нет на складе");
                    return;
                }
            }
            order.setStatus(statusOrder);
            orderDao.update(order);
        } catch (DaoException e) {
            log.error("changeOrder id: {}, {}", id, e.toString());
            throw e;
        }

    }

    @Override
    public List<Order> listSortOrder(TypeSortOrder typeSortOrder) {
        log.info("ListSortOrder-OrderService");
        try {
            return orderDao.getAll().stream()
                    .filter(predicate(typeSortOrder))
                    .sorted(comparator(typeSortOrder))
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }

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
        log.info("{}-OrderService", Method.class.getName());
        try {
            return orderDao.getAll().stream()
                    .filter(order -> order.getStatusOrder() == StatusOrder.COMPLETED)
                    .filter(order -> TimeUtil.isBetweenHalfOpen(order.getDateComplete(), localDateStart, localDateEnd))
                    .sorted(Comparator.comparing(order -> order.getBook().getNameBook()))
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }
    }

    @Override
    public int AmountOfMoneyForPeriodForTime(LocalDate localDateStart, LocalDate localDateEnd) {
        log.info("{}-OrderService", Method.class.getName());
        try {
            return orderDao.getAll().stream().filter(order -> order.getStatusOrder() == StatusOrder.COMPLETED)
                    .filter(order -> TimeUtil.isBetweenHalfOpen(order.getDateComplete(), localDateStart, localDateEnd))
                    .mapToInt(Order::getCost)
                    .sum();
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }
    }

    @Override
    public int countCompleteOrders(LocalDate localDateStart, LocalDate localDateEnd) {
        log.info("{}-OrderService", Method.class.getName());
        try {
            return (int) orderDao.getAll().stream()
                    .filter(order -> order.getStatusOrder() == StatusOrder.COMPLETED)
                    .filter(order -> TimeUtil.isBetweenHalfOpen(order.getDateComplete(), localDateStart, localDateEnd))
                    .count();
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }
    }

    @Override
    public void delete(int id) {
        try {
            log.info("Delete_Order_BY_Id: {}", id);
            orderDao.delete(orderDao.get(id));
        } catch (DaoException e) {
            log.error("deleteOrder id: {}, {}", id, e.toString());
            throw e;
        }
    }

    @Override
    public Order getOrder(int id) {
        try {
            log.info("Get_Order_BY_Id: {}", id);
            return orderDao.get(id);
        } catch (DaoException e) {
            log.error("getOrder id: {}, {}", id, e.toString());
            throw e;
        }
    }

    @Override
    public List<Order> getAll() {
        log.info("{}-OrderService", Method.class.getName());
        try {
            return new ArrayList<>(orderDao.getAll());
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }

    }

    public IRequestService requestService() {
        return requestService;
    }

    public IOrderDao orderDao() {
        return orderDao;
    }


}
