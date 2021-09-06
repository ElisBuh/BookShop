package com.senla.controller;

import com.senla.api.service.IBookService;
import com.senla.api.service.IStorageService;
import com.senla.model.Request;
import com.senla.model.Storage;
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

import java.util.List;

@RestController
@RequestMapping(value = "/storage")
public class StorageController {
    private static final Logger log = LoggerFactory.getLogger(StorageController.class);

    private IStorageService storageService;
    private IBookService bookService;

    public StorageController(IStorageService storageService, IBookService bookService) {
        this.storageService = storageService;
        this.bookService = bookService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> create(@RequestParam(name = "idBook") Integer idBook,
                                    @RequestParam(name = "date") String date) {
        log.info("create");
        storageService.add(bookService.get(idBook), DateTimeUtil.stringToLocalDate(date));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        log.info("delete");
        storageService.delete(bookService.get(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Storage>> readAll() {
        log.info("readAll");
        return new ResponseEntity<>(storageService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/bookNotSell")
    public ResponseEntity<List<Storage>> bookNotSellMoreNmonth() {
        log.info("readAll");
        return new ResponseEntity<>(storageService.bookNotSellMoreNmonth(), HttpStatus.OK);
    }
}
