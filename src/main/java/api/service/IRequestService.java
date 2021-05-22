package api.service;

import model.book.Book;
import model.storage.Request;

public interface IRequestService {

    void addRequest(Book book);

    Boolean isRequest(Book book);

    void changeCountRequest(Book book);

    void deleteRequest(Request request);

    Request getRequest(Book book);


    void print();
}
