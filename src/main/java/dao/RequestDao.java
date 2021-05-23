package dao;

import api.dao.IRequestDao;
import model.book.Book;
import model.storage.Request;

import java.util.ArrayList;

public class RequestDao implements IRequestDao {

    private final ArrayList<Request> requests = new ArrayList<>();

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
    public ArrayList<Request> getRequests() {
        return requests;
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

    @Override
    public void print() {
        requests.forEach(System.out::println);
    }
}
