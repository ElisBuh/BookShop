package com.senla.ui.actions.request;

import com.senla.exceptions.DaoException;
import com.senla.ui.actions.AbstractAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;

public class AddRequest extends AbstractAction implements IAction {
    private static final Logger log = LoggerFactory.getLogger(AddRequest.class);

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите Id книги на которую хотите оставить запрос:");
            int id = ConsoleHelper.readInt();
            boolean isRequest = requestService.save(bookService.get(id));
            if (isRequest) {
                ConsoleHelper.writeMessage("Запрос добавлен.");
            } else ConsoleHelper.writeMessage("Книга есть на складе.");
        } catch (DaoException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
