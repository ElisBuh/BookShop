package com.senla.service;

import com.senla.api.dao.IBookDao;
import com.senla.api.dao.IStorageDao;
import com.senla.api.service.IRequestService;
import com.senla.api.service.IStorageService;
import com.senla.exceptions.DaoException;
import com.senla.exceptions.ServiceException;
import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.model.StatusBook;
import com.senla.model.Storage;
import com.senla.util.annotation.InjectByType;
import com.senla.util.annotation.InjectProperty;
import com.senla.util.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class StorageService implements IStorageService {
    private static final Logger log = LoggerFactory.getLogger(StorageService.class);

    @InjectProperty("storageService.month")
    private String month;
    @InjectProperty("storageService.permissionChangeStatusRequest")
    private String changeStatusRequest;

    @InjectByType
    private IStorageDao storageDao;

    @InjectByType
    private IBookDao bookDao;

    @InjectByType
    private IRequestService requestService;


    @Override
    public boolean add(Book book, LocalDate localDate) {
        log.info("Add_BY_Book: {}, id:{}", book.getNameBook(), book.getId());
        try {
            book.setStatusBook(StatusBook.INSTOCK);
            book.setDateReceipt(localDate);
            storageDao.save(new Storage(book));
            bookDao.set(book);
            if (requestService.isRequest(book) && Boolean.parseBoolean(changeStatusRequest)) {
                requestService.delete(requestService.findRequest(book));
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
            book.setDateReceipt(null);
            bookDao.set(book);
            return storageDao.delete(findStorage(book));
        } catch (DaoException e) {
            log.error("deleteBook: {}-{}", book.getNameBook(), book.getId());
            throw e;
        }


    }

    @Override
    public List<Storage> BookNotSellMoreNmonth() {
        log.info("Not sell list");
        try {
            return storageDao.getAll().stream()
                    .filter(storage -> LocalDate.now().minusMonths(Integer.parseInt(month)).isAfter(storage.getBook().getDateReceipt()))
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }
    }

    @Override
    public List<Storage> getAll() {
        log.info("get_All_Storage");
        try {
            return new ArrayList<>(storageDao.getAll());
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }
    }

    private Storage findStorage(Book book) {
        List<Storage> storages = storageDao.getAll();
        for (Storage storage : storages) {
            if (storage.getBook().equals(book)) {
                return storage;
            }
        }
        throw new ServiceException("Not request");
    }

    @Override
    public <T> void set(List<T> list) {
//        log.info("Десериализация Storage");
//        list.stream().map(e -> (Book) e).forEach(storageDao::add);
    }
}
