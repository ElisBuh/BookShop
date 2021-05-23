package service.bookSevvice;


import api.dao.IBookDao;
import api.service.IBookService;
import dao.BookDao;
import model.book.Book;
import model.book.StatusBook;


import java.time.LocalDate;
import java.util.Comparator;


public class BookService implements IBookService {
    private int id;
    private IBookDao iBookDao = new BookDao();

    @Override
    public void addBook(String nameBook, String nameAuthor, int price, LocalDate date) {
        id++;
        iBookDao.addBook(new Book(id, nameBook, nameAuthor, date, price, StatusBook.ABSENT));
    }

    @Override
    public void sortBooks(TypeSortBook typeSortBook) {
        if (typeSortBook.equals(TypeSortBook.NAME_BOOK)){
            iBookDao.getBooks().stream().sorted(Comparator.comparing(Book::getNameBook)).forEach(System.out::println);
        }else if (typeSortBook.equals(TypeSortBook.DATE)){
            iBookDao.getBooks().stream().sorted(Comparator.comparing(Book::getDate)).forEach(System.out::println);
        }else if (typeSortBook.equals(TypeSortBook.PRICE)){
            iBookDao.getBooks().stream().sorted(Comparator.comparing(Book::getPrice)).forEach(System.out::println);
        }else if (typeSortBook.equals(TypeSortBook.IN_STOCK)){
            iBookDao.getBooks().stream().sorted(Comparator.comparing(Book::getStatusBook)).forEach(System.out::println);
        }
    }

    @Override
    public Book getBook(int id) {
        return iBookDao.getBook(id);
    }

    @Override
    public void print() {
        iBookDao.print();
    }


}
