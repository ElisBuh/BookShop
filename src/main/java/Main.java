
import api.service.IBookService;
import api.service.IOrderService;
import api.service.IRequestService;
import api.service.IStorageService;
import model.StatusOrder;
import service.*;


import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {
        IBookService book =  new BookService();
        IRequestService request = new RequestService();
        IStorageService storageService = new StorageService(request);
        book.addBook("War and Peace","Leo Tolstoy",25, LocalDate.of(2001,5,25));
        book.addBook("War and Peace3","Leo Tolstoy1",38, LocalDate.of(2011,1,22));
        book.addBook("War and Peace1","Leo Tolstoy3",52, LocalDate.of(2041,2,15));
        book.addBook("War and Peace5","Leo Tolstoy2",55, LocalDate.of(2061,4,23));
        book.addBook("War and Peace4","Leo Tolstoy5",21, LocalDate.of(2012,10,1));
        book.addBook("War and Peace6","Leo Tolstoy4",26, LocalDate.of(2021,6,12));

        storageService.addBook(book.getBook(1),LocalDate.of(2021,5,12));
        storageService.addBook(book.getBook(3),LocalDate.of(2021,3,12));
        storageService.addBook(book.getBook(6),LocalDate.of(2020,1,12));


        book.getListBooks().forEach(System.out::println);
        System.out.println("");
        book.ListSortBooks(TypeSortBook.IN_STOCK).forEach(System.out::println);
        System.out.println("");
        storageService.printStorageBook();

        storageService.deleteBook(book.getBook(3));
        System.out.println("");
        book.getListBooks().forEach(System.out::println);

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
        order.changeStatusOrder(1, StatusOrder.COMPLETED);
        order.changeStatusOrder(2, StatusOrder.COMPLETED);
        request.addRequest(book.getBook(5));
        request.addRequest(book.getBook(3));

        System.out.println("");
        order.ListOrders().forEach(System.out::println);
        System.out.println("");
//        order.listSortOrder(TypeSortOrder.STATUS).forEach(System.out::println);
//        order.listOrderCompleteForPeriodForTime(LocalDate.of(2021,1,1),LocalDate.of(2022,1,1)).forEach(System.out::println);
        System.out.println(order.AmountOfMoneyForPeriodForTime(LocalDate.of(2021,1,1),LocalDate.of(2022,1,1)));
//        System.out.println(order.countCompleteOrders(LocalDate.of(2021,1,1),LocalDate.of(2022,1,1)));
//        System.out.println("");
        request.listRequests().forEach(System.out::println);
//        System.out.println("");
//        request.sortRequest(TypeSortRequest.NAME_BOOK).forEach(System.out::println);
//        storageService.printStorageBook();
//        storageService.printBookNotSellMoreSixMonth();





    }
}
