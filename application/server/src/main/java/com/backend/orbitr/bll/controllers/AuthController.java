package com.backend.orbitr.bll.controllers;

import com.backend.orbitr.bll.services.AuthService;
import com.backend.orbitr.bll.services.UserService;
import com.backend.orbitr.dal.models.LoginRequest;
import com.backend.orbitr.dal.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public String login(@RequestBody LoginRequest loginRequest) {
        if(authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword())){
            String token = authService.generateToken(loginRequest.getUsername());
            return token;
        }
        return "Login unsuccessful";
    }
}