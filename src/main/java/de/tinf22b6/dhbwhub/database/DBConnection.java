package de.tinf22b6.dhbwhub.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String url;
    private final String username;
    private final String password;

    private Connection connection;

    public DBConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Attempts to establish a connection to the stored database url.
     *
     * @return The connection instance
     * @throws SQLException If the url is {@code null}, a database access error
     * occurs or the connection timed out
     */
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
