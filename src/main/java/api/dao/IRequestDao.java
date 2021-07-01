package api.dao;

import model.Book;
import model.Request;

import java.util.List;

public interface IRequestDao {

    boolean save(Request request);

    Boolean isBook(Book book);

    Request changeCountRequest(Book book);

    void set(Integer index, Request request);

    Integer indexRequest(Request request);

    void delete(Request request);

    Request get(Book book);

    List<Request> getAll();

}
