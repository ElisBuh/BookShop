package ui.actions.book;

import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddBook extends AbstractAction implements IAction {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Ведите Название книги:");
        String nameBook = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Ведите Название автора:");
        String nameAuthor = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Ведите Стоимость книги:");
        int price = ConsoleHelper.readInt();
        ConsoleHelper.writeMessage("Ведите Дату, пример: \"22.05.2021\":");
        LocalDate date = LocalDate.parse(ConsoleHelper.readString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        bookService.addBook(nameBook, nameAuthor, price, date);
    }
}
