package DataTest;

import com.senla.model.Book;
import com.senla.model.StatusBook;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class BookTestData {

    public static final int START_SEQ = 100000;

    public static final Book BOOK_1 = new Book(START_SEQ, "Война и Мир", "Лев Толстой", LocalDate.of(2020, 1, 30), 25, StatusBook.ABSENT);
    public static final Book BOOK_2 = new Book(START_SEQ + 1, "Горе от ума", "Александр Грибоедов", LocalDate.of(2020, 2, 12), 12, StatusBook.ABSENT);
    public static final Book BOOK_3 = new Book(START_SEQ + 2, "Великий Гэтсби", "Фрэнсис Скотт Фицджеральд", LocalDate.of(2020, 1, 15), 22, StatusBook.INSTOCK, LocalDate.of(2020, 10, 24));
    public static final Book BOOK_4 = new Book(START_SEQ + 3, "Идиот", "Федор Достоевский", LocalDate.of(2020, 5, 13), 41, StatusBook.ABSENT);
    public static final Book BOOK_5 = new Book(START_SEQ + 4, "Грозовой перевал", "Эмили Бронте", LocalDate.of(2020, 4, 1), 31, StatusBook.ABSENT);
    public static final Book BOOK_6 = new Book(START_SEQ + 5, "Маленький принц", "Сент-Экзюпери А.Д", LocalDate.of(2020, 6, 11), 38, StatusBook.INSTOCK, LocalDate.of(2020, 4, 19));
    public static final Book BOOK_7 = new Book(START_SEQ + 6, "Коллекционер", "Джон Фаулз", LocalDate.of(2020, 9, 25), 69, StatusBook.ABSENT);
    public static final Book BOOK_8 = new Book(START_SEQ + 7, "Мастер и Маргарита", "Михаил Булгаков", LocalDate.of(2020, 6, 6), 73, StatusBook.INSTOCK, LocalDate.of(2020, 5, 15));
    public static final Book BOOK_9 = new Book(START_SEQ + 8, "Дон Кихот", "Мигель де Сервантес", LocalDate.of(2020, 11, 24), 64, StatusBook.ABSENT);

    public static final List<Book> BOOK_LIST = Arrays.asList(BOOK_1, BOOK_2, BOOK_3, BOOK_4, BOOK_5, BOOK_6, BOOK_7, BOOK_8, BOOK_9);

    public static final Book NEW_BOOK = new Book(START_SEQ + 9, "newBook", "newAuthor", LocalDate.of(2000, 1, 1), 10, StatusBook.ABSENT);
    public static final Book NEW_BOOK_STORAGE = new Book(START_SEQ + 8, "Дон Кихот", "Мигель де Сервантес", LocalDate.of(2020, 11, 24), 64, StatusBook.INSTOCK, LocalDate.of(2020, 1, 20));

    public static final Book UPDATE_BOOK_9 = new Book(START_SEQ + 8, "Дон Кихот", "Мигель де Сервантес", LocalDate.of(2020, 11, 24), 64, StatusBook.INSTOCK);


}
