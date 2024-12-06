package com.aplication.maxtrix2_0.service;

import com.aplication.maxtrix2_0.repository.CategoryRepository;
import com.aplication.maxtrix2_0.repository.UserRepository;
import com.aplication.maxtrix2_0.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public boolean createTest(Map<String, Object> requestData) {
        String category = (String) requestData.get("category");
        String authorLogin = (String) requestData.get("author"); // Логін користувача
        String testName = (String) requestData.get("testName");

        // Перевірка валідності категорії
        if (!categoryRepository.existsByCategoryName(category)) {
            throw new IllegalArgumentException("Invalid category. Please use a valid category.");
        }

        // Перевірка, чи автор існує
        if (!userRepository.existsByLogin(authorLogin)) {
            throw new IllegalArgumentException("Author not registered. Please use a valid user.");
        }

        // Перевірка, чи автор є адміністратором
        if (!userRepository.isAdmin(authorLogin)) {
            throw new IllegalArgumentException("Only administrators can create tests.");
        }

        // Перевірка наявності тесту з таким ім'ям
        if (testRepository.existsByTestName(testName)) {
            throw new IllegalArgumentException("Test name already exists.");
        }

        // Збереження тесту
        return testRepository.saveTest(requestData);
    }

    public boolean isValidCategory(String category) {
        return categoryRepository.existsByCategoryName(category);
    }

    // Отримує тест із репозиторію
    public Map<String, Object> getTest(String testName, String category) {
        return testRepository.findTestByCategoryAndTestName(testName);
    }

    // Отримує всі тести автора
    public List<String> getTestsByAuthor(String authorLogin) {
        return testRepository.findTestsByAuthor(authorLogin);
    }

    // Отримує всі тести за категорією
    public List<String> getTestsByCategory(String category) {
        return testRepository.findTestsByCategory(category);
    }
}
