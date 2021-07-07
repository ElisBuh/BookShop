package com.senla.ui.actions.order;

import com.senla.api.service.IOrderService;
import com.senla.exceptions.DaoException;
import com.senla.service.BookService;
import com.senla.util.annotation.InjectByType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;

public class AddOrder implements IAction {
    private static final Logger log = LoggerFactory.getLogger(AddOrder.class);
    @InjectByType
    private IOrderService orderService;
    @InjectByType
    private BookService bookService;

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите имя заказчика");
            String name = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Введите id книги");
            int id = ConsoleHelper.readInt();
            orderService.creat(name, bookService.get(id));
            ConsoleHelper.writeMessage("Заказ добавлен");
        } catch (DaoException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
