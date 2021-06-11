package ui.menu;

import ui.actions.ConsoleHelper;

import java.util.stream.IntStream;

public class Navigator {
    private Menu currentMenu;
    private static volatile Navigator navigatorInstance;

    private Navigator() {
    }

    public static Navigator getNavigatorInstance() {
        if (navigatorInstance == null) {
            navigatorInstance = new Navigator();
        }
        return navigatorInstance;
    }

    public void printMenu() {
        ConsoleHelper.writeMessage(currentMenu.getName());
        IntStream.range(0, currentMenu.getMenuItem().length).mapToObj(i -> i + "-" + currentMenu.getMenuItem()[i]).forEach(System.out::println);
        ConsoleHelper.writeMessage("Сделайте выбор");
    }

    public void navigate(int point) {
        if (currentMenu != null) {
            MenuItem currentItem = currentMenu.getMenuItem()[point];
            currentItem.getAction().execute();
            currentMenu = currentItem.getNextMenu();
        }
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}


