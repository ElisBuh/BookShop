package com.senla.ui.actions.order;

import com.senla.exceptions.DaoException;
import com.senla.ui.actions.AbstractAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;

public class CancelOrder extends AbstractAction implements IAction {
    private static final Logger log = LoggerFactory.getLogger(CancelOrder.class);

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите id заказа для отмены");
            orderService.cancel(ConsoleHelper.readInt());
        } catch (DaoException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
