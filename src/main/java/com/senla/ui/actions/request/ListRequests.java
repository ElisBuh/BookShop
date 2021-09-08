package com.senla.ui.actions.request;

import com.senla.api.service.IRequestService;
import com.senla.exceptions.DaoException;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListRequests implements IAction {
    @Autowired
    private IRequestService requestService;

    @Override
    public void execute() {
        try {
        requestService.getAll(ConsoleHelper.readInt(), ConsoleHelper.readInt()).forEach(System.out::println);
        } catch (DaoException e) {
            System.out.println("Критическая ошибка в БД, Обратитеть в тех поддержку. Ошибка: " + e.getMessage());
        }
    }
}
