package view;

import api.service.IBookService;
import api.service.IRequestService;
import api.view.GUIFactory;
import service.BookService;
import service.RequestService;
import service.TypeSortRequest;

public class RequestView implements GUIFactory {
    private final IRequestService iRequestService = RequestService.getRequestServiceInstance();
    private final IBookService iBookService = BookService.getBookServiceInstance();

    @Override
    public void menu() {
        ConsoleHelper.writeMessage(
                "Меню запросов книг:\n" +
                        "1 - Создать запрос на книгу.\n" +
                        "2 - Список запросов.\n" +
                        "3 - Список запросов по количеству.\n" +
                        "4 - Удалить запрос.\n" +
                        "5 - Назад.\n" +
                        "0 - Выход.\n" +
                        "Сделайте выбор:");
        int point = ConsoleHelper.readInt();
        if (point == 1) {
            addRequest();
        } else if (point == 2) {
            iRequestService.listRequests().forEach(System.out::println);
            menu();
        } else if (point == 3) {
            iRequestService.sortRequest(TypeSortRequest.COUNT_REQUEST).forEach(System.out::println);
            menu();
        } else if (point == 4) {
            deleteRequest();
        } else if (point == 5) {
            View.getViewInstance().menu();
        } else if (point == 0) {
            System.exit(0);
        } else {
            ConsoleHelper.writeMessage("Не вверный ввод. Повторите попытку.");
            menu();
        }
    }

    private void addRequest() {
        ConsoleHelper.writeMessage("Введите Id книги на которую хотите оставить запрос:");
        int id = ConsoleHelper.readInt();
        boolean isRequest = iRequestService.addRequest(iBookService.getBook(id));
        if (isRequest) {
            ConsoleHelper.writeMessage("Запрос добавлен.");
        } else ConsoleHelper.writeMessage("Книга есть на складе.");
        menu();
    }

    private void deleteRequest() {
        ConsoleHelper.writeMessage("Введите Id книги на которую хотите оставить запрос:");
        int id = ConsoleHelper.readInt();
        iRequestService.deleteRequest(iRequestService.getRequest(iBookService.getBook(id)));
        menu();
    }
}
