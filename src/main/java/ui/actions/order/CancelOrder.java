package ui.actions.order;

import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class CancelOrder extends AbstractAction implements IAction {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Введите id заказа для отмены");
        orderService.cancelOrder(ConsoleHelper.readInt());
    }
}
