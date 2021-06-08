package dao;

import api.dao.IRequestDao;
import model.Book;
import model.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestDao implements IRequestDao {

    private final List<Request> requests = new ArrayList<>();
    private static volatile RequestDao requestDaoInstance;

    private RequestDao() {
    }

    public static RequestDao getRequestDaoInstance() {
        if (requestDaoInstance == null) {
            requestDaoInstance = new RequestDao();
        }
        return requestDaoInstance;
    }

    @Override
    public boolean add(Request request) {
        return requests.add(request);
    }

    @Override
    public Boolean isBook(Book book) {
        return requests.stream().anyMatch(request -> request.getBook().equals(book));
    }

    @Override
    public Request changeCountRequest(Book book) {
        return requests.stream().filter(request -> request.getBook().equals(book)).findFirst().orElse(null);
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
        return requests.stream().filter(request -> isBook(book)).findFirst().orElse(null);
    }

}
