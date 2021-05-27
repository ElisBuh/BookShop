package service;


import api.dao.IBookDao;
import api.service.IBookService;
import dao.BookDao;
import model.Book;
import model.StatusBook;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class BookService implements IBookService {
    private int id;
    private IBookDao iBookDao = new BookDao();

    @Override
    public void addBook(String nameBook, String nameAuthor, int price, LocalDate date) {
        id++;
        iBookDao.addBook(new Book(id, nameBook, nameAuthor, date, price, StatusBook.ABSENT));
    }

    @Override
    public List<Book> ListSortBooks(TypeSortBook typeSortBook) {
        return iBookDao.getBooks().stream().sorted(comparator(typeSortBook)).collect(Collectors.toList());
    }

    private Comparator<Book> comparator(TypeSortBook typeSortBook){
        if (typeSortBook.equals(TypeSortBook.NAME_BOOK)){
            return Comparator.comparing(Book::getNameBook);
        }else if (typeSortBook.equals(TypeSortBook.DATE)){
            return Comparator.comparing(Book::getDate);
        }else if (typeSortBook.equals(TypeSortBook.PRICE)){
            return Comparator.comparing(Book::getPrice);
        }else if (typeSortBook.equals(TypeSortBook.IN_STOCK)){
            return Comparator.comparing(Book::getStatusBook);
        }
        else return null;
    }

    @Override
    public Book getBook(int id) {
        return iBookDao.getBook(id);
    }

    @Override
    public List<Book> getListBooks() {
       return new ArrayList<>(iBookDao.getBooks());
    }

}
