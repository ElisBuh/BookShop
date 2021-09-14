package com.senla.controller;

import com.senla.api.service.IBookService;
import com.senla.api.service.IOrderService;
import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.StatusOrder;
import com.senla.model.dto.OrderDto;
import com.senla.service.TypeSortOrder;
import com.senla.util.utilits.DateTimeUtil;
import com.senla.util.utilits.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final IOrderService orderService;
    private final IBookService bookService;
    private final Mapper mapper;

    public OrderController(IOrderService orderService, IBookService bookService, Mapper mapper) {
        this.orderService = orderService;
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody OrderDto orderDto) {
        log.info("create");
        Book book = bookService.get(orderDto.getIdBook());
        orderService.creat(orderDto.getNameClient(), book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> cancel(@PathVariable(name = "id") Integer id) {
        log.info("cancel");
        orderService.cancel(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> read(@PathVariable(name = "id") Integer id) {
        log.info("read");
        Order order = orderService.getOrder(id);
        OrderDto orderDto = mapper.convertOrderToOrderDto(order);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> readAll(@RequestParam(name = "pageNumber") int pageNumber,
                                                  @RequestParam(name = "pageSize") int pageSize,
                                                  @RequestParam(name = "typeSort", defaultValue = "not") String typeSort,
                                                  @RequestParam(name = "dateStart", required = false) String dateStart,
                                                  @RequestParam(name = "dateEnd", required = false) String dateEnd) {
        if (typeSort.equals("not")) {
            log.info("readAll");
            List<Order> orders = orderService.getAll(pageNumber, pageSize);
            List<OrderDto> orderDtoList = Mapper.convertList(orders, mapper::convertOrderToOrderDto);
            return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
        } else if (typeSort.equals("orderComplete")) {
            log.info("listOrderCompleteForPeriodForTime");
            LocalDate localDateStart = DateTimeUtil.stringToLocalDate(dateStart);
            LocalDate localDateEnd = DateTimeUtil.stringToLocalDate(dateEnd);
            List<Order> orders = orderService.listOrderCompleteForPeriodForTime(pageNumber, pageSize, localDateStart, localDateEnd);
            List<OrderDto> orderDtoList = Mapper.convertList(orders, mapper::convertOrderToOrderDto);
            return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
        } else {
            log.info("readAllSort");
            TypeSortOrder typeSortOrder = TypeSortOrder.valueOf(typeSort.toUpperCase(Locale.ROOT));
            List<Order> orders = orderService.listSortOrder(pageNumber, pageSize, typeSortOrder);
            List<OrderDto> orderDtoList = Mapper.convertList(orders, mapper::convertOrderToOrderDto);
            return new ResponseEntity<>(orderDtoList, HttpStatus.OK);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        log.info("delete");
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/status")
    public ResponseEntity<?> changeStatus(@RequestParam(name = "id") Integer id,
                                          @RequestParam(name = "status") String status) {
        log.info("changeStatus");
        StatusOrder statusOrder = StatusOrder.valueOf(status.toUpperCase(Locale.ROOT));
        orderService.changeStatusOrder(id, statusOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/countComplete")
    public ResponseEntity<Integer> countCompleteOrders(@RequestParam(name = "dateStart") String dateStart,
                                                       @RequestParam(name = "dateEnd") String dateEnd) {
        log.info("countCompleteOrders");
        LocalDate localDateStart = DateTimeUtil.stringToLocalDate(dateStart);
        LocalDate localDateEnd = DateTimeUtil.stringToLocalDate(dateEnd);
        Integer count = orderService.countCompleteOrders(localDateStart, localDateEnd);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/amountOfMoney")
    public ResponseEntity<Integer> amountOfMoneyForPeriodForTime(@RequestParam(name = "dateStart") String dateStart,
                                                                 @RequestParam(name = "dateEnd") String dateEnd) {
        log.info("amountOfMoneyForPeriodForTime");
        LocalDate localDateStart = DateTimeUtil.stringToLocalDate(dateStart);
        LocalDate localDateEnd = DateTimeUtil.stringToLocalDate(dateEnd);
        Integer count = orderService.amountOfMoneyForPeriodForTime(localDateStart, localDateEnd);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


}
