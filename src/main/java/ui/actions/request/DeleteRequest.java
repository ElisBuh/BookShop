package ui.actions.request;

import exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.actions.AbstractAction;
import ui.actions.ConsoleHelper;
import ui.actions.IAction;

public class DeleteRequest extends AbstractAction implements IAction {
    private static final Logger log = LoggerFactory.getLogger(DeleteRequest.class);

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите Id книги на которую хотите оставить запрос:");
            int id = ConsoleHelper.readInt();
            requestService.deleteRequest(requestService.getRequest(bookService.getBook(id)));
        } catch (ServiceException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
