package service;

import api.dao.IStorageDao;
import api.service.IRequestService;
import api.service.IStorageService;
import dao.StorageDao;
import model.Book;
import model.StatusBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public boolean addBook(Book book, LocalDate localDate) {
            if (storageDao.getBooks().contains(book)) {
                return false;
            }
            book.setStatusBook(StatusBook.INSTOCK);
            book.setDateReceipt(localDate);
            storageDao.addBook(book);
            if (requestService.isRequest(book)) {
                requestService.deleteRequest(requestService.getRequest(book));
            }
            return true;
    }

    @Override
    public boolean deleteBook(Book book) {
            book.setStatusBook(StatusBook.ABSENT);
            return storageDao.delete(book);


    }

    @Override
    public List<Book> printBookNotSellMoreSixMonth() {
        return storageDao.getBooks().stream()
                .filter(book -> LocalDate.now().minusMonths(6).isAfter(book.getDateReceipt()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getStorageBooks() {
        return new ArrayList<>(storageDao.getBooks());
    }

}
