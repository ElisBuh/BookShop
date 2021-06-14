package ui.menu;

import ui.actions.book.*;
import ui.actions.order.*;
import ui.actions.request.AddRequest;
import ui.actions.request.DeleteRequest;
import ui.actions.request.ListRequests;
import ui.actions.request.ListSortRequests;
import ui.actions.storage.AddBookToStorage;
import ui.actions.storage.BookNotSellMoreSixMonth;
import ui.actions.storage.DeleteBookFromStorage;
import ui.actions.storage.ListBooksInStorage;


public class Builder {
    private Menu rootMenu;
    private static Builder builderInstance;

    private Builder() {
    }

    public static Builder getBuilderInstance() {
        if (builderInstance == null) {
            builderInstance = new Builder();
        }
        return builderInstance;
    }

    public void buildMenu() {
        MenuItem[] rootMenuItems = new MenuItem[4];
        rootMenuItems[0] = new MenuItem("Меню книг.", () -> { }, bookMenu());
        rootMenuItems[1] = new MenuItem("Меню заказов.", () -> { }, orderMenu());
        rootMenuItems[2] = new MenuItem("Меню запросов.", () -> { }, requestMenu());
        rootMenuItems[3] = new MenuItem("Меню склада.", () -> { }, storageMenu());
        this.rootMenu = new Menu("Главное меню:", rootMenuItems);
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    private Menu bookMenu() {

        MenuItem[] bookMenuItems = new MenuItem[7];
        bookMenuItems[0] = new MenuItem("Добавить книгу.", new AddBook(), rootMenu);
        bookMenuItems[1] = new MenuItem("Список книг.", new ListBooks(), rootMenu);
        bookMenuItems[2] = new MenuItem("Список книг по название книги.", new ListSortNameBooks(), rootMenu);
        bookMenuItems[3] = new MenuItem("Список книг по дате.", new ListSortDateBooks(), rootMenu);
        bookMenuItems[4] = new MenuItem("Список книг по цене.", new ListSortPriceBooks(), rootMenu);
        bookMenuItems[5] = new MenuItem("Список книг по статусу.", new ListSortStatusBooks(), rootMenu);
        bookMenuItems[6] = new MenuItem("Главное меню.", null, getRootMenu());


        return new Menu("Меню книг:", bookMenuItems);
    }

    private Menu orderMenu() {
        MenuItem[] orderMenuItems = new MenuItem[7];
        orderMenuItems[0] = new MenuItem("Создать заказ.", new AddOrder(), rootMenu);
        orderMenuItems[1] = new MenuItem("Отменить заказ.", new CancelOrder(), rootMenu);
        orderMenuItems[2] = new MenuItem("Изменить статус заказа.", new ChangeOrderStatus(), rootMenu);
        orderMenuItems[3] = new MenuItem("Сортированный список заказов.", new ListSortOrder(), rootMenu);
        orderMenuItems[4] = new MenuItem("Данные за промежуток времени.", new TimeForPeriodForTime(), rootMenu);
        orderMenuItems[5] = new MenuItem("Удалить заказ.", new DeleteOrder(), rootMenu);
        orderMenuItems[6] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню заказов:", orderMenuItems);
    }

    private Menu requestMenu() {
        MenuItem[] requestMenuItems = new MenuItem[5];
        requestMenuItems[0] = new MenuItem("Создать запрос на книгу.", new AddRequest(), rootMenu);
        requestMenuItems[1] = new MenuItem("Список запросов.", new ListRequests(), rootMenu);
        requestMenuItems[2] = new MenuItem("Список запросов по количеству запросов.", new ListSortRequests(), rootMenu);
        requestMenuItems[3] = new MenuItem("Удалить запрос.", new DeleteRequest(), rootMenu);
        requestMenuItems[4] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню запросов:", requestMenuItems);
    }

    private Menu storageMenu() {
        MenuItem[] storageMenuItems = new MenuItem[5];
        storageMenuItems[0] = new MenuItem("Добавить книгу на склад.", new AddBookToStorage(), rootMenu);
        storageMenuItems[1] = new MenuItem("Удалить книгу со склада.", new DeleteBookFromStorage(), rootMenu);
        storageMenuItems[2] = new MenuItem("Список книг на складе.", new ListBooksInStorage(), rootMenu);
        storageMenuItems[3] = new MenuItem("Книги на складе более 6 месяцев.", new BookNotSellMoreSixMonth(), rootMenu);
        storageMenuItems[4] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню склада:", storageMenuItems);
    }
}
