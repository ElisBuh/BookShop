package com.senla.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "requests")
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "count_request")
    private Integer countRequest;

    public Request() {
    }

    public Request(Long id, Book book) {
        this.book = book;
        this.countRequest = 1;
        this.id = id;
    }

    public Request(Long id, Book book, Integer countRequest) {
        this.id = id;
        this.book = book;
        this.countRequest = countRequest;
    }

    public Request(Book book) {
        this.book = book;
        this.countRequest = 1;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getCountRequest() {
        return countRequest;
    }

    public void setCountRequest(Integer countRequest) {
        this.countRequest = countRequest;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Запрос " +
                "id:" + id +
                ", Книга " + book +
                ", Количество запросов: " + countRequest;
    }
}
