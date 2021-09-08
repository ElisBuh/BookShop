package com.senla.ui.actions.order;

import com.senla.api.service.IBookService;
import com.senla.api.service.IOrderService;
import com.senla.exceptions.DaoException;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddOrder implements IAction {
    private static final Logger log = LoggerFactory.getLogger(AddOrder.class);
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IBookService bookService;

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
