package model.order;

import model.book.Book;

public class Order {
    private int id;
    private String nameClient;
    private Book book;
    private StatusOrder statusOrder;

    public Order(int id, String nameClient, Book book, StatusOrder statusOrder) {
        this.nameClient = nameClient;
        this.book = book;
        this.statusOrder = statusOrder;
        this.id = id;

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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", nameClient='" + nameClient + '\'' +
                ", book=" + book +
                ", statusOrder=" + statusOrder +
                '}';
    }
}
