package com.senla.ui.menu;

import java.util.Arrays;

public class Menu {
    private String name;
    private MenuItem[] menuItem;


    public Menu(String name, MenuItem[] menuItem) {
        this.name = name;
        this.menuItem = menuItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuItem[] getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem[] menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", menuItem=" + Arrays.toString(menuItem) +
                '}';
    }
}
