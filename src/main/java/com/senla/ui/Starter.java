package com.senla.ui;

import com.senla.util.AppConfig;
import com.senla.ui.menu.MenuController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Starter {

    public static void main(String[] args) {
//        ApplicationContext context = Application.run("com.senla");
//        MenuController menuController = context.getObject(MenuController.class);
//        menuController.run();
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MenuController menuController = ctx.getBean(MenuController.class);
        menuController.run();

    }


}
