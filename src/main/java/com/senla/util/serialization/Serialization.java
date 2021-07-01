package com.senla.util.serialization;

import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.Request;
import com.senla.ui.actions.AbstractAction;
import com.senla.util.Config;

import java.util.List;

public class Serialization extends AbstractAction {

    public void serialize(){
        final Serialize serBook = new Serialize(Config.configProperties("serBook")),
                serOrder = new Serialize(Config.configProperties("serOrder")),
                serRequest = new Serialize(Config.configProperties("serRequest")),
                serStorage = new Serialize(Config.configProperties("serStorage"));

        List<Book> books = bookService.getAll();
        List<Order> orders = orderService.getAll();
        List<Request> request = requestService.getAll();
        List<Book> storage = storageService.getAll();

        serBook.serialize(books);
        serOrder.serialize(orders);
        serRequest.serialize(request);
        serStorage.serialize(storage);
    }
}
