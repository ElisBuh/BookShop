package com.senla.ui.actions.storage;

import com.senla.ui.actions.AbstractAction;
import com.senla.ui.actions.IAction;

public class ListBooksInStorage extends AbstractAction implements IAction {
    @Override
    public void execute() {
        storageService.getAll().forEach(System.out::println);
    }
}
