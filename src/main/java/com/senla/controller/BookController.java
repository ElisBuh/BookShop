package com.senla.controller;

import com.senla.api.service.IBookService;
import com.senla.model.Book;
import com.senla.util.utilits.Mapper;
import com.senla.model.dto.BookDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping(value = "/books")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final IBookService bookService;
    private final Mapper mapper;

    public BookController(IBookService bookService, Mapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody BookDto bookDto) {
        log.info("create");
        System.out.println(bookDto);
        Book book = mapper.convertBookDtoToBook(bookDto);
        System.out.println(book.toString());
        bookService.save(book.getNameBook(), book.getNameAuthor(), book.getPrice(), book.getDate());
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> readAll(@RequestParam(name = "pageNumber") int pageNumber,
                                                 @RequestParam(name = "pageSize") int pageSize,
                                                 @RequestParam(name = "typeSort", defaultValue = "not") String typeSort) {
        if (typeSort.equals("not")) {
            log.info("readAll");
            List<Book> books = bookService.getAll(pageNumber, pageSize);
            List<BookDto> bookDtoList = Mapper.convertList(books, mapper::convertBookToBookDto);
            return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
        } else {
            log.info("readAllSort");
            TypeSortBook typeSortBook = TypeSortBook.valueOf(typeSort.toUpperCase(Locale.ROOT));
            List<Book> books = bookService.listSortBooks(pageNumber, pageSize, typeSortBook);
            List<BookDto> bookDtoList = Mapper.convertList(books, mapper::convertBookToBookDto);
            return new ResponseEntity<>(bookDtoList, HttpStatus.OK);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> read(@PathVariable(name = "id") Integer id) {
        log.info("read");
        Book book = bookService.get(id);
        BookDto bookDto = mapper.convertBookToBookDto(book);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }
}
