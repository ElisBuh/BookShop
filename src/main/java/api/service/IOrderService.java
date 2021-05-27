package api.service;

import model.Book;
import model.Order;
import model.StatusOrder;
import service.TypeSortOrder;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    void creatOrder(String nameClient, Book book);
    void cancelOrder(int id);
    void changeStatusOrder(int id, StatusOrder statusOrder);
    List<Order> ListOrders();
    void deleteOrder(int id);
    Order getOrder(int id);
    List<Order> listSortOrder(TypeSortOrder typeSortOrder);
    List<Order> listOrderCompleteForPeriodForTime(LocalDate localDateStart,LocalDate localDateEnd);
    int AmountOfMoneyForPeriodForTime(LocalDate localDateStart,LocalDate localDateEnd);
    int countCompleteOrders(LocalDate localDateStart, LocalDate localDateEnd);
}
