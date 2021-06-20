package ui.actions.order;

import exceptions.ServiceException;
import model.StatusOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;
import ui.menu.MenuController;

public class ChangeOrderStatus extends AbstractAction implements IAction {
    private static final Logger log = LoggerFactory.getLogger(ChangeOrderStatus.class);

    @Override
    public void execute() {
        try {

            ConsoleHelper.writeMessage("Введите id заказа для изменения статуса");
            int id = ConsoleHelper.readInt();
            ConsoleHelper.writeMessage(
                    "Выбирите статус заказа:\n" +
                            "1 - Новый.\n" +
                            "2 - Выполнен.\n" +
                            "3 - Отменён.\n" +
                            "Сделайте выбор:");
            int point = ConsoleHelper.readInt();
            StatusOrder statusOrder = null;
            if (point == 1) {
                statusOrder = StatusOrder.NEW;
            } else if (point == 2) {
                statusOrder = StatusOrder.COMPLETED;
            } else if (point == 3) {
                statusOrder = StatusOrder.CANCEL;
            } else {
                ConsoleHelper.writeMessage("Не вверный ввод");
                MenuController.getMenuControllerInstance().run();
            }
            orderService.changeStatusOrder(id, statusOrder);
        } catch (ServiceException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
