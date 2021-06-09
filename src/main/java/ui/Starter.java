package ui;

import ui.actions.Exit;
import ui.actions.book.*;
import ui.actions.order.*;
import ui.actions.request.AddRequest;
import ui.actions.request.DeleteRequest;
import ui.actions.request.ListRequests;
import ui.actions.request.ListSortRequests;
import ui.actions.root.*;
import ui.actions.storage.AddBookToStorage;
import ui.actions.storage.BookNotSellMoreSixMonth;
import ui.actions.storage.DeleteBookFromStorage;
import ui.actions.storage.ListBooksInStorage;
import ui.menu.Menu;
import ui.menu.MenuItem;
import ui.menu.Navigator;

public class Starter {


    public static void main(String[] args) {
        startUI();
    }

    public static Menu rootMenu() {
        MenuItem[] rootMenuItems = new MenuItem[5];
        rootMenuItems[0] = new MenuItem("Меню книг.", new MenuBook());
        rootMenuItems[1] = new MenuItem("Меню заказов.", new MenuOrder());
        rootMenuItems[2] = new MenuItem("Меню запросов.", new MenuRequest());
        rootMenuItems[3] = new MenuItem("Меню склада.", new MenuStorage());
        rootMenuItems[4] = new MenuItem("Выход.", new Exit());

        return new Menu("Главное меню:", rootMenuItems);
    }

    public static Menu bookMenu() {
        MenuItem[] bookMenuItems = new MenuItem[8];
        bookMenuItems[0] = new MenuItem("Добавить книгу.", new AddBook());
        bookMenuItems[1] = new MenuItem("Список книг.", new ListBooks());
        bookMenuItems[2] = new MenuItem("Список книг по название книги.", new ListSortNameBooks());
        bookMenuItems[3] = new MenuItem("Список книг по дате.", new ListSortDateBooks());
        bookMenuItems[4] = new MenuItem("Список книг по цене.", new ListSortPriceBooks());
        bookMenuItems[5] = new MenuItem("Список книг по статусу.", new ListSortStatusBooks());
        bookMenuItems[6] = new MenuItem("Главное меню.", new MenuRoot());
        bookMenuItems[7] = new MenuItem("Выход.", new Exit());

        return new Menu("Меню книг:", bookMenuItems);
    }

    public static Menu orderMenu() {
        MenuItem[] orderMenuItems = new MenuItem[8];
        orderMenuItems[0] = new MenuItem("Создать заказ.", new AddOrder());
        orderMenuItems[1] = new MenuItem("Отменить заказ.", new CancelOrder());
        orderMenuItems[2] = new MenuItem("Изменить статус заказа.", new ChangeOrderStatus());
        orderMenuItems[3] = new MenuItem("Сортированный список заказов.", new ListSortOrder());
        orderMenuItems[4] = new MenuItem("Данные за промежуток времени.", new TimeForPeriodForTime());
        orderMenuItems[5] = new MenuItem("Удалить заказ.", new DeleteOrder());
        orderMenuItems[6] = new MenuItem("Главное меню.", new MenuRoot());
        orderMenuItems[7] = new MenuItem("Выход.", new Exit());

        return new Menu("Меню заказов:", orderMenuItems);
    }

    public static Menu requestMenu() {
        MenuItem[] requestMenuItems = new MenuItem[6];
        requestMenuItems[0] = new MenuItem("Создать запрос на книгу.", new AddRequest());
        requestMenuItems[1] = new MenuItem("Список запросов.", new ListRequests());
        requestMenuItems[2] = new MenuItem("Список запросов по количеству запросов.", new ListSortRequests());
        requestMenuItems[3] = new MenuItem("Удалить запрос.", new DeleteRequest());
        requestMenuItems[4] = new MenuItem("Главное меню.", new MenuRoot());
        requestMenuItems[5] = new MenuItem("Выход.", new Exit());

        return new Menu("Меню запросов:", requestMenuItems);
    }

    public static Menu storageMenu() {
        MenuItem[] storageMenuItems = new MenuItem[6];
        storageMenuItems[0] = new MenuItem("Добавить книгу на склад.", new AddBookToStorage());
        storageMenuItems[1] = new MenuItem("Удалить книгу со склада.", new DeleteBookFromStorage());
        storageMenuItems[2] = new MenuItem("Список книг на складе.", new ListBooksInStorage());
        storageMenuItems[3] = new MenuItem("Книги на складе более 6 месяцев.", new BookNotSellMoreSixMonth());
        storageMenuItems[4] = new MenuItem("Главное меню.", new MenuRoot());
        storageMenuItems[5] = new MenuItem("Выход.", new Exit());

        return new Menu("Меню склада:", storageMenuItems);
    }


    public static void startUI() {
        Navigator navigator = new Navigator(rootMenu());
        navigator.printMenu();
    }
}
