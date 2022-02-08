package com.quest.questapp.service;

import com.quest.questapp.model.User;
import com.quest.questapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User user) {
        return userRepository.save(user);
    }

    public User getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User user) {
        Optional<User> newUser = userRepository.findById(userId);
        if(newUser.isPresent()) {
            User foundUser = newUser.get();
            foundUser.setUserName(user.getUserName());
            foundUser.setUserName(user.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }else {
            return null;
        }
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
