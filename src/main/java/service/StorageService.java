package service;

import api.dao.IStorageDao;
import api.service.IRequestService;
import api.service.IStorageService;
import dao.StorageDao;
import model.book.Book;
import model.book.StatusBook;

import java.time.LocalDate;
import java.util.Comparator;

public class StorageService implements IStorageService {

    private IStorageDao storageDao = new StorageDao();
    private IRequestService iRequestService;

    public StorageService(IRequestService iRequestService) {
        this.iRequestService = iRequestService;
    }

    @Override
    public void addBook(Book book,LocalDate localDate) {
        book.setStatusBook(StatusBook.INSTOCK);
        book.setDateReceipt(localDate);
        storageDao.addBook(book);
        if (iRequestService.isRequest(book)) {
            iRequestService.deleteRequest(iRequestService.getRequest(book));
        }
    }

    @Override
    public void deleteBook(Book book) {
        book.setStatusBook(StatusBook.ABSENT);
        storageDao.delete(book);
    }

    @Override
    public void printBookNotSellMoreSixMonth() {
        storageDao.getBooks().stream()
                .filter(book ->LocalDate.now().minusMonths(6).isAfter(book.getDateReceipt()))
                .forEach(System.out::println);
    }

    @Override
    public void printStorageBook() {
        storageDao.print();
    }

}
