package com.senla.ui.actions.order;

import com.senla.api.service.IOrderService;
import com.senla.exceptions.DaoException;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class TimeForPeriodForTime implements IAction {
    @Autowired
    private IOrderService orderService;

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите первую дату, пример: \"22.05.2021\":");
            LocalDate dateStart = ConsoleHelper.readDate();
            ConsoleHelper.writeMessage("Введите вторую дату, пример: \"22.05.2021\":");
            LocalDate dateEnd = ConsoleHelper.readDate();
            ConsoleHelper.writeMessage(
                    "Выбирите вид вывода:\n" +
                            "1 - Список выполненых заказов.\n" +
                            "2 - Количество заработаных денег.\n" +
                            "3 - Количество выполненых заказов.\n" +
                            "Сделайте выбор:");
            int point = ConsoleHelper.readInt();
            if (point == 1) {
                orderService.listOrderCompleteForPeriodForTime(dateStart, dateEnd).forEach(System.out::println);
            } else if (point == 2) {
                ConsoleHelper.writeMessage(orderService.amountOfMoneyForPeriodForTime(dateStart, dateEnd) + "");
            } else if (point == 3) {
                ConsoleHelper.writeMessage(orderService.countCompleteOrders(dateStart, dateEnd) + "");
            }
        } catch (DaoException e) {
            System.out.println("Критическая ошибка в БД, Обратитеть в тех поддержку. Ошибка: " + e.getMessage());
        }
    }
}
