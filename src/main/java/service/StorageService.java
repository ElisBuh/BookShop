package service;

import api.dao.IStorageDao;
import api.service.IRequestService;
import api.service.IStorageService;
import dao.StorageDao;
import model.Book;
import model.StatusBook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageService implements IStorageService {

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
        boolean isBook = false;
        try {
            if (storageDao.getBooks().contains(book)) {
                return false;
            }
            book.setStatusBook(StatusBook.INSTOCK);
            book.setDateReceipt(localDate);
            storageDao.addBook(book);
            if (requestService.isRequest(book)) {
                requestService.deleteRequest(requestService.getRequest(book));
            }
            isBook = true;
        } catch (NullPointerException e) {
            System.err.println("Такой книги нет");
        }
        return isBook;
    }

    @Override
    public boolean deleteBook(Book book) {
        boolean isBook = false;
        try {
            book.setStatusBook(StatusBook.ABSENT);
            storageDao.delete(book);
            isBook = true;
        } catch (NullPointerException e) {
            System.err.println("Такой книги нет");
        }
        return isBook;
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
