package com.senla.ui.actions.request;

import com.senla.api.service.IRequestService;
import com.senla.exceptions.DaoException;
import com.senla.service.TypeSortRequest;
import com.senla.ui.actions.IAction;
import com.senla.util.annotation.InjectByType;

public class ListSortRequests implements IAction {
    @InjectByType
    private IRequestService requestService;
    @Override
    public void execute() {
        try {
        requestService.sortRequest(TypeSortRequest.COUNT_REQUEST).forEach(System.out::println);
        } catch (DaoException e) {
            System.out.println("Критическая ошибка в БД, Обратитеть в тех поддержку. Ошибка: " + e.getMessage());
        }
    }
}
