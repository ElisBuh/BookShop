package ui.actions.storage;

import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class DeleteBookFromStorage extends AbstractAction implements IAction {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Введите Id книги которые хотите удалить со склад:");
        int id = ConsoleHelper.readInt();
        boolean isMessage = storageService.deleteBook(bookService.getBook(id));
        if (isMessage) {
            ConsoleHelper.writeMessage("Книга удалена со склада.");
        }
    }
}
