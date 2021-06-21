package ui.actions.storage;

import exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddBookToStorage extends AbstractAction implements IAction {
    private static final Logger log = LoggerFactory.getLogger(AddBookToStorage.class);

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите Id книги которые хотите добавить на склад из каталога:");
            int id = ConsoleHelper.readInt();
            ConsoleHelper.writeMessage("Ведите Дату добавления книги, пример: \"22.05.2021\":");
            LocalDate date = LocalDate.parse(ConsoleHelper.readString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            boolean isMessage = storageService.addBook(bookService.getBook(id), date);

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
