package com.senla.ui.menu;


import com.senla.ui.actions.IAction;

public class MenuItem {
    private String title;
    private IAction action;
    private Menu nextMenu;


    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;

    }

    public IAction getAction() {
        return action;
    }


    @Override
    public String toString() {
        return title;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }
}
