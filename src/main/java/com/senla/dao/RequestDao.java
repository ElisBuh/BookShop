package com.senla.dao;

import com.senla.api.dao.IRequestDao;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.util.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class RequestDao implements IRequestDao {
    private static final Logger log = LoggerFactory.getLogger(RequestDao.class);

    private final List<Request> requests = new ArrayList<>();

    @Override
    public boolean save(Request request) {
        return requests.add(request);
    }

    @Override
    public Boolean isBook(Book book) {
        return requests.stream()
                .anyMatch(request -> request.getBook().equals(book));
    }

    @Override
    public Request changeCountRequest(Book book) {
        log.info("Change_count_By_Book: {}, id:{}", book.getNameBook(), book.getId());
        return requests.stream().filter(request -> request.getBook()
                .equals(book))
                .findFirst()
                .orElseThrow(() -> new DaoException("Book:" + book.getNameBook() + "-not found"));
    }

    @Override
    public List<Request> getAll() {
        return new ArrayList<>(requests);
    }

    @Override
    public Integer indexRequest(Request request) {
        return requests.indexOf(request);
    }

    @Override
    public void set(Integer index, Request request) {
        requests.set(index, request);
    }

    @Override
    public void delete(Request request) {
        requests.remove(request);
    }

    @Override
    public Request get(Book book) {
        return requests.stream().filter(request -> isBook(book))
                .findFirst()
                .orElseThrow(() -> new DaoException("Book:" + book + "-not found"));
    }
}
