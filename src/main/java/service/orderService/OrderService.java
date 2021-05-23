package service.orderService;

import api.dao.IOrderDao;
import api.service.IOrderService;
import api.service.IRequestService;
import dao.OrderDao;
import model.book.Book;
import model.book.StatusBook;
import model.order.Order;
import model.order.StatusOrder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.function.Predicate;


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
    public void sortOrder(TypeSortOrder typeSortOrder) {
        if (typeSortOrder.equals(TypeSortOrder.DATA_COMPLETE)){
            iOrderDao.orders().stream()
                    .filter(order -> order.getStatusOrder()==StatusOrder.COMPLETED)
                    .sorted(Comparator.comparing(Order::getDateComplete))
                    .forEach(System.out::println);
        }else if (typeSortOrder.equals(TypeSortOrder.COST)){
            iOrderDao.orders().stream()
                    .sorted(Comparator.comparing(Order::getCost))
                    .forEach(System.out::println);
        }else if (typeSortOrder.equals(TypeSortOrder.STATUS)){
            iOrderDao.orders().stream()
                    .sorted(Comparator.comparing(Order::getStatusOrder))
                    .forEach(System.out::println);
        }
    }

    public boolean isBetweenHalfOpen(LocalDate lt, LocalDate startTime, LocalDate endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    @Override
    public void printOrderCompleteForPeriodForTime(LocalDate localDateStart, LocalDate localDateEnd) {
        iOrderDao.orders().stream()
                .filter(order -> order.getStatusOrder()==StatusOrder.COMPLETED)
                .filter(order ->isBetweenHalfOpen(order.getDateComplete(),localDateStart,localDateEnd))
                .sorted(Comparator.comparing(order -> order.getBook().getNameBook()))
                .forEach(System.out::println);
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
        for (Order order : iOrderDao.orders()) System.out.println(order);
    }

}
