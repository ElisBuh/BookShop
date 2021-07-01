package com.senla.ui.actions.storage;

import com.senla.ui.actions.AbstractAction;
import com.senla.ui.actions.IAction;

public class BookNotSellMoreSixMonth extends AbstractAction implements IAction {
    @Override
    public void execute() {
        storageService.BookNotSellMoreNmonth().forEach(System.out::println);
    }
}
