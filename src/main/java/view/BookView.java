package view;

import api.service.IBookService;
import api.view.GUIFactory;
import service.BookService;
import service.TypeSortBook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookView implements GUIFactory {
    private final IBookService iBookService = BookService.getBookServiceInstance();

    @Override
    public void menu() {
        ConsoleHelper.writeMessage(
                "Меню каталога книг:\n" +
                        "1 - Добавить книгу.\n" +
                        "2 - Список книг.\n" +
                        "3 - Список книг(сортированный).\n" +
                        "4 - Назад.\n" +
                        "0 - Выход.\n" +
                        "Сделайте выбор:");
        int point = ConsoleHelper.readInt();
        if (point == 1) {
            addBook();
        } else if (point == 2) {
            iBookService.getListBooks().forEach(System.out::println);
            menu();
        } else if (point == 3) {
            sortListBook();
        } else if (point == 4) {
            View.getViewInstance().menu();
        } else if (point == 0) {
            System.exit(0);
        } else {
            ConsoleHelper.writeMessage("Не вверный ввод. Повторите попытку.");
            menu();
        }

    }

    private void addBook() {
        ConsoleHelper.writeMessage("Ведите Название книги:");
        String nameBook = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Ведите Название автора:");
        String nameAuthor = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Ведите Стоимость книги:");
        int price = ConsoleHelper.readInt();
        ConsoleHelper.writeMessage("Ведите Дату, пример: \"22.05.2021\":");
        LocalDate date = LocalDate.parse(ConsoleHelper.readString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        iBookService.addBook(nameBook, nameAuthor, price, date);
        menu();
    }

//    private void listBook() {
//        ConsoleHelper.writeMessage("Список книг:");
//        iBookService.getListBooks().forEach(System.out::println);
//        menu();
//    }

    private void sortListBook() {
        TypeSortBook typeSortBook = null;
        ConsoleHelper.writeMessage(
                "Выбирите тип сортировки:\n" +
                        "1 - По имени книг.\n" +
                        "2 - По дате.\n" +
                        "3 - По цене.\n" +
                        "4 - По статусу.\n" +
                        "5 - Назад.\n" +
                        "6 - Выход.\n" +
                        "Сделайте выбор:");
        int point = ConsoleHelper.readInt();
        if (point == 1) {
            typeSortBook = TypeSortBook.NAME_BOOK;
        } else if (point == 2) {
            typeSortBook = TypeSortBook.DATE;
        } else if (point == 3) {
            typeSortBook = TypeSortBook.PRICE;
        } else if (point == 4) {
            typeSortBook = TypeSortBook.IN_STOCK;
        } else if (point == 5) {
            menu();
        } else if (point == 6) {
            System.exit(0);
        } else {
            ConsoleHelper.writeMessage("Не вверный ввод. Повторите попытку.");
            sortListBook();
        }
        iBookService.listSortBooks(typeSortBook).forEach(System.out::println);
        menu();
    }

}
