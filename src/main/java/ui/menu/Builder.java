package ui.menu;

public class Builder {
    private final Menu rootMenu;

    public Builder(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu(){
        Navigator navigator = new Navigator(rootMenu);
        navigator.printMenu();
    }

}
