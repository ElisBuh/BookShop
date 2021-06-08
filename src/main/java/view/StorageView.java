package view;

import api.service.IBookService;
import api.service.IStorageService;
import api.view.GUIFactory;
import service.BookService;
import service.StorageService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StorageView implements GUIFactory {
    private final IStorageService iStorageService = StorageService.getStorageServiceInstance();
    private final IBookService iBookService = BookService.getBookServiceInstance();

    @Override
    public void menu() {
        ConsoleHelper.writeMessage(
                "Меню каталога книг:\n" +
                        "1 - Добавить книгу на склад.\n" +
                        "2 - Удалить книгу со склада.\n" +
                        "3 - Список книг на складе.\n" +
                        "4 - Книги на складе более 6 месяцев.\n" +
                        "5 - Назад.\n" +
                        "0 - Выход.\n" +
                        "Сделайте выбор:");
        int point = ConsoleHelper.readInt();
        if (point == 1) {
            addBookToStorage();
        } else if (point == 2) {
            deleteBookFromStorage();
        } else if (point == 3) {
            iStorageService.getStorageBooks().forEach(System.out::println);
            menu();
        } else if (point == 4) {
            iStorageService.printBookNotSellMoreSixMonth().forEach(System.out::println);
            menu();
        } else if (point == 5) {
            View.getViewInstance().menu();
        } else if (point == 0) {
            System.exit(0);
        } else {
            ConsoleHelper.writeMessage("Не вверный ввод. Повторите попытку.");
            menu();
        }
    }

    private void addBookToStorage() {
        ConsoleHelper.writeMessage("Введите Id книги которые хотите добавить на склад из каталога:");
        int id = ConsoleHelper.readInt();
        ConsoleHelper.writeMessage("Ведите Дату добавления книги, пример: \"22.05.2021\":");
        LocalDate date = LocalDate.parse(ConsoleHelper.readString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        boolean isMessage = iStorageService.addBook(iBookService.getBook(id), date);

        if (isMessage) {
            ConsoleHelper.writeMessage("Книга добавлена на склад.");
        } else {
            ConsoleHelper.writeMessage("Книга уже добавлена на склад.");
        }
        menu();
    }

    private void deleteBookFromStorage() {
        ConsoleHelper.writeMessage("Введите Id книги которые хотите удалить со склад:");
        int id = ConsoleHelper.readInt();
        boolean isMessage = iStorageService.deleteBook(iBookService.getBook(id));
        if (isMessage) {
            ConsoleHelper.writeMessage("Книга удалена со склада.");
        }
        menu();
    }

}
