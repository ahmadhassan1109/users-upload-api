package com.uploads.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uploads.model.User;
import com.uploads.repo.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;   

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public List<User> getUsers() {
    	return userRepository.findAll();
    }
}