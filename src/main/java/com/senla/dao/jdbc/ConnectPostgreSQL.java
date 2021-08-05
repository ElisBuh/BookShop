package com.senla.dao.jdbc;

import com.senla.exceptions.DaoException;
import com.senla.util.annotation.InjectProperty;
import com.senla.util.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Singleton
public class ConnectPostgreSQL {
    private static final Logger log = LoggerFactory.getLogger(ConnectPostgreSQL.class);

    @InjectProperty("db_url")
    private String DB_URL;
    @InjectProperty("user")
    private String USER;
    @InjectProperty("pass")
    private String PASS;
    @InjectProperty("db_driver")
    private String dbDriver;

    private Connection connection;

    public Connection conPostqres() {
        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            log.info("Connection to BD: {}", connection.getClientInfo());
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Connection Failed: {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    public void connectionClose() {
        try {
            connection.close();
            log.info("Bd session close.");
        } catch (SQLException e) {
            log.error("Connection Failed: {}", e.getMessage());
        }
    }
}
