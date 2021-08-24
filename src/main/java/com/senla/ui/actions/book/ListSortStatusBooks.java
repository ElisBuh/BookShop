package com.senla.ui.actions.book;

import com.senla.api.service.IBookService;
import com.senla.exceptions.DaoException;
import com.senla.service.TypeSortBook;
import com.senla.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListSortStatusBooks implements IAction {
    @Autowired
    private IBookService bookService;

    @Override
    public void execute() {
        try {
            bookService.listSortBooks(TypeSortBook.IN_STOCK).forEach(System.out::println);
        } catch (DaoException e) {
            System.out.println("Критическая ошибка в БД, Обратитеть в тех поддержку. Ошибка: " + e.getMessage());
        }
    }
}
