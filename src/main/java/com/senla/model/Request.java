package com.senla.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "requests")
public class Request extends AEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "request_id", sequenceName = "request_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "count_request")
    private Integer countRequest;

    public Request() {
    }

    public Request(Integer id, Book book) {
        this.book = book;
        this.countRequest = 1;
        this.id = id;
    }

    public Request(Integer id, Book book, Integer countRequest) {
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

    public Integer getId() {
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
