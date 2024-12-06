package com.aplication.maxtrix2_0.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class    TestRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean existsByTestName(String testName) {
        String query = "SELECT COUNT(*) FROM tests WHERE test_name = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, testName);
        return count != null && count > 0;
    }

    public boolean saveTest(Map<String, Object> requestData) {
        String testName = (String) requestData.get("testName");
        String author = (String) requestData.get("author");
        String category = (String) requestData.get("category");

        // Перетворюємо Map<String, Object> на JSON-рядок
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData;
        try {
            jsonData = objectMapper.writeValueAsString(requestData.get("data"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }

        String query = "INSERT INTO tests (test_name, author, category, data) VALUES (?, ?, ?, ?::JSONB)";
        try {
            jdbcTemplate.update(query, testName, author, category, jsonData);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public Map<String, Object> findTestByCategoryAndTestName(String testName) {
        String query = "SELECT * FROM tests WHERE test_name = ?";
        try {
            return jdbcTemplate.queryForMap(query, testName);
        } catch (EmptyResultDataAccessException e) {
            // Тест не знайдено
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Знаходить усі тести за логіном автора
    public List<String> findTestsByAuthor(String authorLogin) {
        String query = "SELECT test_name FROM tests WHERE author = ?";
        return jdbcTemplate.queryForList(query, String.class, authorLogin);
    }

    // Знаходить усі тести за категорією
    public List<String> findTestsByCategory(String category) {
        String query = "SELECT test_name FROM tests WHERE category = ?";
        return jdbcTemplate.queryForList(query, String.class, category);
    }

}
