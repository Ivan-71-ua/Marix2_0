package com.aplication.maxtrix2_0.controller;

import com.aplication.maxtrix2_0.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> requestData) {
        boolean success = userService.registerUser(requestData);
        if (success) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(400).body("Registration failed. User may already exist.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> requestData) {
        boolean success = userService.loginUser(requestData);
        if (success) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid login or password");
        }
    }

    @PostMapping("/getUserInfo")
    public ResponseEntity<?> getUserInfo(@RequestBody Map<String, String> requestData) {
        String login = requestData.get("login");

        // Перевірка, чи користувач зареєстрований
        if (!userService.isUserRegistered(login)) {
            return ResponseEntity.status(404).body("User not found.");
        }

        // Отримання інформації про користувача
        Map<String, Object> userInfo = userService.getUserInfo(login);
        if (userInfo != null) {
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }
}
