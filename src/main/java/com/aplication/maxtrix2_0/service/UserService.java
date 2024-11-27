package com.aplication.maxtrix2_0.service;

import com.aplication.maxtrix2_0.model.User;
import com.aplication.maxtrix2_0.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(String fullName, String login, String password, boolean isAdmin) {
        if (userRepository.existsByLogin(login)) {
            return false;
        }
        User user = new User(login, password, fullName, isAdmin);
        userRepository.save(user);
        return true;
    }

    public boolean loginUser(String login, String password) {
        User user = userRepository.findByLogin(login);
        return user != null && user.getPassword().equals(password);
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
