package ui.actions.order;

import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;
import ui.menu.Builder;
import ui.menu.Menu;
import ui.menu.MenuController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeForPeriodForTime extends AbstractAction implements IAction {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Введите первую дату, пример: \"22.05.2021\":");
        LocalDate dateStart = LocalDate.parse(ConsoleHelper.readString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        ConsoleHelper.writeMessage("Введите вторую дату, пример: \"22.05.2021\":");
        LocalDate dateEnd = LocalDate.parse(ConsoleHelper.readString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
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
            ConsoleHelper.writeMessage(orderService.AmountOfMoneyForPeriodForTime(dateStart, dateEnd) + "");
        } else if (point == 3) {
            ConsoleHelper.writeMessage(orderService.countCompleteOrders(dateStart, dateEnd) + "");
        } else {
            MenuController.getMenuControllerInstance().run();
        }
    }
}
