package org.orbitr.bll.controllers;

import org.orbitr.bll.services.UserService;
import org.orbitr.dal.models.User;

public class UserController {
    private User currentUser = null;
    private UserService userService = new UserService();

    public boolean register(User user){
        if(!userService.isUsernameAvailable(user.getUsername())) return false;
        userService.addUser(user);
        return true;
    }

    public boolean login(User user){
        if(!userService.authenticate(user.getUsername(), user.getPassword())) return false;
        currentUser = user;
        return true;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public void logout(){
        currentUser = null;
    }
}
