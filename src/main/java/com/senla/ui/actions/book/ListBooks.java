package com.senla.ui.actions.book;

import com.senla.exceptions.DaoException;
import com.senla.service.BookService;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import com.senla.util.annotation.InjectByType;

import java.sql.SQLException;

public class ListBooks implements IAction {
    @InjectByType
    private BookService bookService;

    @Override
    public void execute() {
        try {
        ConsoleHelper.writeMessage("Список книг:");
        bookService.getAll().forEach(System.out::println);

        }catch (DaoException | NullPointerException e){
            System.out.println("Критическая ошибка в БД, Обратитеть в тех поддержку. Ошибка: " + e.getMessage());
        }
    }
}
