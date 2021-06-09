package ui.actions.book;

import service.TypeSortBook;
import ui.actions.AbstractAction;
import ui.actions.IAction;

public class ListSortNameBooks extends AbstractAction implements IAction {
    @Override
    public void execute() {
        bookService.listSortBooks(TypeSortBook.NAME_BOOK).forEach(System.out::println);
    }
}
