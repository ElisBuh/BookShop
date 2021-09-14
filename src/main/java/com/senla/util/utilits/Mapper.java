package com.senla.util.utilits;

import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.Request;
import com.senla.model.Storage;
import com.senla.model.dto.BookDto;
import com.senla.model.dto.OrderDto;
import com.senla.model.dto.RequestDto;
import com.senla.model.dto.StorageDto;
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


    public BookDto convertBookToBookDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public Book convertBookDtoToBook(BookDto bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public OrderDto convertOrderToOrderDto(Order order) {
        OrderDto orderDTO = modelMapper.map(order, OrderDto.class);
        orderDTO.setBookDTO(convertBookToBookDto(order.getBook()));
        return orderDTO;
    }

    public Order convertOrderDtoToOrder(OrderDto orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    public RequestDto convertRequestToRequestDto(Request request) {
        RequestDto requestDTO = modelMapper.map(request, RequestDto.class);
        requestDTO.setBookDTO(convertBookToBookDto(request.getBook()));
        return requestDTO;
    }

    public StorageDto convertStorageToStorageDto(Storage storage) {
        StorageDto storageDTO = modelMapper.map(storage, StorageDto.class);
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
