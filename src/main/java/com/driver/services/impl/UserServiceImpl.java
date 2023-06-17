package com.driver.services.impl;

import com.driver.model.User;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository4;
    @Override
    public void deleteUser(Integer userId) throws Exception {
        Optional<User> userOptional = userRepository4.findById(userId);

        if(!userOptional.isPresent()){
            throw new Exception("User not found");
        }

        User user = userOptional.get();
        userRepository4.delete(user);
    }

    @Override
    public User updatePassword(Integer userId, String password) throws Exception {
        Optional<User> userOptional = userRepository4.findById(userId);

        if(!userOptional.isPresent()){
            throw new Exception("User not found");
        }

        User user = userOptional.get();
        user.setPassword(password);
        userRepository4.save(user);
        return user;
    }

    @Override
    public void register(String name, String phoneNumber, String password) {
        User user = new User(name, phoneNumber, password);
        userRepository4.save(user);
    }
}