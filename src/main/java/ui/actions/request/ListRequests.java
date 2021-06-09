package ui.actions.request;

import ui.actions.AbstractAction;
import ui.actions.IAction;

public class ListRequests extends AbstractAction implements IAction {
    @Override
    public void execute() {
        requestService.listRequests().forEach(System.out::println);
    }
}
