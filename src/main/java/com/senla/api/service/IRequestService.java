package com.senla.api.service;

import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.service.TypeSortRequest;

import java.util.List;

public interface IRequestService {

    boolean save(Book book);

    Boolean isRequest(Book book);

    void changeCountRequest(Book book);

    void delete(Request request);

    Request get(Integer id);

    List<Request> sortRequest(int pageNumber, int pageSize, TypeSortRequest typeSortRequest);

    List<Request> getAll(int pageNumber, int pageSize);

    Request findRequest(Book book);
}
