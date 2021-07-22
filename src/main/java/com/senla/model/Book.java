package com.senla.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nameBook;
    private String nameAuthor;
    private LocalDate date;
    private int price;
    private StatusBook statusBook;
    private LocalDate dateReceipt;


    public Book(int id, String nameBook, String nameAuthor, LocalDate date, int price, StatusBook statusBook) {
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

    public int getId() {
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
        return id == book.id && price == book.price && Objects.equals(nameBook, book.nameBook) && Objects.equals(nameAuthor, book.nameAuthor) && Objects.equals(date, book.date) && statusBook == book.statusBook && Objects.equals(dateReceipt, book.dateReceipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameBook, nameAuthor, date, price, statusBook, dateReceipt);
    }

    @Override
    public String toString() {
        if (id != 0){
        return "Книга: " +
                "id=" + id +
                ", Название книги: '" + nameBook + '\'' +
                ", Имя Автора: '" + nameAuthor + '\'' +
                ", Дата издания: " + date +
                ", Стоимость: " + price +
                ", Статус книги: " + statusBook + ".";
    }else {
            return "Книга: " +
                    "Название книги: '" + nameBook + '\'' +
                    ", Имя Автора: '" + nameAuthor + '\'' +
                    ", Дата издания: " + date +
                    ", Стоимость: " + price +
                    ", Статус книги: " + statusBook + ".";
        }
    }
}
