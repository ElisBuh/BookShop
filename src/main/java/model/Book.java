package model;


import java.time.LocalDate;


public class Book {
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
    public String toString() {
        return "Книга: " +
                "id=" + id +
                ", Название книги: '" + nameBook + '\'' +
                ", Имя Автора: '" + nameAuthor + '\'' +
                ", Дата издания: " + date +
                ", Стоимость: " + price +
                ", Статус книги: " + statusBook + ".";
    }
}
