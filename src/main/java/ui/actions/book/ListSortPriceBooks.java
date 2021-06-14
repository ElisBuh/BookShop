package ui.actions.book;

import service.TypeSortBook;
import ui.actions.AbstractAction;
import ui.actions.IAction;

public class ListSortPriceBooks extends AbstractAction implements IAction {
    @Override
    public void execute() {
        bookService.listSortBooks(TypeSortBook.PRICE).forEach(System.out::println);
    }
}
