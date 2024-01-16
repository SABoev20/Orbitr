package com.backend.orbitr.bll.controllers;

import com.backend.orbitr.bll.services.AuthService;
import com.backend.orbitr.bll.services.UserService;
import com.backend.orbitr.dal.models.LoginRequest;
import com.backend.orbitr.dal.models.Spaceship;
import com.backend.orbitr.dal.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService){
        this.userService = userService;
        this.authService = authService;
    }

/*    @GetMapping(value = "/", produces = "application/json")
    public List<User> getUsers() {
        return userService.listUsers();
    }*/

    @GetMapping("/current")
    public User getCurrentUser(Authentication authentication) {
        return authService.getUserByToken(authentication);
    }

    @PostMapping("/update/picture")
    public User updateProfilePicture(@RequestBody LoginRequest loginRequest, Authentication authentication) {
        User user = authService.getUserByToken(authentication);
        System.out.println(loginRequest.getUsername());
        user.setProfilePicture(loginRequest.getUsername());
        return userService.updateUser(user.getID(), user);
    }

    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public User update(@RequestBody User updatedUser, Authentication authentication) {
        User user = authService.getUserByToken(authentication);
        updatedUser.setID(user.getID());
        return userService.updateUser(updatedUser.getID(), updatedUser);
    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    public void delete(Authentication authentication) {
        userService.deleteUser(authService.getUserByToken(authentication).getID());
    }
}