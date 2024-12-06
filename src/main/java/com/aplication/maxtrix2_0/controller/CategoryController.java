package com.aplication.maxtrix2_0.controller;

import com.aplication.maxtrix2_0.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllCategories() {
        List<String> categories = categoryService.getAllCategories();
        if (categories != null && !categories.isEmpty()) {
            return ResponseEntity.ok(categories);
        } else {
            return ResponseEntity.status(404).body("No categories found.");
        }
    }
}
