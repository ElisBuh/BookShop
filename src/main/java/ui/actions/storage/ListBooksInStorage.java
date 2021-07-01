package ui.actions.storage;

import ui.actions.AbstractAction;
import ui.actions.IAction;

public class ListBooksInStorage extends AbstractAction implements IAction {
    @Override
    public void execute() {
        storageService.getAll().forEach(System.out::println);
    }
}
