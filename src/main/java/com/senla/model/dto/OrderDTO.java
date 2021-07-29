package com.senla.model.dto;

import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.StatusOrder;
import com.senla.util.DataTimeUtil;
import com.senla.util.annotation.InjectByType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDTO {

    public Order getOrder(ResultSet resultSet, BookDTO bookDTO) throws SQLException {
        int idOrder = resultSet.getInt("id");
        String nameClient = resultSet.getString("name_client");
        Book book = bookDTO.getBook(resultSet);
        StatusOrder statusOrder = StatusOrder.valueOf(resultSet.getString("status_order"));
        LocalDate date = null;
        if (resultSet.getTimestamp("date_complete") != null) {
            date = DataTimeUtil.timestampToLocalDate(resultSet.getTimestamp("date_complete"));
        }
        return new Order(idOrder, nameClient, book, date, statusOrder);
    }

}
