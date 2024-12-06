package com.aplication.maxtrix2_0.controller;

import com.aplication.maxtrix2_0.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/create")
    public ResponseEntity<?> createTest(@RequestBody Map<String, Object> requestData) {
        try {
            boolean success = testService.createTest(requestData);
            if (success) {
                return ResponseEntity.ok("Test created successfully");
            } else {
                return ResponseEntity.status(400).body("Failed to create test. Test name may already exist.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }

    @PostMapping("/get")
    public ResponseEntity<?> getTest(@RequestBody Map<String, String> requestData) {
        try {
            String testName = requestData.get("testName");
            String category = requestData.get("category");

            // Перевірка валідності категорії
            if (!testService.isValidCategory(category)) {
                return ResponseEntity.status(400).body("Invalid category. Please use a valid category.");
            }

            // Отримуємо тест
            Map<String, Object> testDetails = testService.getTest(testName, category);

            if (testDetails != null) {
                return ResponseEntity.ok(testDetails);
            } else {
                return ResponseEntity.status(404).body("Test not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }


}
