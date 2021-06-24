package util;

import model.Book;
import model.Order;
import ui.actions.AbstractAction;

import java.util.List;

public class Deserialization extends AbstractAction {

    public void deserialize(){
        final Serialize serBook = new Serialize("src/main/resources/BookDate.obj"),
                serOrder = new Serialize("src/main/resources/OrderDate.obj"),
                serRequest = new Serialize("src/main/resources/RequestDate.obj"),
                serStorage = new Serialize("src/main/resources/StorageDate.obj");

        serOrder.is();
        bookService.set(serBook.deserialize());
        orderService.set(serOrder.deserialize());
        requestService.set(serRequest.deserialize());
        storageService.set(serStorage.deserialize());
    }
}
