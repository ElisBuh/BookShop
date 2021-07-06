package com.senla.ui.actions.storage;

import com.senla.exceptions.DaoException;
import com.senla.service.BookService;
import com.senla.service.StorageService;
import com.senla.util.annotation.InjectByType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;

public class DeleteBookFromStorage implements IAction {
    private static final Logger log = LoggerFactory.getLogger(DeleteBookFromStorage.class);
    @InjectByType
    private StorageService storageService;
    @InjectByType
    private BookService bookService;

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите Id книги которые хотите удалить со склад:");
            int id = ConsoleHelper.readInt();
            boolean isMessage = storageService.delete(bookService.get(id));
            if (isMessage) {
                ConsoleHelper.writeMessage("Книга удалена со склада.");
            }
        } catch (DaoException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
