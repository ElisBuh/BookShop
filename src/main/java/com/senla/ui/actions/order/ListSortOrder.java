package com.senla.ui.actions.order;

import com.senla.api.service.IOrderService;
import com.senla.service.TypeSortOrder;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import com.senla.util.annotation.InjectByType;


public class ListSortOrder implements IAction {
    @InjectByType
    private IOrderService orderService;

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
