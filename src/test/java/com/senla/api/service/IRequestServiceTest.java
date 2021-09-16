package com.senla.api.service;

import DataTest.BookTestData;
import DataTest.RequestTestData;
import com.senla.exceptions.DaoException;
import com.senla.model.Request;
import com.senla.service.TypeSortRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.HsqldbDataConfig;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@ContextConfiguration(classes = HsqldbDataConfig.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Sql(scripts = "classpath:book_shop_initDB_hsqldb.sql")
@Sql(scripts = "classpath:book_shop_populateDB_hsqldb.sql")
public class IRequestServiceTest {

    @Autowired
    private IRequestService requestService;

    @Test
    public void save() {
        requestService.save(BookTestData.BOOK_9);
        Request newRequest = requestService.get(100004);
        assertEquals(newRequest, RequestTestData.NEW_REQUEST);
    }

    @Test
    public void changeCountRequest() {
        requestService.changeCountRequest(BookTestData.BOOK_2);
        Request request = requestService.get(100000);
        assertEquals(request, RequestTestData.UPDATE_REQUEST);
    }

    @Test
    public void delete() {
        requestService.delete(RequestTestData.REQUEST_2);
        List<Request> requestList = requestService.getAll(1, 10);
        assertEquals(requestList, RequestTestData.DELETE_REQUEST_LIST);
    }


    @Test
    public void get() {
        Request request = requestService.get(100001);
        assertEquals(request, RequestTestData.REQUEST_2);
    }

    @Test
    public void getNotRequest() {
        assertThrows(DaoException.class, () -> requestService.get(1));
    }

    @Test
    public void sortRequest() {
        assertEquals(
                requestService.sortRequest(1, 10, TypeSortRequest.NAME_BOOK),
                RequestTestData.REQUEST_LIST.stream()
                        .sorted(Comparator.comparing(o -> o.getBook().getNameBook()))
                        .collect(Collectors.toList()));
        assertEquals(
                requestService.sortRequest(1, 10, TypeSortRequest.COUNT_REQUEST),
                RequestTestData.REQUEST_LIST.stream()
                        .sorted(Comparator.comparing(Request::getCountRequest))
                        .collect(Collectors.toList()));
    }

    @Test
    public void getAll() {
        List<Request> requestList = requestService.getAll(1, 10);
        assertEquals(requestList, RequestTestData.REQUEST_LIST);
    }
}