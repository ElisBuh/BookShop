package com.senla.dao;

import com.senla.api.dao.IStorageDao;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.StatusBook;
import com.senla.model.Storage;
import com.senla.model.dto.BookDTO;
import com.senla.util.DataTimeUtil;
import com.senla.util.annotation.InjectByType;
import com.senla.util.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class StorageDao implements IStorageDao {
    private static final Logger log = LoggerFactory.getLogger(StorageDao.class);

    private static final String GET_ALL_STORAGE_QUERY = "SELECT * FROM storage,books WHERE books.id=storage.book_id";
    private static final String ADD_BOOK_TO_STORAGE_QUERY = "INSERT INTO storage(book_id) VALUES(?)";
    private static final String DELETE_BOOK_TO_STORAGE_QUERY = "DELETE FROM storage WHERE book_id=?";


    @InjectByType
    private ConnectPostgreSQL connectPostgreSQL;
    private Connection connection;
    @InjectByType
    private BookDTO bookDTO;

    @PostConstruct
    public void connection() {
        this.connection = connectPostgreSQL.conPostqres();
    }

    @Override
    public List<Storage> getAll() {
        log.info("getAll-StorageDao");
        List<Storage> storageList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_STORAGE_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                storageList.add(new Storage(resultSet.getLong("id"), bookDTO.getBook(resultSet)));
            }
            return storageList;
        } catch (SQLException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void add(Storage storage) {
        log.info("Save Storage: {} To BD", storage.toString());
        try (PreparedStatement statement = connection.prepareStatement(ADD_BOOK_TO_STORAGE_QUERY)) {
            statement.setInt(1, storage.getBook().getId());
            statement.execute();
        } catch (SQLException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Book book) {
        log.info("Delete_Book: {}-{}", book.getNameBook(), book.getId());
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_TO_STORAGE_QUERY)) {
            statement.setInt(1, book.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }
}
