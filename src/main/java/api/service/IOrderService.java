package api.service;

import model.book.Book;
import model.order.Order;
import model.order.StatusOrder;

public interface IOrderService {

    void creatOrder(String nameClient, Book book);
    void cancelOrder(int id);
    void changeStatusOrder(int id, StatusOrder statusOrder);
    void printOrder();
    void deleteOrder(int id);
    Order getOrder(int id);
}
