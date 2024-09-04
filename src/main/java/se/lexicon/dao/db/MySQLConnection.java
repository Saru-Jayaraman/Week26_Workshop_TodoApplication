package se.lexicon.dao.db;

import se.lexicon.exception.MySQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/todoit";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PWD = "root";

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
        }  catch (SQLException e) {
            throw new MySQLException("Error occurred while establishing connection with database: ", e);
        }
        return connection;
    }
}
