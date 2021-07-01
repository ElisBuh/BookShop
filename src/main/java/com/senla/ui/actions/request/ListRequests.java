package com.senla.ui.actions.request;

import com.senla.ui.actions.AbstractAction;
import com.senla.ui.actions.IAction;

public class ListRequests extends AbstractAction implements IAction {
    @Override
    public void execute() {
        requestService.getAll().forEach(System.out::println);
    }
}
