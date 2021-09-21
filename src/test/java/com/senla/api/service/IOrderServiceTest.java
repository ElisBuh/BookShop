package com.senla.api.service;

import DataTest.BookTestData;
import DataTest.OrderTestData;
import com.senla.exceptions.DaoException;
import com.senla.model.Order;
import com.senla.model.StatusOrder;
import com.senla.service.TypeSortOrder;
import com.senla.util.utilits.TimeBetweenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.HsqldbDataConfig;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@ContextConfiguration(classes = HsqldbDataConfig.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Sql(scripts = "classpath:book_shop_initDB_hsqldb.sql")
@Sql(scripts = "classpath:book_shop_populateDB_hsqldb.sql")
public class IOrderServiceTest {

    @Autowired
    private IOrderService orderService;

    private final LocalDate start = LocalDate.of(2020, 1, 1);
    private final LocalDate end = LocalDate.of(2021, 12, 31);

    @Test
    public void creat() {
        orderService.creat("newClient", BookTestData.BOOK_8);
        Order order = orderService.getOrder(100003);
        assertEquals(order, OrderTestData.NEW_ORDER);

    }

    @Test
    public void cancel() {
        orderService.cancel(100001);
        Order order = orderService.getOrder(100001);
        assertEquals(order, OrderTestData.CANCEL_ORDER);
    }

    @Test
    public void changeStatusOrder() {
        orderService.changeStatusOrder(100001, StatusOrder.COMPLETED);
        Order order = orderService.getOrder(100001);
        assertEquals(order, OrderTestData.COMPLETED_ORDER);
    }

    @Test
    public void getAll() {
        List<Order> orders = orderService.getAll(1, 10);
        assertEquals(orders, OrderTestData.ORDER_LIST);
    }

    @Test
    public void delete() {
        orderService.delete(100001);
        List<Order> orderList = orderService.getAll(1, 10);
        assertEquals(orderList, OrderTestData.ORDER_LIST_DELETE);
    }

    @Test
    public void deleteNotOrder() {
        assertThrows(DaoException.class, () -> orderService.delete(1));

    }

    @Test
    public void getOrder() {
        Order order = orderService.getOrder(100000);
        assertEquals(OrderTestData.ORDER_1, order);

    }

    @Test
    public void getNotOrder() {
        assertThrows(DaoException.class, () -> orderService.getOrder(1));
    }

    @Test
    public void listSortOrder() {
        assertEquals(
                orderService.listSortOrder(1, 10, TypeSortOrder.COST),
                OrderTestData.ORDER_LIST.stream()
                        .sorted(Comparator.comparing(Order::getCost))
                        .collect(Collectors.toList()));
        assertEquals(
                orderService.listSortOrder(1, 10, TypeSortOrder.STATUS),
                OrderTestData.ORDER_LIST.stream()
                        .sorted(Comparator.comparing(Order::getStatusOrder))
                        .collect(Collectors.toList()));
        assertEquals(
                orderService.listSortOrder(1, 10, TypeSortOrder.DATA_COMPLETE),
                OrderTestData.ORDER_LIST.stream()
                        .filter(order -> order.getStatusOrder() == StatusOrder.COMPLETED)
                        .sorted(Comparator.comparing(Order::getStatusOrder))
                        .collect(Collectors.toList()));

    }

    @Test
    public void listOrderCompleteForPeriodForTime() {
        assertEquals(
                orderService.listOrderCompleteForPeriodForTime(1, 10, start, end),
                OrderTestData.ORDER_LIST.stream()
                        .filter(order -> order.getStatusOrder() == StatusOrder.COMPLETED)
                        .filter(order -> TimeBetweenUtil.isBetweenHalfOpen(order.getDateComplete(), start, end))
                        .sorted(Comparator.comparing(order -> order.getBook().getNameBook()))
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void amountOfMoneyForPeriodForTime() {
        assertEquals(orderService.amountOfMoneyForPeriodForTime(start, end), 12);
    }

    @Test
    public void countCompleteOrders() {
        assertEquals(orderService.countCompleteOrders(start, end), 1);
    }
}