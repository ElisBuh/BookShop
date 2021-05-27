package dao;

import api.dao.IRequestDao;
import model.Book;
import model.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestDao implements IRequestDao {

    private final List<Request> requests = new ArrayList<>();

    @Override
    public void add(Request request) {
        requests.add(request);
    }

    @Override
    public Boolean isBook(Book book) {
        for (Request request : requests) {
            if (request.getBook().equals(book)) return true;
        }
        return false;
    }

    @Override
    public Request changeCountRequest(Book book) {
        for (Request request : requests) {
            if (request.getBook().equals(book)) {
                return request;
            }
        }
        return null;
    }

    @Override
    public List<Request> getRequests() {
        return new ArrayList<>(requests);
    }

    @Override
    public Integer indexRequest(Request request) {
        return requests.indexOf(request);
    }

    @Override
    public void setRequest(Integer index, Request request) {
        requests.set(index, request);
    }

    @Override
    public void deleteRequest(Request request) {
        requests.remove(request);
    }

    @Override
    public Request getRequest(Book book) {
        for (Request request : requests) {
            if (isBook(book)) return request;
        }
        return null;
    }

}
