package service;

import api.dao.IRequestDao;
import api.service.IRequestService;
import api.service.IStorageService;
import dao.RequestDao;
import model.Book;
import model.Request;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RequestService implements IRequestService {
    private int idRequest;
    private final IRequestDao iRequestDao;

    private static volatile RequestService requestServiceInstance;

    private RequestService() {
        iRequestDao = RequestDao.getRequestDaoInstance();
    }

    public static RequestService getRequestServiceInstance() {
        if (requestServiceInstance == null) {
            requestServiceInstance = new RequestService();
        }
        return requestServiceInstance;
    }

    @Override
    public boolean addRequest(Book book) {
        boolean isReq;
        if (isRequest(book)) {
            changeCountRequest(book);
            isReq = true;
        } else {
            idRequest++;
            isReq = iRequestDao.add(new Request(idRequest, book));
        }
        return isReq;
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
    public List<Request> listRequests() {
        return new ArrayList<>(iRequestDao.getRequests());
    }

    @Override
    public List<Request> sortRequest(TypeSortRequest typeSortRequest) {
        return iRequestDao.getRequests().stream().sorted(comparator(typeSortRequest)).collect(Collectors.toList());

    }

    private Comparator<Request> comparator(TypeSortRequest typeSortRequest) {
        if (typeSortRequest.equals(TypeSortRequest.NAME_BOOK)) {
            return Comparator.comparing(o -> o.getBook().getNameBook());
        } else {
            return Comparator.comparing(Request::getCountRequest);
        }
    }
}
