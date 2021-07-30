package com.senla.model;

import java.io.Serializable;
import java.time.LocalDate;


public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public Order(int id, String nameClient, Book book, LocalDate dateComplete, StatusOrder statusOrder) {
        this.id = id;
        this.nameClient = nameClient;
        this.book = book;
        this.cost = book.getPrice();
        this.dateComplete = dateComplete;
        this.statusOrder = statusOrder;
    }

    public Order(String nameClient, Book book, StatusOrder statusOrder) {
        this.nameClient = nameClient;
        this.book = book;
        this.cost = book.getPrice();
        this.statusOrder = statusOrder;
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

    public int getCost() {
        return cost;
    }

    public LocalDate getDateComplete() {
        return dateComplete;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setDateComplete(LocalDate dateComplete) {
        this.dateComplete = dateComplete;
    }

    public String getNameClient() {
        return nameClient;
    }

    @Override
    public String toString() {
        if (dateComplete == null) {
            return "Заказ" +
                    "id: " + id +
                    ", Имя Клиента " + nameClient + '\'' +
                    ", " + book +
                    ", Стоимость: " + cost +
                    ", Статус заказа: " + statusOrder;
        } else {
            return "Заказ" +
                    "id: " + id +
                    ", Имя Клиента " + nameClient + '\'' +
                    ", " + book +
                    ", Стоимость: " + cost +
                    ", Дата выполнения: " + dateComplete +
                    ", Статус заказа: " + statusOrder;
        }
    }
}
