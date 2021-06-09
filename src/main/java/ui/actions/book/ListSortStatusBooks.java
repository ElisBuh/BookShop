package ui.actions.book;

import service.TypeSortBook;
import ui.actions.AbstractAction;
import ui.actions.IAction;

public class ListSortStatusBooks extends AbstractAction implements IAction {
    @Override
    public void execute() {
        bookService.listSortBooks(TypeSortBook.IN_STOCK).forEach(System.out::println);
    }
}
