package com.senla.api.service;

import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.StatusOrder;
import com.senla.service.TypeSortOrder;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    void creat(String nameClient, Book book);
    void cancel(int id);
    void changeStatusOrder(int id, StatusOrder statusOrder);
    List<Order> getAll();
    void delete(int id);
    Order getOrder(int id);
    List<Order> listSortOrder(TypeSortOrder typeSortOrder);
    List<Order> listOrderCompleteForPeriodForTime(LocalDate localDateStart,LocalDate localDateEnd);
    int amountOfMoneyForPeriodForTime(LocalDate localDateStart,LocalDate localDateEnd);
    int countCompleteOrders(LocalDate localDateStart, LocalDate localDateEnd);
}
