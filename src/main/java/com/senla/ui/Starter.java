package com.senla.ui;

import com.senla.ui.menu.MenuController;
import com.senla.util.di.Application;
import com.senla.util.di.ApplicationContext;


public class Starter {

    public static void main(String[] args) {
        ApplicationContext context = Application.run("com.senla");
        MenuController menuController = context.getObject(MenuController.class);
        menuController.run();

    }


}
