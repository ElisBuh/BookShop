package com.senla.ui.menu;


import com.senla.ui.actions.IAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class Builder {
    @Value("${storageService.month}")
    private String month;

    private final Map<String, IAction> actions;


    private Menu rootMenu;

    public Builder(Map<String, IAction> actions) {
        this.actions = actions;
    }


    public void buildMenu() {
        MenuItem[] rootMenuItems = new MenuItem[5];
        rootMenuItems[0] = new MenuItem("Выход",actions.get("exit"),rootMenu);
        rootMenuItems[1] = new MenuItem("Меню книг.", () -> { }, bookMenu());
        rootMenuItems[2] = new MenuItem("Меню заказов.", () -> { }, orderMenu());
        rootMenuItems[3] = new MenuItem("Меню запросов.", () -> { }, requestMenu());
        rootMenuItems[4] = new MenuItem("Меню склада.", () -> { }, storageMenu());
        this.rootMenu = new Menu("Главное меню:", rootMenuItems);
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    private Menu bookMenu() {

        MenuItem[] bookMenuItems = new MenuItem[8];
        bookMenuItems[0] = new MenuItem("Выход",actions.get("exit"),rootMenu);
        bookMenuItems[1] = new MenuItem("Добавить книгу.", actions.get("addBook"), rootMenu);
        bookMenuItems[2] = new MenuItem("Список книг.", actions.get("listBooks"), rootMenu);
        bookMenuItems[3] = new MenuItem("Список книг по название книги.", actions.get("listSortNameBooks"), rootMenu);
        bookMenuItems[4] = new MenuItem("Список книг по дате.", actions.get("listSortDateBooks"), rootMenu);
        bookMenuItems[5] = new MenuItem("Список книг по цене.", actions.get("listSortPriceBooks"), rootMenu);
        bookMenuItems[6] = new MenuItem("Список книг по статусу.", actions.get("listSortStatusBooks"), rootMenu);
        bookMenuItems[7] = new MenuItem("Главное меню.", () -> { }, getRootMenu());


        return new Menu("Меню книг:", bookMenuItems);
    }

    private Menu orderMenu() {
        MenuItem[] orderMenuItems = new MenuItem[8];
        orderMenuItems[0] = new MenuItem("Выход",actions.get("exit"),rootMenu);
        orderMenuItems[1] = new MenuItem("Создать заказ.", actions.get("addOrder"), rootMenu);
        orderMenuItems[2] = new MenuItem("Отменить заказ.", actions.get("cancelOrder"), rootMenu);
        orderMenuItems[3] = new MenuItem("Изменить статус заказа.", actions.get("changeOrderStatus"), rootMenu);
        orderMenuItems[4] = new MenuItem("Сортированный список заказов.", actions.get("listSortOrder"), rootMenu);
        orderMenuItems[5] = new MenuItem("Данные за промежуток времени.", actions.get("timeForPeriodForTime"), rootMenu);
        orderMenuItems[6] = new MenuItem("Удалить заказ.", actions.get("deleteOrder"), rootMenu);
        orderMenuItems[7] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню заказов:", orderMenuItems);
    }

    private Menu requestMenu() {
        MenuItem[] requestMenuItems = new MenuItem[6];
        requestMenuItems[0] = new MenuItem("Выход",actions.get("exit"),rootMenu);
        requestMenuItems[1] = new MenuItem("Создать запрос на книгу.", actions.get("addRequest"), rootMenu);
        requestMenuItems[2] = new MenuItem("Список запросов.", actions.get("listRequests"), rootMenu);
        requestMenuItems[3] = new MenuItem("Список запросов по количеству запросов.", actions.get("listSortRequests"), rootMenu);
        requestMenuItems[4] = new MenuItem("Удалить запрос.", actions.get("deleteRequest"), rootMenu);
        requestMenuItems[5] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню запросов:", requestMenuItems);
    }

    private Menu storageMenu() {

        MenuItem[] storageMenuItems = new MenuItem[6];
        storageMenuItems[0] = new MenuItem("Выход",actions.get("exit"),rootMenu);
        storageMenuItems[1] = new MenuItem("Добавить книгу на склад.", actions.get("addBookToStorage"), rootMenu);
        storageMenuItems[2] = new MenuItem("Удалить книгу со склада.", actions.get("deleteBookFromStorage"), rootMenu);
        storageMenuItems[3] = new MenuItem("Список книг на складе.", actions.get("listBooksInStorage"), rootMenu);
        storageMenuItems[4] = new MenuItem("Книги на складе более "+ month +" месяцев.", actions.get("bookNotSellMoreSixMonth"), rootMenu);
        storageMenuItems[5] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню склада:", storageMenuItems);
    }



}
