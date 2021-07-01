package com.senla.ui.actions.request;

import com.senla.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.ui.actions.AbstractAction;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;

public class DeleteRequest extends AbstractAction implements IAction {
    private static final Logger log = LoggerFactory.getLogger(DeleteRequest.class);

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите Id книги с которой хотите снять запрос:");
            int id = ConsoleHelper.readInt();
            requestService.delete(requestService.get(bookService.get(id)));
        } catch (DaoException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
