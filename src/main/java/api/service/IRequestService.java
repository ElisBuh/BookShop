package api.service;

import model.Book;
import model.Request;
import service.TypeSortRequest;

import java.util.List;

public interface IRequestService extends AbstractService {

    boolean save(Book book);

    Boolean isRequest(Book book);

    void changeCountRequest(Book book);

    void delete(Request request);

    Request get(Book book);

    List<Request> sortRequest(TypeSortRequest typeSortRequest);

    List<Request> getAll();
}
