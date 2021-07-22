package com.senla.model;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Book book;
    private Integer countRequest;


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
