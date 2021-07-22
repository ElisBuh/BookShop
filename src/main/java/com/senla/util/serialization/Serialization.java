package com.senla.util.serialization;

import com.senla.api.service.IBookService;
import com.senla.api.service.IOrderService;
import com.senla.api.service.IRequestService;
import com.senla.api.service.IStorageService;
import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.Request;
import com.senla.model.Storage;
import com.senla.util.annotation.InjectByType;
import com.senla.util.annotation.InjectProperty;
import com.senla.util.annotation.Singleton;

import java.sql.SQLException;
import java.util.List;


@Singleton
public class Serialization {
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


    public void serialize(){
        Serialize serBook = new Serialize(pathBook),
                serOrder = new Serialize(pathOrder),
                serRequest = new Serialize(pathRequest),
                serStorage = new Serialize(pathStorage);

        List<Book> books = null;
        try {
            books = bookService.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<Order> orders = orderService.getAll();
        List<Request> request = requestService.getAll();
        List<Storage> storage = storageService.getAll();

        serBook.serialize(books);
        serOrder.serialize(orders);
        serRequest.serialize(request);
        serStorage.serialize(storage);
    }
}
