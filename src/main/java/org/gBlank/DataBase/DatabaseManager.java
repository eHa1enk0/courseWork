package org.gBlank.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String url = "jdbc:sqlite:my_db.db";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("Помилка під час підключення до бази даних: " + e.getMessage());
            throw e;
        }
    }
}
