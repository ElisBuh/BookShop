package com.senla.ui.actions.request;

import com.senla.api.service.IRequestService;
import com.senla.exceptions.DaoException;
import com.senla.ui.actions.ConsoleHelper;
import com.senla.ui.actions.IAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteRequest implements IAction {
    private static final Logger log = LoggerFactory.getLogger(DeleteRequest.class);
    @Autowired
    private IRequestService requestService;

    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("Введите Id запроса который хотите снять:");
            int id = ConsoleHelper.readInt();
            requestService.delete(requestService.get(id));
        } catch (DaoException e) {
            log.error(e.toString());
            System.err.println("Такого id нет");
        }
    }
}
