package com.senla.controller;

import com.senla.api.service.IBookService;
import com.senla.api.service.IStorageService;
import com.senla.model.Storage;
import com.senla.model.dto.StorageDTO;
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

@RestController
@RequestMapping(value = "/storages")
public class StorageController {
    private static final Logger log = LoggerFactory.getLogger(StorageController.class);

    private final IStorageService storageService;
    private final IBookService bookService;
    private final Mapper mapper;

    public StorageController(IStorageService storageService, IBookService bookService, Mapper mapper) {
        this.storageService = storageService;
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody StorageDTO storageDTO) {
        log.info("create");
        storageService.add(bookService.get(storageDTO.getIdBook()), storageDTO.getDateReceipt());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        log.info("delete");
        storageService.delete(bookService.get(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<StorageDTO>> readAll(@RequestParam(name = "pageNumber") int pageNumber,
                                                    @RequestParam(name = "pageSize") int pageSize,
                                                    @RequestParam(name = "typeSort", defaultValue = "not") String typeSort) {
        if (typeSort.equals("bookNotSell")) {
            log.info("bookNotSell");
            List<Storage> storages = storageService.bookNotSellMoreNmonth(pageNumber, pageSize);
            List<StorageDTO> storageDTOList = Mapper.convertList(storages, mapper::convertStorageToStorageDto);
            return new ResponseEntity<>(storageDTOList, HttpStatus.OK);
        } else {
            log.info("readAll");
            List<Storage> storages = storageService.getAll(pageNumber, pageSize);
            List<StorageDTO> storageDTOList = Mapper.convertList(storages, mapper::convertStorageToStorageDto);
            return new ResponseEntity<>(storageDTOList, HttpStatus.OK);
        }
    }

}
