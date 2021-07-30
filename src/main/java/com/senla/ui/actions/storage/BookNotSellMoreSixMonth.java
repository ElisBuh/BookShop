package com.senla.ui.actions.storage;

import com.senla.exceptions.DaoException;
import com.senla.service.StorageService;
import com.senla.ui.actions.IAction;
import com.senla.util.annotation.InjectByType;

public class BookNotSellMoreSixMonth implements IAction {
    @InjectByType
    private StorageService storageService;

    @Override
    public void execute() {
        try {
            storageService.BookNotSellMoreNmonth().forEach(System.out::println);
        } catch (DaoException e) {
            System.out.println("Критическая ошибка в БД, Обратитеть в тех поддержку. Ошибка: " + e.getMessage());
        }
    }
}
