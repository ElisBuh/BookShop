package com.senla.model;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    public Storage() {
    }

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
