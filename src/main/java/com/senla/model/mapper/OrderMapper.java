package com.senla.model.mapper;

import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.StatusOrder;
import com.senla.util.DataTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderMapper {

    public Order getOrder(ResultSet resultSet, BookMapper bookMapper) throws SQLException {
        Integer idOrder = resultSet.getInt("id");
        String nameClient = resultSet.getString("name_client");
        Book book = bookMapper.getBook(resultSet);
        StatusOrder statusOrder = StatusOrder.valueOf(resultSet.getString("status_order"));
        LocalDate date = null;
        if (resultSet.getTimestamp("date_complete") != null) {
            date = DataTimeUtil.timestampToLocalDate(resultSet.getTimestamp("date_complete"));
        }
        return new Order(idOrder, nameClient, book, date, statusOrder);
    }

}
