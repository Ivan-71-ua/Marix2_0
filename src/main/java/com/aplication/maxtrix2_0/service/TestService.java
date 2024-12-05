package com.aplication.maxtrix2_0.service;

import com.aplication.maxtrix2_0.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public boolean createTest(Map<String, Object> requestData) {
        String category = (String) requestData.get("category");
        String testName = (String) requestData.get("testName");

        if (testRepository.existsByTestName(testName)) {
            return false;
        }

        return testRepository.saveTest(requestData);
    }

    public Map<String, Object> getTest(String testName, String category) {
        return testRepository.findTestByCategoryAndTestName(testName);
    }
}
