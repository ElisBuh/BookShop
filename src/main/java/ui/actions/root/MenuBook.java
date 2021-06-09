package ui.actions.root;

import ui.Starter;
import ui.actions.IAction;
import ui.menu.Builder;

public class MenuBook implements IAction {
    @Override
    public void execute() {
        Builder builder = new Builder(Starter.bookMenu());
        builder.buildMenu();
    }
}
