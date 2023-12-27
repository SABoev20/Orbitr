package org.orbitr.bll.services;

import org.orbitr.dal.models.User;
import org.orbitr.dal.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserService {
    private UserRepository userRepository = UserRepository.getInstance();
    private List<User> usersList = userRepository.getAllUsers();

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void addUser(User user){
        userRepository.createUser(user);
    }

    public boolean isUsernameAvailable(String username){
        for(User user : usersList){
            if(user.getUsername() == username) return false;
        }
        return true;
    }

    public boolean authenticate(String username, String password){
        for(User user : usersList){
            if(user.getUsername() != username) continue;
            if(passwordEncoder.matches(password, user.getPassword())) return true;
        }
        return false;
    }


}
