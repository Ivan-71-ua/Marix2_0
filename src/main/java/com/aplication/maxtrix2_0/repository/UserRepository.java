package com.aplication.maxtrix2_0.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean existsByLogin(String login) {
        String query = "SELECT COUNT(*) FROM users WHERE login = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, login);
        return count != null && count > 0;
    }

    public boolean saveUser(Map<String, Object> requestData) {
        String login = (String) requestData.get("login");
        String fullName = (String) requestData.get("fullName");
        String password = (String) requestData.get("password");
        boolean isAdmin = (boolean) requestData.getOrDefault("isAdmin", false);

        String query = "INSERT INTO users (login, full_name, password, is_admin) VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(query, login, fullName, password, isAdmin);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateUser(String login, String password) {
        String query = "SELECT COUNT(*) FROM users WHERE login = ? AND password = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, login, password);
        return count != null && count > 0;
    }
}
