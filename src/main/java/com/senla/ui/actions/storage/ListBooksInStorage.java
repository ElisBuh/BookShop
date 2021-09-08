package com.senla.ui.actions.storage;

import com.senla.api.service.IStorageService;
import com.senla.exceptions.DaoException;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListBooksInStorage implements IAction {
    @Autowired
    private IStorageService storageService;
    @Override
    public void execute() {
        try {
        storageService.getAll(ConsoleHelper.readInt(), ConsoleHelper.readInt()).forEach(System.out::println);
        } catch (DaoException e) {
            System.out.println("Критическая ошибка в БД, Обратитеть в тех поддержку. Ошибка: " + e.getMessage());
        }
    }
}
