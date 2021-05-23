package service;

import api.dao.IOrderDao;
import api.service.IOrderService;
import api.service.IRequestService;
import dao.OrderDao;
import model.book.Book;
import model.book.StatusBook;
import model.order.Order;
import model.order.StatusOrder;
import java.time.LocalDate;


public class OrderService implements IOrderService {
    private int idOrder;
    private Order order;
    private IRequestService iRequestService;
    private IOrderDao iOrderDao = new OrderDao();


    public OrderService(IRequestService iRequestService) {
        this.iRequestService = iRequestService;

    }

    @Override
    public void creatOrder(String nameClient, Book book) {
        if (book.getStatusBook().equals(StatusBook.INSTOCK)) {
            idOrder++;
            order = new Order(idOrder, nameClient, book, StatusOrder.NEW);
            iOrderDao.addOrder(order);
        } else {
            if (iRequestService.isRequest(book)) {
                iRequestService.changeCountRequest(book);
            } else {

                iRequestService.addRequest(book);
            }
        }

    }

    @Override
    public void cancelOrder(int id) {
        changeStatusOrder(id, StatusOrder.CANCEL);

    }

    @Override
    public void changeStatusOrder(int id, StatusOrder statusOrder) {
        order = iOrderDao.getOrder(id);
        if (statusOrder.equals(StatusOrder.COMPLETED)) {
            Book book = order.getBook();
            order.setDateComplete(LocalDate.now());
            if (iRequestService.isRequest(book)) {
                System.out.println("Статус изменить нельзя, так как книги нет на складе");
                return;
            }
        }
        order.setStatus(statusOrder);
        iOrderDao.setOrder(order);

    }

    @Override
    public void deleteOrder(int id) {
        iOrderDao.deleteOrder(iOrderDao.getOrder(id));
    }

    @Override
    public Order getOrder(int id) {
        return iOrderDao.getOrder(id);
    }

    @Override
    public void printOrder() {
        for (Order order : iOrderDao.printOrder()) System.out.println(order);
    }

}
