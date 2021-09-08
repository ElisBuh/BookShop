package com.senla.util.utilits;

import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.Request;
import com.senla.model.Storage;
import com.senla.model.dto.BookDTO;
import com.senla.model.dto.OrderDTO;
import com.senla.model.dto.RequestDTO;
import com.senla.model.dto.StorageDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mapper {

    private final ModelMapper modelMapper;


    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public BookDTO convertBookToBookDto(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public Book convertBookDtoToBook(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public OrderDTO convertOrderToOrderDto(Order order) {
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setBookDTO(convertBookToBookDto(order.getBook()));
        return orderDTO;
    }

    public Order convertOrderDtoToOrder(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    public RequestDTO convertRequestToRequestDto(Request request) {
        RequestDTO requestDTO = modelMapper.map(request, RequestDTO.class);
        requestDTO.setBookDTO(convertBookToBookDto(request.getBook()));
        return requestDTO;
    }

    public StorageDTO convertStorageToStorageDto(Storage storage) {
        StorageDTO storageDTO = modelMapper.map(storage, StorageDTO.class);
        storageDTO.setBookDTO(convertBookToBookDto(storage.getBook()));
        return storageDTO;
    }

    public static <R, E> List<R> convertList(List<E> list, Function<E, R> converter) {
        return list.stream().map(converter).collect(Collectors.toList());
    }


//    public <T,E> T convertEntityToDto(E e, Class<?> aClass){
//        return (T) modelMapper.map(e,aClass);
//    }
//    public <T,E> T convertDtoToEntity(E e, Class<?> aclass){
//        return (T) modelMapper.map(e,aclass);
//    }
}
