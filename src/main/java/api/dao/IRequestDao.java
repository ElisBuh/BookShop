package api.dao;

import model.book.Book;
import model.storage.Request;

public interface IRequestDao {

    void add(Request request);

    Boolean isBook(Book book);

    Request changeCountRequest(Book book);

    void setRequest(Integer index, Request request);

    Integer indexRequest(Request request);

    void deleteRequest(Request request);

    Request getRequest(Book book);

    void print();
}
