package com.senla.model;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries(
        {
                @NamedQuery(
                        name = "findStorageOnBook",
                        query = "from Storage as s where s.book.id = :id"
                )
        }
)

@Entity
@Table(name = "storage")
public class Storage extends AEntity {
    @Id
    @SequenceGenerator(name = "storage_id", sequenceName = "storage_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storage_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    public Storage() {
    }

    public Storage(Integer id, Book book) {
        this.id = id;
        this.book = book;
    }

    public Storage(Book book) {
        this.book = book;
    }

    public Integer getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(id, storage.id) && Objects.equals(book, storage.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book);
    }
}
