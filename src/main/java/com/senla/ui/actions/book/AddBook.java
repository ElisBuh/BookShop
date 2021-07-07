package com.senla.ui.actions.book;

import com.senla.service.BookService;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import com.senla.util.annotation.InjectByType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddBook implements IAction {
    @InjectByType
    private BookService bookService;

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
        bookService.save(nameBook, nameAuthor, price, date);
    }
}
