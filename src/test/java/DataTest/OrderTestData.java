package DataTest;

import com.senla.model.Order;
import com.senla.model.StatusOrder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class OrderTestData {
    public static final int START_SEQ = 100000;

    public static final Order ORDER_1 = new Order(START_SEQ, "Роман", BookTestData.BOOK_3, 12, LocalDate.of(2021, 1, 24), StatusOrder.COMPLETED);
    public static final Order ORDER_2 = new Order(START_SEQ + 1, "Саша", BookTestData.BOOK_8, 69, StatusOrder.NEW);
    public static final Order ORDER_3 = new Order(START_SEQ + 2, "Ира", BookTestData.BOOK_6, 31, StatusOrder.CANCEL);

    public static final List<Order> ORDER_LIST = Arrays.asList(ORDER_1, ORDER_2, ORDER_3);
    public static final List<Order> ORDER_LIST_DELETE = Arrays.asList(ORDER_1, ORDER_3);

    public static final Order NEW_ORDER = new Order(START_SEQ + 3, "newClient", BookTestData.BOOK_8, 73, StatusOrder.NEW);

    public static final Order CANCEL_ORDER = new Order(START_SEQ + 1, "Саша", BookTestData.BOOK_8, 69, StatusOrder.CANCEL);

    public static final Order COMPLETED_ORDER = new Order(START_SEQ + 1, "Саша", BookTestData.BOOK_8, 69, LocalDate.now(), StatusOrder.COMPLETED);
}
