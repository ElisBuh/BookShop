package com.senla.model;


public class Storage {
    private Long id;
    private Book book;

    public Storage(Long id, Book book) {
        this.id = id;
        this.book = book;
    }

    public Storage(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", " + book +
                '}';
    }
}
