package org.gBlank.dao;

import org.gBlank.DataBase.DatabaseManager;
import org.gBlank.entity.Request;
import org.gBlank.entity.Status;
import org.gBlank.entity.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    private final UserDAO userDAO = new UserDAO();

    public void saveRequest(Request request) {
        String sql = "INSERT INTO requests (id, user_id, status, description, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, request.getId());
            stmt.setString(2, request.getUser().getId());
            stmt.setString(3, request.getStatus().name());
            stmt.setString(4, request.getDescription());
            stmt.setString(5, request.getCreatedAt().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Помилка при збереженні заявки: " + e.getMessage());
        }
    }

    public Request getRequestById(String id) {
        String sql = "SELECT * FROM requests WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = userDAO.getUserById(rs.getString("user_id"));
                if (user == null) return null;

                Request request = new Request(Status.valueOf(rs.getString("status")), user, rs.getString("description"));
                request.setId(rs.getString("id"));
                request.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));
                return request;
            }
        } catch (SQLException e) {
            System.err.println("Помилка при пошуку заявки: " + e.getMessage());
        }
        return null;
    }

    public List<Request> getAllRequests() {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM requests";

        try (Connection conn = DatabaseManager.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = userDAO.getUserById(rs.getString("user_id"));
                Request request = new Request(Status.valueOf(rs.getString("status")), user, rs.getString("description"));
                request.setId(rs.getString("id"));
                request.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));
                requests.add(request);
            }
        } catch (SQLException e) {
            System.err.println("Помилка при отриманні всіх заявок: " + e.getMessage());
        }
        return requests;
    }

    public boolean updateStatus(String id, Status status) {
        String sql = "UPDATE requests SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status.name());
            stmt.setString(2, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Помилка при оновленні заявки: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteRequest(String id) {
        String sql = "DELETE FROM requests WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Помилка при видаленні заявки: " + e.getMessage());
        }

        return false;
    }
}
