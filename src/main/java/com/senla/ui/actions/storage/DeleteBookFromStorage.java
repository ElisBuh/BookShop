package com.senla.ui.actions.storage;

import com.senla.api.service.IBookService;
import com.senla.api.service.IStorageService;
import com.senla.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteBookFromStorage implements IAction {
    private static final Logger log = LoggerFactory.getLogger(DeleteBookFromStorage.class);
    @Autowired
    private IStorageService storageService;
    @Autowired
    private IBookService bookService;

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
