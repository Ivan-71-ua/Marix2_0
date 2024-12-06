package com.aplication.maxtrix2_0.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean existsByCategoryName(String categoryName) {
        String query = "SELECT COUNT(*) FROM categories WHERE category_name = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, categoryName);
        return count != null && count > 0;
    }

    public List<String> findAllCategories() {
        String query = "SELECT category_name FROM categories";
        return jdbcTemplate.queryForList(query, String.class);
    }
}
