package ui.actions.order;

import exceptions.DaoException;
import exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class CancelOrder extends AbstractAction implements IAction {
    private static final Logger log = LoggerFactory.getLogger(CancelOrder.class);

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите id заказа для отмены");
            orderService.cancelOrder(ConsoleHelper.readInt());
        } catch (DaoException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
