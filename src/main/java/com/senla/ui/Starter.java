package com.senla.ui;

import com.senla.service.BookService;
import com.senla.ui.menu.MenuController;

import java.time.LocalDate;

public class Starter {

    public static void main(String[] args) {
//        addDataBook();
        MenuController.getMenuControllerInstance().run();

    }

//    private static void addDataBook() {
//        BookService bookService = BookService.getBookServiceInstance();
//
//        bookService.save("War and Peace", "Leo Tolstoy", 25, LocalDate.of(2001, 5, 25));
//        bookService.save("War and Peace3", "Leo Tolstoy1", 38, LocalDate.of(2011, 1, 22));
//        bookService.save("War and Peace1", "Leo Tolstoy3", 52, LocalDate.of(2041, 2, 15));
//        bookService.save("War and Peace5", "Leo Tolstoy2", 55, LocalDate.of(2061, 4, 23));
//        bookService.save("War and Peace4", "Leo Tolstoy5", 21, LocalDate.of(2012, 10, 1));
//        bookService.save("War and Peace6", "Leo Tolstoy4", 26, LocalDate.of(2021, 6, 12));
//    }
}
