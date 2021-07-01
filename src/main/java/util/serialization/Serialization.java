package util.serialization;

import model.Book;
import model.Order;
import model.Request;
import ui.actions.AbstractAction;
import util.Config;

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
