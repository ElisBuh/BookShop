package com.senla;

import com.senla.dao.BookDao;
import com.senla.dao.ConnectPostgreSQL;
import com.senla.model.Book;
import com.senla.model.StatusBook;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
//    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/book_shop";
//    static final String USER = "user";
//    static final String PASS = "password";

    public static void main(String[] argv) {

//        System.out.println("Testing connection to PostgreSQL JDBC");

//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
//            e.printStackTrace();
//            return;
//        }
//
//        System.out.println("PostgreSQL JDBC Driver successfully connected");
//        ConnectPostgreSQL connectPostgreSQL = new ConnectPostgreSQL();
//        Connection connection = connectPostgreSQL.conPostqres();
//        BookDao bookDao = new BookDao();
//        bookDao.get(100001).toString();
//        try {
//            connection = DriverManager.getConnection(DB_URL, USER, PASS);
//
//        } catch (SQLException e) {
//            System.out.println("Connection Failed");
//            e.printStackTrace();
//            return;
//        }
//
//        if (connection != null) {
//            System.out.println("You successfully connected to database now");
//        } else {
//            System.out.println("Failed to make connection to database");
//        }
//        try {
//            List<Book> bookList = new ArrayList<>();
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books;");
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()){
//               bookList.add(new Book(resultSet.getInt(1),resultSet.getString("name_book"),resultSet.getString("name_author"),
//                       resultSet.getTimestamp("date").toLocalDateTime().toLocalDate(), resultSet.getInt("price"), StatusBook.valueOf(resultSet.getString("status_book"))));
//            }
//            bookList.forEach(System.out::println);
//            connection.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }
}
