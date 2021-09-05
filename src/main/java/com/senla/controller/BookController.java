package com.senla.controller;

import com.senla.api.service.IBookService;
import com.senla.model.Book;
import com.senla.service.TypeSortBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping(value = "/book")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> create(@RequestBody Book book) {
        log.info("create");
        bookService.save(book.getNameBook(),book.getNameAuthor(),book.getPrice(),book.getDate());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list/{typeSortBook}")
    public ResponseEntity<List<Book>> allBooksSort(@PathVariable(name = "typeSortBook") String typeSort) {
        log.info("allBooksSort");
        TypeSortBook typeSortBook = TypeSortBook.valueOf(typeSort.toUpperCase(Locale.ROOT));
        return new ResponseEntity<>(bookService.listSortBooks(typeSortBook), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> readAll(){
        log.info("readAll");
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> read(@PathVariable(name = "id") Integer id) {
        log.info("read");
        Book book = bookService.get(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
