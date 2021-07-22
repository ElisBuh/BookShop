package com.senla.dao;

import com.senla.api.dao.IRequestDao;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.model.StatusBook;
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

    private static String SAVE_REQUEST_QUERY = "INSERT INTO requests(book_id, count_request) VALUES(?,?)";
    private static String GET_ALL_REQUESTS_QUERY = "SELECT * FROM requests,books WHERE books.id=requests.book_id";
    private static String SET_REQUEST_QUERY = "UPDATE requests SET count_request = ? WHERE id = ?";
    private static String DELETE_REQUEST_QUERY = "DELETE FROM requests WHERE id=?";
    private static String GET_REQUEST_QUERY = "SELECT * FROM requests,books WHERE books.id=requests.book_id And requests.book_id=?";

    @InjectByType
    private ConnectPostgreSQL connectPostgreSQL;
    private Connection connection;

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
        } catch (NullPointerException e) {
            log.error(e.toString());
            throw new DaoException(e);
        } catch (SQLException e) {
            log.error("RequestDao sql-exception {}", e.getMessage());
            System.out.println("Ошибка в БД");
        }
        return false;
    }

    @Override
    public Boolean isBook(Book book) {
        log.info("isBook-RequestDao");
        try {
            return getAll().stream()
                    .anyMatch(request -> request.getBook().getId() == book.getId());
        } catch (DaoException e) {
            log.error("isBook-id: {}, {}", book, book.getId());
            throw e;
        }
    }

    @Override
    public List<Request> getAll() {
        log.info("getAll-RequestDao");
        List<Request> requestList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_REQUESTS_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                requestList.add(new Request(resultSet.getInt("id"), getBook(resultSet), resultSet.getInt("count_request")));
            }
            return requestList;
        } catch (NullPointerException e) {
            log.error(e.toString());
            throw new DaoException(e);
        } catch (SQLException e) {
            log.error("RequestDao sql-exception {}", e.getMessage());
            System.out.println("Ошибка в БД");
        }
        return requestList;
    }

    @Override
    public void set(Request request) {
        log.info("Set_Request: {}", request.toString());
        try (PreparedStatement statement = connection.prepareStatement(SET_REQUEST_QUERY)) {
            statement.setInt(1, request.getCountRequest());
            statement.setInt(2, request.getId());
            statement.execute();
        } catch (NullPointerException e) {
            log.error(e.toString());
            throw new DaoException(e);
        } catch (SQLException e) {
            log.error("RequestDao sql-exception {}", e.getMessage());
            System.out.println("Ошибка в БД");
        }
    }

    @Override
    public void delete(Request request) {
        log.info("Delete request: {}", request.toString());
        try (PreparedStatement statement = connection.prepareStatement(DELETE_REQUEST_QUERY)) {
            statement.setInt(1, request.getId());
            statement.execute();
        } catch (NullPointerException e) {
            log.error(e.toString());
            throw new DaoException(e);
        } catch (SQLException e) {
            log.error("RequestDao sql-exception {}", e.getMessage());
            System.out.println("Ошибка в БД");
        }
    }

    @Override
    public Request get(Book book) {
        log.info("Get_Request, {}", book.toString());
        try (PreparedStatement statement = connection.prepareStatement(GET_REQUEST_QUERY)) {
            statement.setInt(1, book.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new Request(resultSet.getInt("id"), getBook(resultSet), resultSet.getInt("count_request"));
            }
        } catch (NullPointerException e) {
            log.error(e.toString());
            throw new DaoException(e);
        } catch (SQLException e) {
            log.error("RequestDao sql-exception {}", e.getMessage());
            System.out.println("Ошибка в БД");
        }
        return null;
    }

    private Book getBook(ResultSet resultSet) throws SQLException {
        int idBook = resultSet.getInt("book_id");
        String nameBook = resultSet.getString("name_book");
        String nameAuthor = resultSet.getString("name_author");
        LocalDate date = DataTimeUtil.timestampToLocalDate(resultSet.getTimestamp("date"));
        int price = resultSet.getInt("price");
        StatusBook statusBook = StatusBook.valueOf(resultSet.getString("status_book"));
        return new Book(idBook, nameBook, nameAuthor, date, price, statusBook);
    }
}
