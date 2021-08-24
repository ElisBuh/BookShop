package com.senla.service;

import com.senla.api.dao.IBookDao;
import com.senla.api.dao.IStorageDao;
import com.senla.api.service.IRequestService;
import com.senla.api.service.IStorageService;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.StatusBook;
import com.senla.model.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StorageService implements IStorageService {
    private static final Logger log = LoggerFactory.getLogger(StorageService.class);

    @Value("${storageService.month}")
    private String month;
    @Value("${storageService.permissionChangeStatusRequest}")
    private String changeStatusRequest;

    private final IStorageDao storageDao;
    private final IBookDao bookDao;
    private final IRequestService requestService;

    public StorageService(IStorageDao storageDao, IBookDao bookDao, IRequestService requestService) {
        this.storageDao = storageDao;
        this.bookDao = bookDao;
        this.requestService = requestService;
    }

    @Override
    public boolean add(Book book, LocalDate localDate) {
        log.info("Add_BY_Book: {}, id:{}", book.getNameBook(), book.getId());
        try {
            book.setStatusBook(StatusBook.INSTOCK);
            book.setDateReceipt(localDate);
            storageDao.save(new Storage(book));
            bookDao.update(book);
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
            bookDao.update(book);
            return storageDao.delete(storageDao.findStorageOnBook(book));
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

}
