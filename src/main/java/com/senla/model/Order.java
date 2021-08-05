package com.senla.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "name_client")
    @NotBlank
    private String nameClient;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "cost")
    @NotBlank
    private int cost;

    @Column(name = "date_complete")
    private LocalDate dateComplete;

    @Column(name = "status_order")
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    public Order() {
    }

    public Order(Long id, String nameClient, Book book, StatusOrder statusOrder) {
        this.nameClient = nameClient;
        this.book = book;
        this.statusOrder = statusOrder;
        this.id = id;
        this.cost = book.getPrice();
    }

    public Order(Long id, String nameClient, Book book, LocalDate dateComplete, StatusOrder statusOrder) {
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

    public Long getId() {
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
