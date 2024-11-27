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

    // Запит для створення тесту
    @PostMapping("/create")
    public ResponseEntity<?> createTest(@RequestBody Map<String, Object> requestData) {
        boolean success = testService.createTest(requestData);
        if (success) {
            return ResponseEntity.ok(true); // Успішно створено
        } else {
            return ResponseEntity.status(400).body(false); // Створення не вдалося
        }
    }

    // Запит для отримання тесту
    @PostMapping("/get")
    public ResponseEntity<?> getTest(@RequestBody Map<String, String> requestData) {
        Map<String, Object> testDetails = testService.getTest(requestData);
        if (testDetails != null) {
            return ResponseEntity.ok(testDetails); // Повернення даних тесту
        } else {
            return ResponseEntity.status(404).body("Test not found"); // Тест не знайдено
        }
    }
}
