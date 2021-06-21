package ui.actions.storage;

import exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class DeleteBookFromStorage extends AbstractAction implements IAction {
    private static final Logger log = LoggerFactory.getLogger(DeleteBookFromStorage.class);

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите Id книги которые хотите удалить со склад:");
            int id = ConsoleHelper.readInt();
            boolean isMessage = storageService.deleteBook(bookService.getBook(id));
            if (isMessage) {
                ConsoleHelper.writeMessage("Книга удалена со склада.");
            }
        } catch (DaoException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
