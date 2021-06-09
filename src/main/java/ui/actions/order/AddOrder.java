package ui.actions.order;

import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class AddOrder extends AbstractAction implements IAction {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Введите имя заказчика");
        String name = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите id книги");
        int id = ConsoleHelper.readInt();
        orderService.creatOrder(name, bookService.getBook(id));
        ConsoleHelper.writeMessage("Заказ добавлен");
    }
}
