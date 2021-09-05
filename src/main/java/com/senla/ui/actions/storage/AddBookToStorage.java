package com.senla.ui.actions.storage;

import com.senla.api.service.IBookService;
import com.senla.api.service.IStorageService;
import com.senla.exceptions.DaoException;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AddBookToStorage implements IAction {
    private static final Logger log = LoggerFactory.getLogger(AddBookToStorage.class);

    @Autowired
    private IStorageService storageService;
    @Autowired
    private IBookService bookService;

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
