package com.senla.ui.actions.storage;

import com.senla.api.service.IStorageService;
import com.senla.exceptions.DaoException;
import com.senla.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookNotSellMoreSixMonth implements IAction {
    @Autowired
    private IStorageService storageService;

    @Override
    public void execute() {
        try {
            storageService.BookNotSellMoreNmonth().forEach(System.out::println);
        } catch (DaoException e) {
            System.out.println("Критическая ошибка в БД, Обратитеть в тех поддержку. Ошибка: " + e.getMessage());
        }
    }
}
