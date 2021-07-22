package com.senla.ui.actions.book;

import com.senla.exceptions.DaoException;
import com.senla.service.BookService;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import com.senla.util.annotation.InjectByType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddBook implements IAction {
    @InjectByType
    private BookService bookService;

    @Override
    public void execute() {
        try {
        ConsoleHelper.writeMessage("Ведите Название книги:");
        String nameBook = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Ведите Название автора:");
        String nameAuthor = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Ведите Стоимость книги:");
        int price = ConsoleHelper.readInt();
        ConsoleHelper.writeMessage("Ведите Дату, пример: \"22.05.2021\":");
        LocalDate date = ConsoleHelper.readDate();
        bookService.save(nameBook, nameAuthor, price, date);
        }
        catch (DaoException e){
            System.out.println("Ошибка В веденных данных, Повторите попытку или обратитесь в тех. Подержку");
        }
    }
}
