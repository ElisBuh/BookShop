package com.senla.ui.actions.storage;

import com.senla.exceptions.DaoException;
import com.senla.service.BookService;
import com.senla.service.StorageService;
import com.senla.util.annotation.InjectByType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddBookToStorage implements IAction {
    private static final Logger log = LoggerFactory.getLogger(AddBookToStorage.class);

    @InjectByType
    private StorageService storageService;
    @InjectByType
    private BookService bookService;

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите Id книги которые хотите добавить на склад из каталога:");
            int id = ConsoleHelper.readInt();
            ConsoleHelper.writeMessage("Ведите Дату добавления книги, пример: \"22.05.2021\":");
            LocalDate date = LocalDate.parse(ConsoleHelper.readString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            boolean isMessage = storageService.add(bookService.get(id), date);

            if (isMessage) {
                ConsoleHelper.writeMessage("Книга добавлена на склад.");
            } else {
                ConsoleHelper.writeMessage("Книга уже добавлена на склад.");
            }
        } catch (DaoException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
