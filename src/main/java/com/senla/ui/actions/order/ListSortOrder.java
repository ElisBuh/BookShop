package com.senla.ui.actions.order;

import com.senla.service.TypeSortOrder;
import com.senla.ui.actions.AbstractAction;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;


public class ListSortOrder extends AbstractAction implements IAction {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(
                "Способ сортировки:\n" +
                        "1 - Дате выполнения.\n" +
                        "2 - Стоимости.\n" +
                        "3 - Статусе.\n" +
                        "Сделайте выбор:");
        int point = ConsoleHelper.readInt();
        TypeSortOrder typeSortOrder = null;
        if (point == 1) {
            typeSortOrder = TypeSortOrder.DATA_COMPLETE;
        } else if (point == 2) {
            typeSortOrder = TypeSortOrder.COST;
        } else if (point == 3) {
            typeSortOrder = TypeSortOrder.STATUS;
        } else {
            ConsoleHelper.writeMessage("Не вверный ввод");

        }
        orderService.listSortOrder(typeSortOrder).forEach(System.out::println);
    }
}
