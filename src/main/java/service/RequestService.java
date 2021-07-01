package service;

import api.dao.IRequestDao;
import api.service.IRequestService;
import dao.RequestDao;
import exceptions.DaoException;
import model.Book;
import model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    public boolean save(Book book) {
        try {
            log.info("Change_Count_Request_BY_Book: {}, id:{}", book.getNameBook(), book.getId());
            boolean isReq;
            if (isRequest(book)) {
                changeCountRequest(book);
                isReq = true;
            } else {
                idRequest++;
                isReq = requestDao.save(new Request(idRequest, book));
            }
            return isReq;
        } catch (DaoException e) {
            log.error("addRequest book-id: {}, {}", book, book.getId());
            throw e;
        }
    }

    @Override
    public Boolean isRequest(Book book) {
        return requestDao.isBook(book);
    }

    @Override
    public void changeCountRequest(Book book) {
        try {
            log.info("Change_Count_Request_BY_Book: {}, id:{}", book.getNameBook(), book.getId());
            Request request = requestDao.changeCountRequest(book);
            Integer index = requestDao.indexRequest(request);
            request.setCountRequest(request.getCountRequest() + 1);
            requestDao.set(index, request);
        } catch (DaoException e) {
            log.error("changeCountRequest book-id: {}, {}", book, book.getId());
            throw e;

        }
    }

    @Override
    public Request get(Book book) {
        try {
            log.info("Get_Request_BY_Book: {}, id:{}", book.getNameBook(), book.getId());
            return requestDao.get(book);
        } catch (DaoException e) {
            log.error("getRequest book-id: {}, {}", book, book.getId());
            throw e;
        }
    }

    @Override
    public void delete(Request request) {
        requestDao.delete(request);
    }

    @Override
    public List<Request> getAll() {
        return new ArrayList<>(requestDao.getAll());
    }

    @Override
    public List<Request> sortRequest(TypeSortRequest typeSortRequest) {
        return requestDao.getAll().stream().sorted(comparator(typeSortRequest)).collect(Collectors.toList());

    }

    private Comparator<Request> comparator(TypeSortRequest typeSortRequest) {
        if (typeSortRequest.equals(TypeSortRequest.NAME_BOOK)) {
            return Comparator.comparing(o -> o.getBook().getNameBook());
        } else {
            return Comparator.comparing(Request::getCountRequest);
        }
    }

    @Override
    public <T> void set(List<T> list) {
        if (list.size() > 0) {
            log.info("Десериализация Request");
            Request request = (Request) list.get(list.size() - 1);
            idRequest = request.getId();
            list.stream().map(e -> (Request) e).forEach(requestDao::save);
        }
    }
}
