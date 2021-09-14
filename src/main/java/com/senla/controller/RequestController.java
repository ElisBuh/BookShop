package com.senla.controller;

import com.senla.api.service.IBookService;
import com.senla.api.service.IRequestService;
import com.senla.model.Request;
import com.senla.model.dto.RequestDto;
import com.senla.service.TypeSortRequest;
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

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {
    private static final Logger log = LoggerFactory.getLogger(RequestController.class);

    private IRequestService requestService;
    private IBookService bookService;
    private final Mapper mapper;

    public RequestController(IRequestService requestService, IBookService bookService, Mapper mapper) {
        this.requestService = requestService;
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody RequestDto requestDto) {
        log.info("create");
        requestService.save(bookService.get(requestDto.getId()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        log.info("delete");
        requestService.delete(requestService.get(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<RequestDto>> readAll(@RequestParam(name = "pageNumber") int pageNumber,
                                                    @RequestParam(name = "pageSize") int pageSize,
                                                    @RequestParam(name = "typeSort", defaultValue = "not") String typeSort) {
        if (typeSort.equals("not")) {
            log.info("readAll");
            List<Request> requests = requestService.getAll(pageNumber, pageSize);
            List<RequestDto> requestDtoList = Mapper.convertList(requests, mapper::convertRequestToRequestDto);
            return new ResponseEntity<>(requestDtoList, HttpStatus.OK);
        } else {
            log.info("readAllSort");
            TypeSortRequest typeSortRequest = TypeSortRequest.valueOf(typeSort.toUpperCase(Locale.ROOT));
            List<Request> requests = requestService.sortRequest(pageNumber, pageSize, typeSortRequest);
            List<RequestDto> requestDtoList = Mapper.convertList(requests, mapper::convertRequestToRequestDto);
            return new ResponseEntity<>(requestDtoList, HttpStatus.OK);

        }

    }
}
