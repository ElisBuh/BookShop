package ui.actions.order;

import exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class DeleteOrder extends AbstractAction implements IAction {
    private static final Logger log = LoggerFactory.getLogger(DeleteOrder.class);

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите id заказа для удаления");
            int id = ConsoleHelper.readInt();
            orderService.deleteOrder(id);
            ConsoleHelper.writeMessage("Заказ удален.");
        } catch (ServiceException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
