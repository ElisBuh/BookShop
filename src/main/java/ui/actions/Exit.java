package ui.actions;

public class Exit implements IAction{
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("До свидания!");
        System.exit(0);
    }
}
