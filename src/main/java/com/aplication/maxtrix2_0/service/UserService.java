package com.aplication.maxtrix2_0.service;

import com.aplication.maxtrix2_0.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(Map<String, Object> requestData) {
        String login = (String) requestData.get("login");
        if (userRepository.existsByLogin(login)) {
            return false;
        }

        return userRepository.saveUser(requestData);
    }

    public boolean loginUser(Map<String, String> requestData) {
        String login = requestData.get("login");
        String password = requestData.get("password");

        return userRepository.validateUser(login, password);
    }

    // Перевіряє, чи користувач зареєстрований
    public boolean isUserRegistered(String login) {
        return userRepository.existsByLogin(login);
    }

    // Отримує повну інформацію про користувача
    public Map<String, Object> getUserInfo(String login) {
        return userRepository.getUserInfoByLogin(login);
    }
}
