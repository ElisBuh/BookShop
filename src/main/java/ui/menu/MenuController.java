package ui.menu;

import ui.actions.ConsoleHelper;

public class MenuController {
    private final Builder builder = Builder.getBuilderInstance();
    private final Navigator navigator = Navigator.getNavigatorInstance();

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
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        while (true) {
            navigator.navigate(ConsoleHelper.readInt());
            navigator.printMenu();

        }
    }

}


