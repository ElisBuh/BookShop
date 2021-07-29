package com.senla.dao;

import com.senla.api.dao.IRequestDao;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.model.StatusBook;
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
public class RequestDao implements IRequestDao {
    private static final Logger log = LoggerFactory.getLogger(RequestDao.class);

    private static final String SAVE_REQUEST_QUERY = "INSERT INTO requests(book_id, count_request) VALUES(?,?)";
    private static final String GET_ALL_REQUESTS_QUERY = "SELECT * FROM requests,books WHERE books.id=requests.book_id";
    private static final String SET_REQUEST_QUERY = "UPDATE requests SET count_request = ? WHERE id = ?";
    private static final String DELETE_REQUEST_QUERY = "DELETE FROM requests WHERE id=?";
    private static final String GET_REQUEST_QUERY = "SELECT * FROM requests,books WHERE books.id=requests.book_id And requests.book_id=?";

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
    public boolean save(Request request) {
        log.info("Save Request: {} To BD", request.toString());
        try (PreparedStatement statement = connection.prepareStatement(SAVE_REQUEST_QUERY)) {
            statement.setInt(1, request.getBook().getId());
            statement.setInt(2, request.getCountRequest());
            statement.execute();
            return true;
        } catch (SQLException e) {
            log.error("RequestDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Boolean isBook(Book book) {
        log.info("isBook-RequestDao");
        try {
            return getAll().stream()
                    .anyMatch(request -> request.getBook().getId() == book.getId());
        } catch (DaoException e) {
            log.error("isBook-id: {}, {}", book, book.getId());
            throw new DaoException(e);
        }
    }

    @Override
    public List<Request> getAll() {
        log.info("getAll-RequestDao");
        List<Request> requestList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_REQUESTS_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                requestList.add(new Request(resultSet.getInt("id"), bookDTO.getBook(resultSet), resultSet.getInt("count_request")));
            }
            return requestList;
        } catch (SQLException e) {
            log.error("RequestDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void set(Request request) {
        log.info("Set_Request: {}", request.toString());
        try (PreparedStatement statement = connection.prepareStatement(SET_REQUEST_QUERY)) {
            statement.setInt(1, request.getCountRequest());
            statement.setInt(2, request.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error("RequestDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Request request) {
        log.info("Delete request: {}", request.toString());
        try (PreparedStatement statement = connection.prepareStatement(DELETE_REQUEST_QUERY)) {
            statement.setInt(1, request.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error("RequestDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Request get(Book book) {
        log.info("Get_Request, {}", book.toString());
        try (PreparedStatement statement = connection.prepareStatement(GET_REQUEST_QUERY)) {
            statement.setInt(1, book.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new Request(resultSet.getInt("id"), bookDTO.getBook(resultSet), resultSet.getInt("count_request"));
            }
            return null;
        } catch (SQLException e) {
            log.error("RequestDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }
}
