package com.example.testcontainer.testcontainer.controller;

import com.example.testcontainer.testcontainer.resource.User;
import com.example.testcontainer.testcontainer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class userController {

    private final UserService userService;

    /**
     * Retrieve all Users
     * @return A list of User's
     */
    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Insert a new User entity into the database
     * @param user the User Entity
     * @return A User entity
     */
    @PostMapping(value = "/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping(value = "/user")
    public User getUserById(@RequestParam (value = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/user/{lastName}")
    public User getUserByLastName(@PathVariable (value = "lastName") String lastName) {
        return userService.getUserByLastName(lastName);
    }
}
