package com.senla.util.serialization;

import com.senla.api.service.IBookService;
import com.senla.api.service.IOrderService;
import com.senla.api.service.IRequestService;
import com.senla.api.service.IStorageService;
import com.senla.util.annotation.InjectByType;
import com.senla.util.annotation.InjectProperty;
import com.senla.util.annotation.Singleton;

@Singleton
public class Deserialization {

    @InjectByType
    protected IBookService bookService;
    @InjectByType
    protected IOrderService orderService;
    @InjectByType
    protected IRequestService requestService;
    @InjectByType
    protected IStorageService storageService;

    @InjectProperty("serBook")
    private String pathBook;
    @InjectProperty("serOrder")
    private String pathOrder;
    @InjectProperty("serRequest")
    private String pathRequest;
    @InjectProperty("serStorage")
    private String pathStorage;

    public void deserialize(){
        Serialize serBook = new Serialize(pathBook),
                serOrder = new Serialize(pathOrder),
                serRequest = new Serialize(pathRequest),
                serStorage = new Serialize(pathStorage);

        serOrder.is();
        bookService.set(serBook.deserialize());
        orderService.set(serOrder.deserialize());
        requestService.set(serRequest.deserialize());
        storageService.set(serStorage.deserialize());
    }

}
