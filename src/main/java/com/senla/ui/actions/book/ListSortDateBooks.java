package com.senla.ui.actions.book;

import com.senla.service.TypeSortBook;
import com.senla.ui.actions.AbstractAction;
import com.senla.ui.actions.IAction;

public class ListSortDateBooks extends AbstractAction implements IAction {
    @Override
    public void execute() {
        bookService.listSortBooks(TypeSortBook.DATE).forEach(System.out::println);
    }
}
