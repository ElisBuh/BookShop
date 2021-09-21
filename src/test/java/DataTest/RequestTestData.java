package DataTest;

import com.senla.model.Request;

import java.util.Arrays;
import java.util.List;

public class RequestTestData {

    public static final int START_SEQ = 100000;

    public static final Request REQUEST_1 = new Request(START_SEQ, BookTestData.BOOK_2, 2);
    public static final Request REQUEST_2 = new Request(START_SEQ + 1, BookTestData.BOOK_7, 1);

    public static final List<Request> REQUEST_LIST = Arrays.asList(REQUEST_1, REQUEST_2);
    public static final List<Request> DELETE_REQUEST_LIST = List.of(REQUEST_1);

    public static final Request NEW_REQUEST = new Request(START_SEQ + 4, BookTestData.BOOK_9, 1);
    public static final Request UPDATE_REQUEST = new Request(START_SEQ, BookTestData.BOOK_2, 3);

}
