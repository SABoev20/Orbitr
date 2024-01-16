package com.backend.orbitr.bll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.backend.orbitr.dal.models.User;
import com.backend.orbitr.dal.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service("userDetailsService")
public class UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user){
        for (User checkUser : userRepository.findAll()){
            if(checkUser.getUsername().equals(user.getUsername())){
                return null;
            }
        }
        return userRepository.save(user);
    }

/*    public User getUser(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "User not found");
        }
        return userOptional.get();
    }*/

    public User updateUser(int id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "User not found");
        }
        User existingUser = userOptional.get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setCredits(user.getCredits());
        existingUser.setProfilePicture(user.getProfilePicture());
        return userRepository.save(existingUser);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}