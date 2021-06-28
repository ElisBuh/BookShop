package ui.actions;

import util.Serialization;

public class Exit extends AbstractAction implements IAction{
    @Override
    public void execute() {
        Serialization serialization = new Serialization();
        serialization.serialize();
        System.out.println("Good Buy");
    }
}
