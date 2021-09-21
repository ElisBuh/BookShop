package DataTest;

import com.senla.model.Storage;

import java.util.Arrays;
import java.util.List;

public class StorageTestData {
    public static final int START_SEQ = 100000;

    public static final Storage STORAGE_1 = new Storage(START_SEQ, BookTestData.BOOK_3);
    public static final Storage STORAGE_2 = new Storage(START_SEQ + 1, BookTestData.BOOK_8);
    public static final Storage STORAGE_3 = new Storage(START_SEQ + 2, BookTestData.BOOK_6);

    public static final Storage NEW_STORAGE = new Storage(START_SEQ + 3, BookTestData.BOOK_9);

    public static final List<Storage> STORAGE_LIST = Arrays.asList(STORAGE_1, STORAGE_2, STORAGE_3);
    public static final List<Storage> DELETE_STORAGE_LIST = Arrays.asList(STORAGE_1, STORAGE_2);
    public static final List<Storage> ADD_STORAGE_LIST = Arrays.asList(STORAGE_1, STORAGE_2, STORAGE_3, NEW_STORAGE);
}
