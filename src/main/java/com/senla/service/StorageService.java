package com.senla.service;

import com.senla.api.dao.IStorageDao;
import com.senla.api.service.IRequestService;
import com.senla.api.service.IStorageService;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.StatusBook;
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
    private IRequestService requestService;


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
                if (Boolean.parseBoolean(changeStatusRequest)){
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
//        int month = Integer.parseInt(Config.configProperties("storageService.month"));
        return storageDao.getAll().stream()
                .filter(book -> LocalDate.now().minusMonths(Integer.parseInt(month)).isAfter(book.getDateReceipt()))
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
