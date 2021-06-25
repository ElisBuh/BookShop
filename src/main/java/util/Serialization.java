package util;

import model.Book;
import model.Order;
import model.Request;
import model.Storage;
import ui.actions.AbstractAction;

import java.util.ArrayList;
import java.util.List;

public class Serialization extends AbstractAction {

    public void serialize(){
        final Serialize serBook = new Serialize(Config.configProperties("serBook")),
                serOrder = new Serialize(Config.configProperties("serOrder")),
                serRequest = new Serialize(Config.configProperties("serRequest")),
                serStorage = new Serialize(Config.configProperties("serStorage"));

        List<Book> books = bookService.getListBooks();
        List<Order> orders = orderService.ListOrders();
        List<Request> request = requestService.listRequests();
        List<Book> storage = storageService.getStorageBooks();

        serBook.serialize(books);
        serOrder.serialize(orders);
        serRequest.serialize(request);
        serStorage.serialize(storage);
    }
}
