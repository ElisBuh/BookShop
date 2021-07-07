package com.senla.ui.actions.book;

import com.senla.service.BookService;
import com.senla.service.TypeSortBook;
import com.senla.ui.actions.IAction;
import com.senla.util.annotation.InjectByType;

public class ListSortPriceBooks implements IAction {
    @InjectByType
    private BookService bookService;

    @Override
    public void execute() {
        bookService.listSortBooks(TypeSortBook.PRICE).forEach(System.out::println);
    }
}
