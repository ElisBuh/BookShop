package ui.actions.request;

import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class DeleteRequest extends AbstractAction implements IAction {
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Введите Id книги на которую хотите оставить запрос:");
        int id = ConsoleHelper.readInt();
        requestService.deleteRequest(requestService.getRequest(bookService.getBook(id)));
    }
}
