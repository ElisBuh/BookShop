package com.senla.service;

import com.senla.api.dao.IRequestDao;
import com.senla.api.service.IRequestService;
import com.senla.exceptions.DaoException;
import com.senla.exceptions.ServiceException;
import com.senla.model.Book;
import com.senla.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public final class RequestService implements IRequestService {
    private static final Logger log = LoggerFactory.getLogger(RequestService.class);
    private final IRequestDao requestDao;

    public RequestService(IRequestDao requestDao) {
        this.requestDao = requestDao;
    }


    @Override
    public boolean save(Book book) {
        try {
            log.info("Change_Count_Request_BY_Book: {}, id:{}", book.getNameBook(), book.getId());
            if (isRequest(book)) {
                changeCountRequest(book);
            } else {
                requestDao.save(new Request(book));
            }
            return true;
        } catch (DaoException e) {
            log.error("addRequest book-id: {}, {}", book, book.getId());
            throw e;
        }
    }

    @Override
    public Boolean isRequest(Book book) {
        log.info("isRequest book: {}", book.toString());
        try {
            return requestDao.getAll().stream().anyMatch(request -> request.getBook().equals(book));
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }
    }

    @Override
    public Request findRequest(Book book) {
        List<Request> requests = requestDao.getAll();
        for (Request request : requests) {
            if (request.getBook().equals(book)) {
                return request;
            }
        }
        throw new ServiceException("Not request");
    }

    @Override
    public void changeCountRequest(Book book) {
        log.info("Change_Count_Request_BY_Book: {}, id:{}", book.getNameBook(), book.getId());
        try {
            Request request = findRequest(book);
            request.setCountRequest(request.getCountRequest() + 1);
            requestDao.update(request);
        } catch (DaoException e) {
            log.error("changeCountRequest book-id: {}, {}", book, book.getId());
            throw e;

        }
    }

    @Override
    public Request get(Integer id) {
        try {
            log.info("Get_Request_id: {}", id);
            return requestDao.get(id);
        } catch (DaoException e) {
            log.error("getRequest book-id: {}", id);
            throw e;
        }
    }

    @Override
    public void delete(Request request) {
        try {
            log.info("Delete_Request: {}", request.toString());
            requestDao.delete(request);
        } catch (DaoException e) {
            log.error("deleteRequest: {}", request);
            throw e;
        }
    }

    @Override
    public List<Request> getAll() {
        log.info("get_All_Request");
        try {
            return new ArrayList<>(requestDao.getAll());
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }
    }

    @Override
    public List<Request> sortRequest(TypeSortRequest typeSortRequest) {
        log.info("SortRequest, {}", typeSortRequest);
        try {
            return requestDao.getAll().stream().sorted(comparator(typeSortRequest)).collect(Collectors.toList());
        } catch (DaoException e) {
            log.error(e.toString());
            throw e;
        }
    }

    private Comparator<Request> comparator(TypeSortRequest typeSortRequest) {
        if (typeSortRequest.equals(TypeSortRequest.NAME_BOOK)) {
            return Comparator.comparing(o -> o.getBook().getNameBook());
        } else {
            return Comparator.comparing(Request::getCountRequest);
        }
    }


    public IRequestDao requestDao() {
        return requestDao;
    }



}
