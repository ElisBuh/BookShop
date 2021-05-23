package api.service;

import model.book.Book;


import java.time.LocalDate;


public interface IBookService {

    void addBook(String nameBook, String nameAuthor,int price, LocalDate date);
    Book getBook(int id);

    void print();
}
