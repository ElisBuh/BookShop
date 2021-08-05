package com.senla.dao.jdbc;

import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.mapper.BookMapper;
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
import java.util.ArrayList;
import java.util.List;

@Singleton
public class JdbcBookDao {
    private static final Logger log = LoggerFactory.getLogger(JdbcBookDao.class);

    private static final String SAVE_BOOK_QUERY = "INSERT INTO books(name_book, name_author, date, price, status_book) VALUES(?,?,?,?,?)";
    private static final String GET_BOOK_QUERY = "SELECT * FROM books WHERE id=?";
    private static final String DELETE_BOOK_QUERY = "DELETE FROM books WHERE id=?";
    private static final String GET_ALL_BOOKS_QUERY = "SELECT * FROM books";
    private static final String SET_BOOK_QUERY = """
            UPDATE books 
            SET name_book = ?,
            name_author = ?,
            date = ?,
            price = ?,
            status_book = ?,
            data_receipt = ?
            WHERE id = ? """;

    @InjectByType
    private ConnectPostgreSQL connectPostgreSQL;
    private Connection connection;
    @InjectByType
    private BookMapper bookMapper;

    @PostConstruct
    public void connection() {
        this.connection = connectPostgreSQL.conPostqres();
    }

//    @Override
    public void save(Book book) {
        log.info("Save Book: {} To BD", book.toString());
        try (PreparedStatement statement = connection.prepareStatement(SAVE_BOOK_QUERY)) {
            statement.setString(1, book.getNameBook());
            statement.setString(2, book.getNameAuthor());
            statement.setTimestamp(3, DataTimeUtil.localDataToTimestamp(book.getDate()));
            statement.setInt(4, book.getPrice());
            statement.setString(5, book.getStatusBook().name());
            statement.execute();
        } catch (SQLException e) {
            log.error("BookDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

//    @Override
    public void set(Book book) {
        log.info("Set Book: {} To BD", book.toString());
        try (PreparedStatement statement = connection.prepareStatement(SET_BOOK_QUERY)) {
            statement.setString(1, book.getNameBook());
            statement.setString(2, book.getNameAuthor());
            statement.setTimestamp(3, DataTimeUtil.localDataToTimestamp(book.getDate()));
            statement.setInt(4, book.getPrice());
            statement.setString(5, book.getStatusBook().name());
            statement.setTimestamp(6, DataTimeUtil.localDataToTimestamp(book.getDateReceipt()));
            statement.setLong(7, book.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error("BookDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

//    @Override
    public Book get(int id) {
        log.info("Get_Book_By_ID: {}", id);
        try (PreparedStatement statement = connection.prepareStatement(GET_BOOK_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return bookMapper.getBook(resultSet);
            }
            return null;
        } catch (SQLException e) {
            log.error("BookDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }


//    @Override
    public boolean delete(Book book) {
        log.info("Delete book: {}", book.toString());
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_QUERY)) {
            statement.setLong(1, book.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            log.error("BookDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }

//    @Override
    public List<Book> getAll() {
        log.info("getAll-BookDao");
        List<Book> bookList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_BOOKS_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookList.add(bookMapper.getBook(resultSet));
            }
            return bookList;
        } catch (SQLException e) {
            log.error("BookDao sql-exception {}", e.getMessage());
            throw new DaoException(e);
        }
    }
}
