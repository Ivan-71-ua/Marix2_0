package com.aplication.maxtrix2_0.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean existsByCategoryName(String categoryName) {
        String query = "SELECT COUNT(*) FROM categories WHERE category_name = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, categoryName);
        return count != null && count > 0;
    }
}
