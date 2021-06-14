package ui.actions.storage;

import ui.actions.AbstractAction;
import ui.actions.IAction;

public class BookNotSellMoreSixMonth extends AbstractAction implements IAction {
    @Override
    public void execute() {
        storageService.printBookNotSellMoreSixMonth().forEach(System.out::println);
    }
}
