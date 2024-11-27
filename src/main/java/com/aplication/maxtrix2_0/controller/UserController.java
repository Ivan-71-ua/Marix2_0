package com.aplication.maxtrix2_0.controller;

import com.aplication.maxtrix2_0.model.User;
import com.aplication.maxtrix2_0.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public boolean registerUser(@RequestBody UserRegistrationRequest request) {
        return userService.registerUser(request.getFullName(), request.getLogin(), request.getPassword(), request.isAdmin());
    }

    @PostMapping("/login")
    public boolean loginUser(@RequestBody UserLoginRequest request) {
        return userService.loginUser(request.getLogin(), request.getPassword());
    }

    @PostMapping("/getUserDetails")
    public ResponseEntity<?> getUserDetails(@RequestBody UserLoginRequest request) {
        User user = userService.findUserByLogin(request.getLogin());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
}
