package com.senla.ui.actions.order;

import com.senla.api.service.IOrderService;
import com.senla.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CancelOrder implements IAction {
    private static final Logger log = LoggerFactory.getLogger(CancelOrder.class);
    @Autowired
    private IOrderService orderService;

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
