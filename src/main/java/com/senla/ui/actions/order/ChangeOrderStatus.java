package com.senla.ui.actions.order;

import com.senla.api.service.IOrderService;
import com.senla.exceptions.DaoException;
import com.senla.model.StatusOrder;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeOrderStatus implements IAction {
    private static final Logger log = LoggerFactory.getLogger(ChangeOrderStatus.class);
    @Autowired
    private IOrderService orderService;


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
            }
            orderService.changeStatusOrder(id, statusOrder);
        } catch (DaoException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
