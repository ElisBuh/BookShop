package ui.actions.storage;

import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddBookToStorage extends AbstractAction implements IAction {
    @Override
    public void execute() {
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
    }
}
