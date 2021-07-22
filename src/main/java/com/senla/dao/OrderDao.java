package com.senla.dao;

import com.senla.api.dao.IOrderDao;
import com.senla.exceptions.DaoException;
import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.StatusBook;
import com.senla.model.StatusOrder;
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
public class OrderDao implements IOrderDao {
    private static final Logger log = LoggerFactory.getLogger(OrderDao.class);

    private static final String SAVE_ORDER_QUERY = "INSERT INTO orders(NAME_CLIENT, BOOK_ID, COST, STATUS_ORDER) VALUES(?,?,?,?)";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM orders WHERE id=?";
    private static final String GET_ALL_ORDERS_QUERY = "SELECT * FROM orders,books WHERE books.id=orders.book_id";
    private static final String GET_ORDER_QUERY = "SELECT * FROM orders,books WHERE books.id=orders.book_id And orders.id=?";
    private static final String SET_ORDER_QUERY = "UPDATE orders SET status_order = ?, date_complete = ? WHERE id = ?";

    @InjectByType
    private ConnectPostgreSQL connectPostgreSQL;
    private Connection connection;

    @PostConstruct
    public void connection() {
        this.connection = connectPostgreSQL.conPostqres();
    }

    @Override
    public void save(Order order) {
        log.info("Save Order: {} To BD", order.toString());
        try (PreparedStatement statement = connection.prepareStatement(SAVE_ORDER_QUERY)) {
            statement.setString(1, order.getNameClient());
            statement.setInt(2, order.getBook().getId());
            statement.setInt(3, order.getCost());
            statement.setString(4, order.getStatusOrder().name());
            statement.execute();
        } catch (NullPointerException e) {
            log.error(e.toString());
            throw new DaoException(e);
        } catch (SQLException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            System.out.println("Ошибка в БД");
        }
    }

    @Override
    public void delete(Order order) {
        log.info("Delete order: {}", order.toString());
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_QUERY)) {
            statement.setInt(1, order.getId());
            statement.execute();
        } catch (NullPointerException e) {
            log.error(e.toString());
            throw new DaoException(e);
        } catch (SQLException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            System.out.println("Ошибка в БД");
        }
    }

    @Override
    public List<Order> gelAll() {
        log.info("getAll-OrderDao");
        List<Order> orderList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderList.add(getOrder(resultSet));
            }
        } catch (NullPointerException e) {
            log.error(e.toString());
            throw new DaoException(e);
        } catch (SQLException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            System.out.println("Ошибка в БД");
        }
        return orderList;
    }

    @Override
    public Order get(int id) {
        log.info("Get_Order_By_ID: {}", id);
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDER_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return getOrder(resultSet);
            }
        } catch (NullPointerException e) {
            log.error(e.toString());
            throw new DaoException(e);
        } catch (SQLException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            System.out.println("Ошибка в БД");
        }
        return null;
    }

    @Override
    public void set(Order order) {
        log.info("Set_Order_By_ID: {}", order.toString());
        try (PreparedStatement statement = connection.prepareStatement(SET_ORDER_QUERY)) {
            statement.setString(1, order.getStatusOrder().name());
            if (order.getDateComplete() != null) {
                statement.setTimestamp(2, DataTimeUtil.localDataToTimestamp(order.getDateComplete()));
            } else {
                statement.setTimestamp(2, null);
            }
            statement.setInt(3, order.getId());
            statement.execute();
        } catch (NullPointerException e) {
            log.error(e.toString());
            throw new DaoException(e);
        } catch (SQLException e) {
            log.error("OrderDao sql-exception {}", e.getMessage());
            System.out.println("Ошибка в БД");
        }
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

    private Order getOrder(ResultSet resultSet) throws SQLException {
        int idOrder = resultSet.getInt("id");
        String nameClient = resultSet.getString("name_client");
        Book book = getBook(resultSet);
        StatusOrder statusOrder = StatusOrder.valueOf(resultSet.getString("status_order"));
        LocalDate date = null;
        if (resultSet.getTimestamp("date_complete") != null) {
            date = DataTimeUtil.timestampToLocalDate(resultSet.getTimestamp("date_complete"));
        }
        return new Order(idOrder, nameClient, book, date, statusOrder);
    }
}
