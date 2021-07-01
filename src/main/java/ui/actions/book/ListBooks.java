package ui.actions.book;

import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class ListBooks extends AbstractAction implements IAction {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Список книг:");
        bookService.getAll().forEach(System.out::println);
    }
}
