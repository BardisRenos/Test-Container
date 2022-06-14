package com.example.testcontainer.testcontainer.service;

import com.example.testcontainer.testcontainer.dao.UserRepository;
import com.example.testcontainer.testcontainer.resource.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findUserByUserId(id);
    }

    public User getUserByLastName(String lastName) {
        return userRepository.findUserByLastName(lastName);
    }
}
