package com.senla.ui.menu;

import com.senla.ui.actions.ConsoleHelper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value = "singleton")
public class MenuController {
    private Builder builder;
    private Navigator navigator;


    public MenuController(Builder builder, Navigator navigator) {
        this.builder = builder;
        this.navigator = navigator;
    }

    public void run() {
//        deserialization.deserialize();
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getRootMenu());
        int point = -1;
        while (point != 0) {
            navigator.printMenu();
            point = ConsoleHelper.readInt();
            navigator.navigate(point);

        }
    }

//    @PostConstruct
//    public void init() {
//        bookService.save("War and Peace", "Leo Tolstoy", 25, LocalDate.of(2001, 5, 25));
//        bookService.save("War and Peace3", "Leo Tolstoy1", 38, LocalDate.of(2011, 1, 22));
//        bookService.save("War and Peace1", "Leo Tolstoy3", 52, LocalDate.of(2041, 2, 15));
//        bookService.save("War and Peace5", "Leo Tolstoy2", 55, LocalDate.of(2061, 4, 23));
//        bookService.save("War and Peace4", "Leo Tolstoy5", 21, LocalDate.of(2012, 10, 1));
//        bookService.save("War and Peace6", "Leo Tolstoy4", 26, LocalDate.of(2021, 6, 12));
//        bookService.getAll().forEach(System.out::println);
//    }

}


