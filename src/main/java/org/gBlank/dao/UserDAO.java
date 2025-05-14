package org.gBlank.dao;

import org.gBlank.DataBase.DatabaseManager;
import org.gBlank.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserDAO {
    public void saveUser (User user) {
        String sql = "INSERT INTO users (id, name, surname, age, email, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getSurname());
            stmt.setInt(4, user.getAge());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getCreatedAt().toString());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Помилка при збереженні користувача" + e.getMessage());
        }
    }

    public User getUserById (String id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("surname"), rs.getInt("age"), rs.getString("email"));
                user.setId(rs.getString("id"));
                user.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));
                return user;
            }
        } catch (SQLException e) {
            System.err.println("Помилка при пошуку користувача: " + e.getMessage());
        }
        return null;
    }
}
