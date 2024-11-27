package com.aplication.maxtrix2_0.service;

import com.aplication.maxtrix2_0.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    // Створення тесту
    public boolean createTest(Map<String, Object> requestData) {
        String category = (String) requestData.get("category");
        String testName = (String) requestData.get("testName");

        // Перевірка на унікальність назви тесту
        if (testRepository.existsByCategoryAndTestName(category, testName)) {
            return false; // Назва тесту вже існує
        }

        // Збереження тесту
        return testRepository.saveTest(requestData);
    }

    // Отримання тесту
    public Map<String, Object> getTest(Map<String, String> requestData) {
        String category = requestData.get("category");
        String testName = requestData.get("testName");

        return testRepository.findTestByCategoryAndTestName(category, testName);
    }
}
