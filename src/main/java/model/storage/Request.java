package model.storage;

import model.book.Book;

public class Request {
    private Integer id;
    private Book book;
    private Integer countRequest;


    public Request(Integer id, Book book) {
        this.book = book;
        this.countRequest = 1;
        this.id = id;
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

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", book=" + book +
                ", countRequest=" + countRequest +
                '}';
    }
}
