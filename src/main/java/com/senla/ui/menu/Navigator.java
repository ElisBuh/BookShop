package com.senla.ui.menu;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.ui.actions.ConsoleHelper;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;


@Component
public class Navigator {
    private static final Logger log = LoggerFactory.getLogger(Navigator.class);
    private Menu currentMenu;

    private Builder builder;

    public Navigator(Builder builder) {
        this.builder = builder;
    }

    public void printMenu() {
        try {
        ConsoleHelper.writeMessage(currentMenu.getName());
        IntStream.range(0, currentMenu.getMenuItem().length).mapToObj(i -> i + "-" + currentMenu.getMenuItem()[i]).forEach(System.out::println);
        ConsoleHelper.writeMessage("Сделайте выбор");
        }catch (NullPointerException e){
            currentMenu = builder.getRootMenu();
            printMenu();
        }
    }

    public void navigate(int point) {
        if (currentMenu != null) {
            try {
            MenuItem currentItem = currentMenu.getMenuItem()[point];
            currentItem.getAction().execute();
            currentMenu = currentItem.getNextMenu();
            } catch (ArrayIndexOutOfBoundsException e){
                log.error("navigate: {}, {}", point, e.toString());
                System.err.println("Не верно веден пункт выбора");
            }
        }
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}


