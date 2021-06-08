package view;

import api.service.IBookService;
import api.service.IOrderService;
import api.view.GUIFactory;
import model.StatusOrder;
import service.BookService;
import service.OrderService;
import service.TypeSortOrder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderView implements GUIFactory {
    private final IOrderService iOrderService = OrderService.getOrderServiceInstance();
    private final IBookService iBookService = BookService.getBookServiceInstance();

    @Override
    public void menu() {
        ConsoleHelper.writeMessage(
                "Меню заказов:\n" +
                        "1 - Создать заказ.\n" +
                        "2 - Отменить заказ.\n" +
                        "3 - Изменить статус заказа.\n" +
                        "4 - Сортированный список заказов.\n" +
                        "5 - Данные за промежуток времени.\n" +
                        "6 - Удалить заказ.\n" +
                        "7 - Назад.\n" +
                        "0 - Выход.\n" +
                        "Сделайте выбор:");
        int point = ConsoleHelper.readInt();
        if (point == 1) {
            creatOrder();
        } else if (point == 2) {
            creatOrder();
        } else if (point == 3) {
            changeOrderStatus();
        } else if (point == 4) {
            sortOrder();
        } else if (point == 5) {
            timeForPeriodForTime();
        } else if (point == 6) {
            deleteOrder();
        } else if (point == 7) {
            View.getViewInstance().menu();
        } else if (point == 0) {
            System.exit(0);
        } else {
            ConsoleHelper.writeMessage("Не вверный ввод. Повторите попытку.");
            menu();
        }
    }

    private void creatOrder() {
        ConsoleHelper.writeMessage("Введите имя заказчика");
        String name = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите id книги");
        int id = ConsoleHelper.readInt();
        iOrderService.creatOrder(name, iBookService.getBook(id));
        ConsoleHelper.writeMessage("Заказ добавлен");
        menu();
    }

    private void cancelOrder() {
        ConsoleHelper.writeMessage("Введите id заказа для отмены");
        iOrderService.cancelOrder(ConsoleHelper.readInt());
        menu();
    }

    private void changeOrderStatus() {
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
            menu();
        }
        iOrderService.changeStatusOrder(id, statusOrder);
        menu();
    }

    private void sortOrder() {
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
            menu();
        }
        iOrderService.listSortOrder(typeSortOrder).forEach(System.out::println);
        menu();
    }


    private void timeForPeriodForTime() {
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
            iOrderService.listOrderCompleteForPeriodForTime(dateStart, dateEnd).forEach(System.out::println);
        } else if (point == 2) {
            ConsoleHelper.writeMessage(iOrderService.AmountOfMoneyForPeriodForTime(dateStart, dateEnd) + "");
        } else if (point == 3) {
            ConsoleHelper.writeMessage(iOrderService.countCompleteOrders(dateStart, dateEnd) + "");
        } else {
            menu();
        }
        menu();
    }

    private void deleteOrder() {
        ConsoleHelper.writeMessage("Введите id заказа для удаления");
        int id = ConsoleHelper.readInt();
        iOrderService.deleteOrder(id);
        ConsoleHelper.writeMessage("Заказ удален.");
        menu();
    }
}
