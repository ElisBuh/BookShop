package service;

import api.dao.IStorageDao;
import api.service.IRequestService;
import api.service.IStorageService;
import dao.StorageDao;
import model.book.Book;
import model.book.StatusBook;

public class StorageService implements IStorageService {

    private IStorageDao storageDao = new StorageDao();
    private IRequestService iRequestService;

    public StorageService(IRequestService iRequestService) {
        this.iRequestService = iRequestService;
    }

    @Override
    public void addBook(Book book) {
        book.setStatusBook(StatusBook.INSTOCK);
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
    public void printStorageBook() {
        storageDao.print();
    }

}
