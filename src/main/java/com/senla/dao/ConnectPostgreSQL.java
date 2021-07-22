package com.senla.dao;

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

    private Connection connection;

    public Connection conPostqres() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            log.info("Connection to BD: {}", connection.getClientInfo());
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Connection Failed: {}", e.getMessage());
        }
        return null;
    }
    public void connectionClose(){
        try {
            connection.close();
            log.info("Bd session close.");
        } catch (SQLException e) {
            log.error("Connection Failed: {}", e.getMessage());
        }
    }
}
