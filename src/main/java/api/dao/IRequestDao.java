package api.dao;

import model.Book;
import model.Request;

import java.util.List;

public interface IRequestDao extends AbstractDao {

    boolean add(Request request);

    Boolean isBook(Book book);

    Request changeCountRequest(Book book);

    void setRequest(Integer index, Request request);

    Integer indexRequest(Request request);

    void deleteRequest(Request request);

    Request getRequest(Book book);

    List<Request> getRequests();

}
