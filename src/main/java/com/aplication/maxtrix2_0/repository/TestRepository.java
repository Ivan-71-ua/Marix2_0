package com.aplication.maxtrix2_0.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TestRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Перевірка на унікальність назви тесту
    public boolean existsByCategoryAndTestName(String category, String testName) {
        String query = "SELECT COUNT(*) FROM " + category + "_tests WHERE test_name = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, testName);
        return count != null && count > 0;
    }

    // Збереження тесту
    public boolean saveTest(Map<String, Object> requestData) {
        String category = (String) requestData.get("category");
        String testName = (String) requestData.get("testName");
        String author = (String) requestData.get("author");
        String questions = requestData.get("questions").toString();
        String options = requestData.get("options").toString();
        String correctAnswers = requestData.get("correctAnswers").toString();

        String query = "INSERT INTO " + category + "_tests (test_name, author, questions, options, correct_answers) VALUES (?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(query, testName, author, questions, options, correctAnswers);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Отримання тесту
    public Map<String, Object> findTestByCategoryAndTestName(String category, String testName) {
        String query = "SELECT * FROM " + category + "_tests WHERE test_name = ?";

        try {
            return jdbcTemplate.queryForMap(query, testName);
        } catch (Exception e) {
            return null; // Тест не знайдено
        }
    }
}
