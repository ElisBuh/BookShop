package com.senla.api.service;

import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.service.TypeSortRequest;

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
