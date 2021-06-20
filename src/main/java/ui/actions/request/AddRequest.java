package ui.actions.request;

import exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class AddRequest extends AbstractAction implements IAction {
    private static final Logger log = LoggerFactory.getLogger(AddRequest.class);

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите Id книги на которую хотите оставить запрос:");
            int id = ConsoleHelper.readInt();
            boolean isRequest = requestService.addRequest(bookService.getBook(id));
            if (isRequest) {
                ConsoleHelper.writeMessage("Запрос добавлен.");
            } else ConsoleHelper.writeMessage("Книга есть на складе.");
        } catch (ServiceException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
