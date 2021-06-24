package api.service;

import model.Book;
import model.Request;
import service.TypeSortRequest;

import java.util.List;

public interface IRequestService extends AbstractService {

    boolean addRequest(Book book);

    Boolean isRequest(Book book);

    void changeCountRequest(Book book);

    void deleteRequest(Request request);

    Request getRequest(Book book);

    List<Request> sortRequest(TypeSortRequest typeSortRequest);

    List<Request> listRequests();
}
