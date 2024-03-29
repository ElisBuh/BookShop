package com.senla.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "books")
public class Book extends AEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "book_id", sequenceName = "book_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id")
    private Integer id;

    @Column(name = "name_book")
    private String nameBook;

    @Column(name = "name_author")
    private String nameAuthor;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "price")
    private int price;

    @Column(name = "status_book")
    @Enumerated(EnumType.STRING)
    private StatusBook statusBook;

    @Column(name = "data_receipt")
    private LocalDate dateReceipt;

    public Book() {
    }

    public Book(Integer id, String nameBook, String nameAuthor, LocalDate date, int price, StatusBook statusBook) {
        this.nameBook = nameBook;
        this.nameAuthor = nameAuthor;
        this.id = id;
        this.statusBook = statusBook;
        this.date = date;
        this.price = price;
    }

    public Book(String nameBook, String nameAuthor, LocalDate date, int price, StatusBook statusBook) {
        this.nameBook = nameBook;
        this.nameAuthor = nameAuthor;
        this.date = date;
        this.price = price;
        this.statusBook = statusBook;
    }

    public Book(Integer id, String nameBook, String nameAuthor, LocalDate date, int price, StatusBook statusBook, LocalDate dateReceipt) {
        this.id = id;
        this.nameBook = nameBook;
        this.nameAuthor = nameAuthor;
        this.date = date;
        this.price = price;
        this.statusBook = statusBook;
        this.dateReceipt = dateReceipt;
    }
    public Book(Book book) {
        this.id = book.getId();
        this.nameBook = book.getNameBook();
        this.nameAuthor = book.getNameAuthor();
        this.date = book.getDate();
        this.price = book.getPrice();
        this.statusBook = book.getStatusBook();
        this.dateReceipt = book.getDateReceipt();
    }

    public String getNameBook() {
        return nameBook;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getDateReceipt() {
        return dateReceipt;
    }

    public void setDateReceipt(LocalDate dateReceipt) {
        this.dateReceipt = dateReceipt;
    }

    public Integer getId() {
        return id;
    }

    public void setStatusBook(StatusBook statusBook) {
        this.statusBook = statusBook;
    }

    public StatusBook getStatusBook() {
        return statusBook;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return price == book.price && Objects.equals(id, book.id) && Objects.equals(nameBook, book.nameBook) && Objects.equals(nameAuthor, book.nameAuthor) && Objects.equals(date, book.date) && statusBook == book.statusBook && Objects.equals(dateReceipt, book.dateReceipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameBook, nameAuthor, date, price, statusBook, dateReceipt);
    }

    @Override
    public String toString() {
        if ((id == null)) {
            return "Книга: " +
                    "Название книги: '" + nameBook + '\'' +
                    ", Имя Автора: '" + nameAuthor + '\'' +
                    ", Дата издания: " + date +
                    ", Стоимость: " + price +
                    ", Статус книги: " + statusBook + ".";
        } else {
            return "Книга: " +
                    "id=" + id +
                    ", Название книги: '" + nameBook + '\'' +
                    ", Имя Автора: '" + nameAuthor + '\'' +
                    ", Дата издания: " + date +
                    ", Стоимость: " + price +
                    ", Статус книги: " + statusBook + ".";
        }
    }
}
