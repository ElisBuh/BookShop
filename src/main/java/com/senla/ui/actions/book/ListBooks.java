package com.senla.ui.actions.book;

import com.senla.ui.actions.AbstractAction;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;

public class ListBooks extends AbstractAction implements IAction {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Список книг:");
        bookService.getAll().forEach(System.out::println);
    }
}
