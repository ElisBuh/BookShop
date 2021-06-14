package ui.actions.request;

import service.TypeSortRequest;
import ui.actions.AbstractAction;
import ui.actions.IAction;

public class ListSortRequests extends AbstractAction implements IAction {
    @Override
    public void execute() {
        requestService.sortRequest(TypeSortRequest.COUNT_REQUEST).forEach(System.out::println);
    }
}
