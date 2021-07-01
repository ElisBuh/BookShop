package com.senla.service;

import com.senla.api.dao.IStorageDao;
import com.senla.api.service.IRequestService;
import com.senla.api.service.IStorageService;
import com.senla.dao.StorageDao;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.StatusBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.senla.util.Config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageService implements IStorageService {
    private static final Logger log = LoggerFactory.getLogger(StorageService.class);

    private final IStorageDao storageDao = StorageDao.getStorageDaoInstance();
    private final IRequestService requestService;
    private static volatile StorageService storageServiceInstance;

    private StorageService() {
        this.requestService = RequestService.getRequestServiceInstance();
    }

    public static StorageService getStorageServiceInstance() {
        if (storageServiceInstance == null) {
            storageServiceInstance = new StorageService();
        }
        return storageServiceInstance;
    }

    @Override
    public boolean add(Book book, LocalDate localDate) {
        log.info("Add_BY_Book: {}, id:{}", book.getNameBook(), book.getId());
        try {
            if (storageDao.getAll().contains(book)) {
                return false;
            }
            book.setStatusBook(StatusBook.INSTOCK);
            book.setDateReceipt(localDate);
            storageDao.add(book);
            if (requestService.isRequest(book)) {
                if (Boolean.parseBoolean(Config.configProperties("storageService.permissionChangeStatusRequest"))){
                requestService.delete(requestService.get(book));
                }
            }
            return true;
        } catch (DaoException e) {
            log.error("addBook book-id: {}, {}", book, book.getId());
            throw e;
        }
    }

    @Override
    public boolean delete(Book book) {
        try {
            log.info("Delete_Book: {}-{}", book.getNameBook(), book.getId());
            book.setStatusBook(StatusBook.ABSENT);
            return storageDao.delete(book);
        } catch (DaoException e) {
            log.error("deleteBook: {}-{}", book.getNameBook(), book.getId());
            throw e;

        }


    }

    @Override
    public List<Book> BookNotSellMoreNmonth() {
        int month = Integer.parseInt(Config.configProperties("storageService.month"));
        return storageDao.getAll().stream()
                .filter(book -> LocalDate.now().minusMonths(month).isAfter(book.getDateReceipt()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(storageDao.getAll());
    }

    @Override
    public <T> void set(List<T> list) {
        log.info("Десериализация Storage");
        list.stream().map(e -> (Book) e).forEach(storageDao::add);
    }
}
