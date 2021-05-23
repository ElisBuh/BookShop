
import api.service.IBookService;
import api.service.IOrderService;
import api.service.IRequestService;
import api.service.IStorageService;
import model.order.StatusOrder;
import service.BookService;
import service.OrderService;
import service.RequestService;
import service.StorageService;


import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {
        IBookService book =  new BookService();
        IRequestService request = new RequestService();
        IStorageService storageService = new StorageService(request);
        book.addBook("War and Peace","Leo Tolstoy",25, LocalDate.of(2001,05,25));
        book.addBook("War and Peace2","Leo Tolstoy",38, LocalDate.of(2001,05,25));
        book.addBook("War and Peace3","Leo Tolstoy",52, LocalDate.of(2001,05,25));
        book.addBook("War and Peace4","Leo Tolstoy",55, LocalDate.of(2001,05,25));
        book.addBook("War and Peace5","Leo Tolstoy",21, LocalDate.of(2001,05,25));
        book.addBook("War and Peace6","Leo Tolstoy",26, LocalDate.of(2001,05,25));

        storageService.addBook(book.getBook(1));
        storageService.addBook(book.getBook(3));
        storageService.addBook(book.getBook(6));


        book.print();
        System.out.println("");
        storageService.printStorageBook();

        storageService.deleteBook(book.getBook(3));
        System.out.println("");
        book.print();

        IOrderService order = new OrderService(request);
        order.creatOrder("Bill",book.getBook(1));
        order.creatOrder("Gill",book.getBook(2));
        order.creatOrder("Dill",book.getBook(2));
        order.creatOrder("Bill2",book.getBook(3));
        order.creatOrder("Bill2",book.getBook(3));
        order.creatOrder("Bill3",book.getBook(6));
        order.creatOrder("Bill4",book.getBook(5));
        order.creatOrder("Bill5",book.getBook(6));

//        storageService.addBook(book.getBook(2));

//        order.cancelOrder(2);
        order.changeStatusOrder(3, StatusOrder.COMPLETED);
        request.addRequest(book.getBook(5));
        request.addRequest(book.getBook(5));

        System.out.println("");
        order.printOrder();
        System.out.println("");
        request.print();
        System.out.println("");
//        storageService.printStorageBook();





    }
}
