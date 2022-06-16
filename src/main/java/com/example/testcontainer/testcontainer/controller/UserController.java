package com.example.testcontainer.testcontainer.controller;

import com.example.testcontainer.testcontainer.resource.User;
import com.example.testcontainer.testcontainer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Retrieve all Users
     * @return A list of Users
     */
    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Insert a new User entity into the database.
     * @param user the User Entity
     * @return A User entity class
     */
    @PostMapping(value = "/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * Retrieve a User entity by a given ID.
     * @param id The given ID
     * @return A User entity class
     */
    @GetMapping(value = "/user")
    public User getUserById(@RequestParam (value = "id") Long id) {
        return userService.getUserById(id);
    }

    /**
     * Retrieve a User by a given Last Name.
     * @param lastName The given Last Name
     * @return A User entity class
     */
    @GetMapping(value = "/user/{lastName}")
    public User getUserByLastName(@PathVariable (value = "lastName") String lastName) {
        return userService.getUserByLastName(lastName);
    }
}
