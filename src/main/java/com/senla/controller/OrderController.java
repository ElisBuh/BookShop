package com.senla.controller;

import com.senla.api.service.IBookService;
import com.senla.api.service.IOrderService;
import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.StatusOrder;
import com.senla.service.TypeSortOrder;
import com.senla.util.utilits.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private IOrderService orderService;
    private IBookService bookService;

    public OrderController(IOrderService orderService, IBookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> create(@RequestParam(name = "nameClient") String nameClient,
                                    @RequestParam(name = "idBook") Integer idBook) {
        log.info("create");
        Book book = bookService.get(idBook);
        orderService.creat(nameClient, book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable(name = "id") Integer id) {
        log.info("cancel");
        orderService.cancel(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> read(@PathVariable(name = "id") Integer id) {
        log.info("read");
        Order order = orderService.getOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Order>> readAll() {
        log.info("readAll");
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        log.info("delete");
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list/listOrderComplete")
    public ResponseEntity<List<Order>> listOrderCompleteForPeriodForTime(@RequestParam(name = "dateStart") String dateStart,
                                                                         @RequestParam(name = "dateEnd") String dateEnd) {
        log.info("listOrderCompleteForPeriodForTime");
        LocalDate localDateStart = DateTimeUtil.stringToLocalDate(dateStart);
        LocalDate localDateEnd = DateTimeUtil.stringToLocalDate(dateEnd);
        List<Order> orders = orderService.listOrderCompleteForPeriodForTime(localDateStart, localDateEnd);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/changeStatus")
    public ResponseEntity<?> changeStatus(@RequestParam(name = "id") Integer id,
                                          @RequestParam(name = "status") String status) {
        log.info("changeStatus");
        StatusOrder statusOrder = StatusOrder.valueOf(status.toUpperCase(Locale.ROOT));
        orderService.changeStatusOrder(id, statusOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list/countComplete")
    public ResponseEntity<Integer> countCompleteOrders(@RequestParam(name = "dateStart") String dateStart,
                                                       @RequestParam(name = "dateEnd") String dateEnd) {
        log.info("countCompleteOrders");
        LocalDate localDateStart = DateTimeUtil.stringToLocalDate(dateStart);
        LocalDate localDateEnd = DateTimeUtil.stringToLocalDate(dateEnd);
        Integer count = orderService.countCompleteOrders(localDateStart, localDateEnd);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/list/{typeSortOrder}")
    public ResponseEntity<List<Order>> allBooksSort(@PathVariable(name = "typeSortOrder") String typeSort) {
        log.info("allBooksSort");
        TypeSortOrder typeSortOrder = TypeSortOrder.valueOf(typeSort.toUpperCase(Locale.ROOT));
        return new ResponseEntity<>(orderService.listSortOrder(typeSortOrder), HttpStatus.OK);
    }

    @GetMapping("/list/amountOfMoney")
    public ResponseEntity<Integer> amountOfMoneyForPeriodForTime(@RequestParam(name = "dateStart") String dateStart,
                                                                 @RequestParam(name = "dateEnd") String dateEnd) {
        log.info("amountOfMoneyForPeriodForTime");
        LocalDate localDateStart = DateTimeUtil.stringToLocalDate(dateStart);
        LocalDate localDateEnd = DateTimeUtil.stringToLocalDate(dateEnd);
        Integer count = orderService.amountOfMoneyForPeriodForTime(localDateStart, localDateEnd);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


}
