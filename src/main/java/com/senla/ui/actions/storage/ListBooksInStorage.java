package com.senla.ui.actions.storage;

import com.senla.service.StorageService;
import com.senla.ui.actions.IAction;
import com.senla.util.annotation.InjectByType;

public class ListBooksInStorage implements IAction {
    @InjectByType
    private StorageService storageService;
    @Override
    public void execute() {
        storageService.getAll().forEach(System.out::println);
    }
}
