package ui.actions.request;

import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class AddRequest extends AbstractAction implements IAction {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Введите Id книги на которую хотите оставить запрос:");
        int id = ConsoleHelper.readInt();
        boolean isRequest = requestService.addRequest(bookService.getBook(id));
        if (isRequest) {
            ConsoleHelper.writeMessage("Запрос добавлен.");
        } else ConsoleHelper.writeMessage("Книга есть на складе.");
    }
}
