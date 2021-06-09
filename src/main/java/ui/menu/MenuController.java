package ui.menu;

public class MenuController {
    private final Builder builder;
    private final Navigator navigator;

    public MenuController(Navigator navigator) {
        this.navigator = navigator;
        this.builder = new Builder(navigator.getCurrentMenu());
    }

    public void run() {
        int point = navigator.navigate();
        try {
            navigator.getCurrentMenu().getMenuItem()[point].getAction().execute();
            builder.buildMenu();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Не вверный ввод.");
            run();
        }
    }

}


