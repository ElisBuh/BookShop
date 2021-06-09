package ui.actions.order;

import model.StatusOrder;
import ui.Starter;
import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;
import ui.menu.Builder;

public class ChangeOrderStatus extends AbstractAction implements IAction {
    @Override
    public void execute() {
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
            Builder builder = new Builder(Starter.orderMenu());
            builder.buildMenu();
        }
        orderService.changeStatusOrder(id, statusOrder);
    }
}
