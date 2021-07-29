package com.senla.model.dto;

import com.senla.model.Book;
import com.senla.model.StatusBook;
import com.senla.util.DataTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookDTO {

    public Book getBook(ResultSet resultSet) throws SQLException {
        int idBook = resultSet.getInt("id");
        String nameBook = resultSet.getString("name_book");
        String nameAuthor = resultSet.getString("name_author");
        LocalDate date = DataTimeUtil.timestampToLocalDate(resultSet.getTimestamp("date"));
        int price = resultSet.getInt("price");
        StatusBook statusBook = StatusBook.valueOf(resultSet.getString("status_book"));
        return new Book(idBook, nameBook, nameAuthor, date, price, statusBook);
    }
}
