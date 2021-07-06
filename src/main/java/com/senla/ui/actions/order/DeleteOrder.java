package com.senla.ui.actions.order;

import com.senla.api.service.IOrderService;
import com.senla.exceptions.DaoException;
import com.senla.util.annotation.InjectByType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;

public class DeleteOrder implements IAction {
    private static final Logger log = LoggerFactory.getLogger(DeleteOrder.class);
    @InjectByType
    private IOrderService orderService;

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите id заказа для удаления");
            int id = ConsoleHelper.readInt();
            orderService.delete(id);
            ConsoleHelper.writeMessage("Заказ удален.");
        } catch (DaoException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
