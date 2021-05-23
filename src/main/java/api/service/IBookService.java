package api.service;

import model.book.Book;
import service.bookSevvice.TypeSortBook;


import java.time.LocalDate;


public interface IBookService {

    void addBook(String nameBook, String nameAuthor,int price, LocalDate date);
    Book getBook(int id);
    void sortBooks(TypeSortBook typeSortBook);

    void print();
}
