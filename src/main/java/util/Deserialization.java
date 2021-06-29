package util;

import ui.actions.AbstractAction;


public class Deserialization extends AbstractAction {

    public void deserialize(){
        final Serialize serBook = new Serialize(Config.configProperties("serBook")),
                serOrder = new Serialize(Config.configProperties("serOrder")),
                serRequest = new Serialize(Config.configProperties("serRequest")),
                serStorage = new Serialize(Config.configProperties("serStorage"));

        serOrder.is();
        bookService.set(serBook.deserialize());
        orderService.set(serOrder.deserialize());
        requestService.set(serRequest.deserialize());
        storageService.set(serStorage.deserialize());
    }

}
