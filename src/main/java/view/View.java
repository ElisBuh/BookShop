package view;

import api.view.*;

public class View implements IView {
    private GUIFactory guiFactory;
    private static View viewInstance;

    private View() {
    }

    public static View getViewInstance() {
        if (viewInstance == null) {
            viewInstance = new View();
        }
        return viewInstance;
    }

    @Override
    public void menu() {
        ConsoleHelper.writeMessage("Книжный Магазин.\n" +
                "Меню:\n" +
                "1 - Меню каталога книг.\n" +
                "2 - Меню заказов.\n" +
                "3 - Меню Склада.\n" +
                "4 - Меню запросов на книги.\n" +
                "0 - Выход.\n" +
                "Сделайте выбор:");
        int point = ConsoleHelper.readInt();
        if (point == 1) {
            guiFactory = new BookView();
        } else if (point == 2) {
            guiFactory = new OrderView();
        } else if (point == 3) {
            guiFactory = new StorageView();
        } else if (point == 4) {
            guiFactory = new RequestView();
        } else if (point == 0) {
            System.exit(0);
        } else {
            ConsoleHelper.writeMessage("Не вверный ввод");
            menu();
        }
        guiFactory.menu();
    }
}
