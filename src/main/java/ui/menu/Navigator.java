package ui.menu;

import ui.actions.ConsoleHelper;

import java.util.stream.IntStream;

public class Navigator {
    private final Menu currentMenu;

    public Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        ConsoleHelper.writeMessage(currentMenu.getName());
        IntStream.range(0, currentMenu.getMenuItem().length).mapToObj(i -> i + "-" + currentMenu.getMenuItem()[i]).forEach(System.out::println);
        ConsoleHelper.writeMessage("Сделайте выбор");
        MenuController menuController = new MenuController(this);
        menuController.run();

    }

    public int navigate() {
        return ConsoleHelper.readInt();
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }
}
