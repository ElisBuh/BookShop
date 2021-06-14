package ui.actions;

import api.service.IBookService;
import api.service.IOrderService;
import api.service.IRequestService;
import api.service.IStorageService;
import service.BookService;
import service.OrderService;
import service.RequestService;
import service.StorageService;

public abstract class AbstractAction {
    protected IBookService bookService = BookService.getBookServiceInstance();
    protected IOrderService orderService = OrderService.getOrderServiceInstance();
    protected IRequestService requestService = RequestService.getRequestServiceInstance();
    protected IStorageService storageService = StorageService.getStorageServiceInstance();
}
