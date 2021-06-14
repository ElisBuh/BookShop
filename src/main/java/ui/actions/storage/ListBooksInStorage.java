package ui.actions.storage;

import ui.actions.AbstractAction;
import ui.actions.IAction;

public class ListBooksInStorage extends AbstractAction implements IAction {
    @Override
    public void execute() {
        storageService.getStorageBooks().forEach(System.out::println);
    }
}
