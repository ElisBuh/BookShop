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
        final Serialize serBook = new Serialize("src/main/resources/BookDate.obj"),
                serOrder = new Serialize("src/main/resources/OrderDate.obj"),
                serRequest = new Serialize("src/main/resources/RequestDate.obj"),
                serStorage = new Serialize("src/main/resources/StorageDate.obj");

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
