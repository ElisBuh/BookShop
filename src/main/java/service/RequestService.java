package service;

import api.dao.IRequestDao;
import api.service.IRequestService;
import dao.RequestDao;
import model.book.Book;
import model.storage.Request;

public class RequestService implements IRequestService {
    private int idRequest;
    private IRequestDao iRequestDao = new RequestDao();


    @Override
    public void addRequest(Book book) {
        if (isRequest(book)) {
            changeCountRequest(book);
        } else {
            idRequest++;
            iRequestDao.add(new Request(idRequest, book));
        }
    }

    @Override
    public Boolean isRequest(Book book) {
        return iRequestDao.isBook(book);
    }

    @Override
    public void changeCountRequest(Book book) {
        Request request = iRequestDao.changeCountRequest(book);
        Integer index = iRequestDao.indexRequest(request);
        request.setCountRequest(request.getCountRequest() + 1);
        iRequestDao.setRequest(index, request);
    }

    @Override
    public Request getRequest(Book book) {
        return iRequestDao.getRequest(book);
    }

    @Override
    public void deleteRequest(Request request) {
        iRequestDao.deleteRequest(request);
    }

    @Override
    public void print() {
        iRequestDao.print();
    }
}
