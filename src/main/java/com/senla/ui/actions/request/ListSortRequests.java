package com.senla.ui.actions.request;

import com.senla.service.TypeSortRequest;
import com.senla.ui.actions.AbstractAction;
import com.senla.ui.actions.IAction;

public class ListSortRequests extends AbstractAction implements IAction {
    @Override
    public void execute() {
        requestService.sortRequest(TypeSortRequest.COUNT_REQUEST).forEach(System.out::println);
    }
}
