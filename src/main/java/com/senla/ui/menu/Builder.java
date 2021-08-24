package com.senla.ui.menu;

import com.senla.ui.actions.Exit;
import com.senla.ui.actions.book.*;
import com.senla.ui.actions.order.AddOrder;
import com.senla.ui.actions.order.CancelOrder;
import com.senla.ui.actions.order.ChangeOrderStatus;
import com.senla.ui.actions.order.DeleteOrder;
import com.senla.ui.actions.order.ListSortOrder;
import com.senla.ui.actions.order.TimeForPeriodForTime;
import com.senla.ui.actions.request.AddRequest;
import com.senla.ui.actions.request.DeleteRequest;
import com.senla.ui.actions.request.ListRequests;
import com.senla.ui.actions.request.ListSortRequests;
import com.senla.ui.actions.storage.AddBookToStorage;
import com.senla.ui.actions.storage.BookNotSellMoreSixMonth;
import com.senla.ui.actions.storage.DeleteBookFromStorage;
import com.senla.ui.actions.storage.ListBooksInStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class Builder {
    @Value("${storageService.month}")
    private String month;

    private final AnnotationConfigApplicationContext ctx;

    private Menu rootMenu;

    @Autowired
    public Builder(AnnotationConfigApplicationContext ctx) {
        this.ctx = ctx;
    }


    public void buildMenu() {
        MenuItem[] rootMenuItems = new MenuItem[5];
        rootMenuItems[0] = new MenuItem("Выход",ctx.getBean(Exit.class),rootMenu);
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
        bookMenuItems[0] = new MenuItem("Выход",ctx.getBean(Exit.class),rootMenu);
        bookMenuItems[1] = new MenuItem("Добавить книгу.", ctx.getBean(AddBook.class), rootMenu);
        bookMenuItems[2] = new MenuItem("Список книг.", ctx.getBean(ListBooks.class), rootMenu);
        bookMenuItems[3] = new MenuItem("Список книг по название книги.", ctx.getBean(ListSortNameBooks.class), rootMenu);
        bookMenuItems[4] = new MenuItem("Список книг по дате.", ctx.getBean(ListSortDateBooks.class), rootMenu);
        bookMenuItems[5] = new MenuItem("Список книг по цене.", ctx.getBean(ListSortPriceBooks.class), rootMenu);
        bookMenuItems[6] = new MenuItem("Список книг по статусу.", ctx.getBean(ListSortStatusBooks.class), rootMenu);
        bookMenuItems[7] = new MenuItem("Главное меню.", () -> { }, getRootMenu());


        return new Menu("Меню книг:", bookMenuItems);
    }

    private Menu orderMenu() {
        MenuItem[] orderMenuItems = new MenuItem[8];
        orderMenuItems[0] = new MenuItem("Выход",ctx.getBean(Exit.class),rootMenu);
        orderMenuItems[1] = new MenuItem("Создать заказ.", ctx.getBean(AddOrder.class), rootMenu);
        orderMenuItems[2] = new MenuItem("Отменить заказ.", ctx.getBean(CancelOrder.class), rootMenu);
        orderMenuItems[3] = new MenuItem("Изменить статус заказа.", ctx.getBean(ChangeOrderStatus.class), rootMenu);
        orderMenuItems[4] = new MenuItem("Сортированный список заказов.", ctx.getBean(ListSortOrder.class), rootMenu);
        orderMenuItems[5] = new MenuItem("Данные за промежуток времени.", ctx.getBean(TimeForPeriodForTime.class), rootMenu);
        orderMenuItems[6] = new MenuItem("Удалить заказ.", ctx.getBean(DeleteOrder.class), rootMenu);
        orderMenuItems[7] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню заказов:", orderMenuItems);
    }

    private Menu requestMenu() {
        MenuItem[] requestMenuItems = new MenuItem[6];
        requestMenuItems[0] = new MenuItem("Выход",ctx.getBean(Exit.class),rootMenu);
        requestMenuItems[1] = new MenuItem("Создать запрос на книгу.", ctx.getBean(AddRequest.class), rootMenu);
        requestMenuItems[2] = new MenuItem("Список запросов.", ctx.getBean(ListRequests.class), rootMenu);
        requestMenuItems[3] = new MenuItem("Список запросов по количеству запросов.", ctx.getBean(ListSortRequests.class), rootMenu);
        requestMenuItems[4] = new MenuItem("Удалить запрос.", ctx.getBean(DeleteRequest.class), rootMenu);
        requestMenuItems[5] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню запросов:", requestMenuItems);
    }

    private Menu storageMenu() {

        MenuItem[] storageMenuItems = new MenuItem[6];
        storageMenuItems[0] = new MenuItem("Выход",ctx.getBean(Exit.class),rootMenu);
        storageMenuItems[1] = new MenuItem("Добавить книгу на склад.", ctx.getBean(AddBookToStorage.class), rootMenu);
        storageMenuItems[2] = new MenuItem("Удалить книгу со склада.", ctx.getBean(DeleteBookFromStorage.class), rootMenu);
        storageMenuItems[3] = new MenuItem("Список книг на складе.", ctx.getBean(ListBooksInStorage.class), rootMenu);
        storageMenuItems[4] = new MenuItem("Книги на складе более "+ month +" месяцев.", ctx.getBean(BookNotSellMoreSixMonth.class), rootMenu);
        storageMenuItems[5] = new MenuItem("Главное меню.", () -> { }, rootMenu);

        return new Menu("Меню склада:", storageMenuItems);
    }



}
