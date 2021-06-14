package ui.actions.book;

import service.TypeSortBook;
import ui.actions.AbstractAction;
import ui.actions.IAction;

public class ListSortDateBooks extends AbstractAction implements IAction {
    @Override
    public void execute() {
        bookService.listSortBooks(TypeSortBook.DATE).forEach(System.out::println);
    }
}
