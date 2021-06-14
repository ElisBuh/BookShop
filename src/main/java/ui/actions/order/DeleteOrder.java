package ui.actions.order;

import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class DeleteOrder extends AbstractAction implements IAction {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Введите id заказа для удаления");
        int id = ConsoleHelper.readInt();
        orderService.deleteOrder(id);
        ConsoleHelper.writeMessage("Заказ удален.");
    }
}
