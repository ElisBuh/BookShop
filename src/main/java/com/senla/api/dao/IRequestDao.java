package com.senla.api.dao;

import com.senla.model.Book;
import com.senla.model.Request;

import java.util.List;

public interface IRequestDao {

    boolean save(Request request);

    Boolean isBook(Book book);

    void set(Request request);

    void delete(Request request);

    Request get(Book book);

    List<Request> getAll();

}
