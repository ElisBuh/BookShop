package com.senla.ui.actions;

import com.senla.api.service.IBookService;
import com.senla.api.service.IOrderService;
import com.senla.api.service.IRequestService;
import com.senla.api.service.IStorageService;
import com.senla.service.BookService;
import com.senla.service.OrderService;
import com.senla.service.RequestService;
import com.senla.service.StorageService;

public abstract class AbstractAction {
    protected IBookService bookService = BookService.getBookServiceInstance();
    protected IOrderService orderService = OrderService.getOrderServiceInstance();
    protected IRequestService requestService = RequestService.getRequestServiceInstance();
    protected IStorageService storageService = StorageService.getStorageServiceInstance();
}
