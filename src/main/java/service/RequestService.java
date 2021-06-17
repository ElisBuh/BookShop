package service;

import api.dao.IRequestDao;
import api.service.IRequestService;
import dao.RequestDao;
import model.Book;
import model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class RequestService implements IRequestService {
    private static final Logger log = LoggerFactory.getLogger(RequestService.class);

    private int idRequest;
    private final IRequestDao requestDao;

    private static volatile RequestService requestServiceInstance;

    private RequestService() {
        requestDao = RequestDao.getRequestDaoInstance();
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
            isReq = requestDao.add(new Request(idRequest, book));
        }
        return isReq;
    }

    @Override
    public Boolean isRequest(Book book) {
        return requestDao.isBook(book);
    }

    @Override
    public void changeCountRequest(Book book) {
        try {
        Request request = requestDao.changeCountRequest(book);
        Integer index = requestDao.indexRequest(request);
        request.setCountRequest(request.getCountRequest() + 1);
        requestDao.setRequest(index, request);
        }catch (NoSuchElementException e){
            log.error("changeCountRequest: {}, {}", book, e.toString());

        }
    }

    @Override
    public Request getRequest(Book book) {
        try {
        return requestDao.getRequest(book);
        }catch (NoSuchElementException e){
            log.error("getRequest: {}, {}", book, e.toString());
            return null;
        }
    }

    @Override
    public void deleteRequest(Request request) {
        requestDao.deleteRequest(request);
    }

    @Override
    public List<Request> listRequests() {
        return new ArrayList<>(requestDao.getRequests());
    }

    @Override
    public List<Request> sortRequest(TypeSortRequest typeSortRequest) {
        return requestDao.getRequests().stream().sorted(comparator(typeSortRequest)).collect(Collectors.toList());

    }

    private Comparator<Request> comparator(TypeSortRequest typeSortRequest) {
        if (typeSortRequest.equals(TypeSortRequest.NAME_BOOK)) {
            return Comparator.comparing(o -> o.getBook().getNameBook());
        } else {
            return Comparator.comparing(Request::getCountRequest);
        }
    }
}
