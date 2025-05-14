package org.gBlank.DataBase;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {
        String createUserTable = """
            CREATE TABLE IF NOT EXISTS users (
                id TEXT PRIMARY KEY,
                name TEXT,
                surname TEXT,
                age INTEGER,
                email TEXT,
                created_at TEXT
            );
        """;

        String createRequestTable = """
                CREATE TABLE IF NOT EXISTS requests (
                    id TEXT PRIMARY KEY,
                    user_id TEXT NOT NULL,
                    status TEXT NOT NULL,
                    description TEXT,
                    created_at TEXT,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );
                """;

        try (Connection conn = DatabaseManager.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(createUserTable);
            stmt.execute(createRequestTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
