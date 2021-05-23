package model.order;

import model.book.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {
    private int id;
    private String nameClient;
    private Book book;
    private int cost;
    private LocalDate dateComplete;
    private StatusOrder statusOrder;

    public Order(int id, String nameClient, Book book, StatusOrder statusOrder) {
        this.nameClient = nameClient;
        this.book = book;
        this.statusOrder = statusOrder;
        this.id = id;
        this.cost = book.getPrice();

    }

    public int getId() {
        return id;
    }

    public void setStatus(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Book getBook() {
        return book;
    }

    public void setDateComplete(LocalDate dateComplete) {
        this.dateComplete = dateComplete;
    }


    @Override
    public String toString() {
        if (dateComplete==null){
            return "Order{" +
                    "id=" + id +
                    ", nameClient='" + nameClient + '\'' +
                    ", book=" + book +
                    ", cost=" + cost +
                    ", statusOrder=" + statusOrder +
                    '}';
        }
        else {
        return "Order{" +
                "id=" + id +
                ", nameClient='" + nameClient + '\'' +
                ", book=" + book +
                ", cost=" + cost +
                ", dateComplete=" + dateComplete +
                ", statusOrder=" + statusOrder +
                '}';
    }
    }
}
