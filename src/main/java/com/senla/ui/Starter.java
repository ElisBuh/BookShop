package com.senla.ui;

import com.senla.ui.menu.MenuController;
import com.senla.util.config.DataConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Starter {

    public static void main(String[] args) {
//        ApplicationContext context = Application.run("com.senla");
//        MenuController menuController = context.getObject(MenuController.class);
//        menuController.run();
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataConfig.class);
        MenuController menuController = ctx.getBean(MenuController.class);
        menuController.run();

    }


}
