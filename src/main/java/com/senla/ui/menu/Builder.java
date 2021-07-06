package com.senla.ui.menu;

import com.senla.ui.actions.Exit;
import com.senla.ui.actions.book.*;
import com.senla.ui.actions.order.*;
import com.senla.ui.actions.request.AddRequest;
import com.senla.ui.actions.request.DeleteRequest;
import com.senla.ui.actions.request.ListRequests;
import com.senla.ui.actions.request.ListSortRequests;
import com.senla.ui.actions.storage.AddBookToStorage;
import com.senla.ui.actions.storage.BookNotSellMoreSixMonth;
import com.senla.ui.actions.storage.DeleteBookFromStorage;
import com.senla.ui.actions.storage.ListBooksInStorage;
import com.senla.util.annotation.InjectByType;
import com.senla.util.annotation.InjectProperty;
import com.senla.util.annotation.Singleton;


@Singleton
public class Builder {
    @InjectProperty("storageService.month")
    private String month;

    @InjectByType
    private Exit exit;
    @InjectByType
    private AddBook addBook;
    @InjectByType
    private ListBooks listBooks;
    @InjectByType
    private ListSortNameBooks listSortNameBooks;
    @InjectByType
    private ListSortDateBooks listSortDateBooks;
    @InjectByType
    private ListSortPriceBooks listSortPriceBooks;
    @InjectByType
    private ListSortStatusBooks listSortStatusBooks;

    @InjectByType
    private AddOrder addOrder;
    @InjectByType
    private CancelOrder cancelOrder;
    @InjectByType
    private ChangeOrderStatus changeOrderStatus;
    @InjectByType
    private ListSortOrder listSortOrder;
    @InjectByType
    private TimeForPeriodForTime timeForPeriodForTime;
    @InjectByType
    private DeleteOrder deleteOrder;

    @InjectByType
    private AddRequest addRequest;
    @InjectByType
    private ListRequests listRequests;
    @InjectByType
    private ListSortRequests listSortRequests;
    @InjectByType
    private DeleteRequest deleteRequest;

    @InjectByType
    private AddBookToStorage addBookToStorage;
    @InjectByType
    private DeleteBookFromStorage deleteBookFromStorage;
    @InjectByType
    private ListBooksInStorage listBooksInStorage;

    private Menu rootMenu;


    public void buildMenu() {
        MenuItem[] rootMenuItems = new MenuItem[5];
        rootMenuItems[0] = new MenuItem("Выход",exit,rootMenu);
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
        bookMenuItems[0] = new MenuItem("Выход",exit,rootMenu);
        bookMenuItems[1] = new MenuItem("Добавить книгу.", addBook, rootMenu);
        bookMenuItems[2] = new MenuItem("Список книг.", listBooks, rootMenu);
        bookMenuItems[3] = new MenuItem("Список книг по название книги.", listSortNameBooks, rootMenu);
        bookMenuItems[4] = new MenuItem("Список книг по дате.", listSortDateBooks, rootMenu);
        bookMenuItems[5] = new MenuItem("Список книг по цене.", listSortPriceBooks, rootMenu);
        bookMenuItems[6] = new MenuItem("Список книг по статусу.", listSortStatusBooks, rootMenu);
        bookMenuItems[7] = new MenuItem("Главное меню.", () -> { }, getRootMenu());


        return new Menu("Меню книг:", bookMenuItems);
    }

    private Menu orderMenu() {
        MenuItem[] orderMenuItems = new MenuItem[8];
        orderMenuItems[0] = new MenuItem("Выход",exit,rootMenu);
        orderMenuItems[1] = new MenuItem("Создать заказ.", addOrder, rootMenu);
        orderMenuItems[2] = new MenuItem("Отменить заказ.", cancelOrder, rootMenu);
        orderMenuItems[3] = new MenuItem("Изменить статус заказа.", changeOrderStatus, rootMenu);
        orderMenuItems[4] = new MenuItem("Сортированный список заказов.", listSortOrder, rootMenu);
        orderMenuItems[5] = new MenuItem("Данные за промежуток времени.", timeForPeriodForTime, rootMenu);
        orderMenuItems[6] = new MenuItem("Удалить заказ.", deleteOrder, rootMenu);
        orderMenuItems[7] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню заказов:", orderMenuItems);
    }

    private Menu requestMenu() {
        MenuItem[] requestMenuItems = new MenuItem[6];
        requestMenuItems[0] = new MenuItem("Выход",exit,rootMenu);
        requestMenuItems[1] = new MenuItem("Создать запрос на книгу.", addRequest, rootMenu);
        requestMenuItems[2] = new MenuItem("Список запросов.", listRequests, rootMenu);
        requestMenuItems[3] = new MenuItem("Список запросов по количеству запросов.", listSortRequests, rootMenu);
        requestMenuItems[4] = new MenuItem("Удалить запрос.", deleteRequest, rootMenu);
        requestMenuItems[5] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню запросов:", requestMenuItems);
    }

    private Menu storageMenu() {

        MenuItem[] storageMenuItems = new MenuItem[6];
        storageMenuItems[0] = new MenuItem("Выход",exit,rootMenu);
        storageMenuItems[1] = new MenuItem("Добавить книгу на склад.", addBookToStorage, rootMenu);
        storageMenuItems[2] = new MenuItem("Удалить книгу со склада.", deleteBookFromStorage, rootMenu);
        storageMenuItems[3] = new MenuItem("Список книг на складе.", listBooksInStorage, rootMenu);
        storageMenuItems[4] = new MenuItem("Книги на складе более "+ month +" месяцев.", new BookNotSellMoreSixMonth(), rootMenu);
        storageMenuItems[5] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню склада:", storageMenuItems);
    }



}
