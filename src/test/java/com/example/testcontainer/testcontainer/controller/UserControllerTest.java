package com.example.testcontainer.testcontainer.controller;

import com.example.testcontainer.testcontainer.resource.User;
import com.example.testcontainer.testcontainer.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("Controller Test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    ObjectMapper om = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.userController = new UserController(this.userService);
    }

    @Test
    void getAllUsersTest() {
        User user1 = new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com");
        User user2 = new User(2L, "Nikos", "Nikos91", "Papas", "nikos@gmail.com");

        List<User> listOfUsers = new ArrayList<>(Arrays.asList(user1, user2));

        when(userService.getAllUsers()).thenReturn(listOfUsers);

        List<User> usersResponse = userController.getAllUsers();

        assertAll(()->assertEquals(1, usersResponse.get(0).getUserId()),
                ()->assertEquals("Renos", usersResponse.get(0).getFirstName()),
                ()->assertEquals("Bardis", usersResponse.get(0).getLastName()),
                ()->assertEquals("Renos87", usersResponse.get(0).getUserName()));
    }

    @Test
    void getUserByIdTest() {
        User user = new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com");

        when(userService.getUserById(1L)).thenReturn(user);

        User usersResponse = userController.getUserById(1L);

        assertAll(()->assertEquals(1, usersResponse.getUserId()),
                ()->assertEquals("Renos", usersResponse.getFirstName()),
                ()->assertEquals("Bardis", usersResponse.getLastName()),
                ()->assertEquals("Renos87", usersResponse.getUserName()));
    }

    @Test
    void getUserByLastName() {
        User user = new User(1L, "Nikos", "Nikos91", "Papas", "nikos@gmail.com");

        when(userService.getUserByLastName("Papas")).thenReturn(user);

        User usersResponse = userController.getUserByLastName("Papas");

        assertAll(()->assertEquals(1, usersResponse.getUserId()),
                ()->assertEquals("Nikos", usersResponse.getFirstName()),
                ()->assertEquals("Papas", usersResponse.getLastName()),
                ()->assertEquals("Nikos91", usersResponse.getUserName()));
    }

    @Test
    void addUser() {
        User user = new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com");

        when(userService.addUser(user)).thenReturn(user);

        User usersResponse = userController.addUser(user);

        assertAll(()->assertEquals(1, usersResponse.getUserId()),
                ()->assertEquals("Renos", usersResponse.getFirstName()),
                ()->assertEquals("Bardis", usersResponse.getLastName()),
                ()->assertEquals("Renos87", usersResponse.getUserName()));
    }
}
