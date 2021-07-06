package com.senla.ui.actions.request;

import com.senla.api.service.IRequestService;
import com.senla.ui.actions.IAction;
import com.senla.util.annotation.InjectByType;

public class ListRequests implements IAction {
    @InjectByType
    private IRequestService requestService;

    @Override
    public void execute() {
        requestService.getAll().forEach(System.out::println);
    }
}
