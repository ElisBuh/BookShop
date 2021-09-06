package com.senla.controller;

import com.senla.api.service.IBookService;
import com.senla.api.service.IRequestService;
import com.senla.model.Request;
import com.senla.service.TypeSortRequest;
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

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/request")
public class RequestController {
    private static final Logger log = LoggerFactory.getLogger(RequestController.class);

    private IRequestService requestService;
    private IBookService bookService;

    public RequestController(IRequestService requestService, IBookService bookService) {
        this.requestService = requestService;
        this.bookService = bookService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> create(@RequestParam(name = "idBook") Integer idBook) {
        log.info("create");
        requestService.save(bookService.get(idBook));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        log.info("delete");
        requestService.delete(requestService.get(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Request>> readAll() {
        log.info("readAll");
        return new ResponseEntity<>(requestService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{typeSort}")
    public ResponseEntity<List<Request>> readAllSort(@PathVariable(name = "typeSort") String typeSort) {
        log.info("readAllSort");
        TypeSortRequest typeSortRequest = TypeSortRequest.valueOf(typeSort.toUpperCase(Locale.ROOT));
        return new ResponseEntity<>(requestService.sortRequest(typeSortRequest), HttpStatus.OK);
    }
}
