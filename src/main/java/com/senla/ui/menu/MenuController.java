package com.senla.ui.menu;

import com.senla.ui.actions.ConsoleHelper;
import com.senla.util.serialization.Deserialization;

public class MenuController {
    private final Builder builder = Builder.getBuilderInstance();
    private final Navigator navigator = Navigator.getNavigatorInstance();
    private final Deserialization deserialization = new Deserialization();

    private static volatile MenuController menuControllerInstance;

    private MenuController() {
    }

    public static MenuController getMenuControllerInstance() {
        if (menuControllerInstance == null) {
            menuControllerInstance = new MenuController();
        }
        return menuControllerInstance;
    }

    public void run() {
        deserialization.deserialize();
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getRootMenu());
        int point = -1;
        while (point != 0) {
            navigator.printMenu();
            point = ConsoleHelper.readInt();
//            if (point == 0){
//                break;
//            }
            navigator.navigate(point);

        }
    }

}


