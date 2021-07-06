package com.senla.ui.actions.book;

import com.senla.service.BookService;
import com.senla.service.TypeSortBook;
import com.senla.ui.actions.IAction;
import com.senla.util.annotation.InjectByType;

public class ListSortDateBooks implements IAction {
    @InjectByType
    private BookService bookService;

    @Override
    public void execute() {
        bookService.listSortBooks(TypeSortBook.DATE).forEach(System.out::println);
    }
}
