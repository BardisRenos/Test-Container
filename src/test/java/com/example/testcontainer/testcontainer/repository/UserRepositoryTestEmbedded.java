package com.example.testcontainer.testcontainer.repository;

import com.example.testcontainer.testcontainer.dao.UserRepository;
import com.example.testcontainer.testcontainer.resource.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class tests the Repository layer of User. On the H2 memory database.
 */
@DataJpaTest
class UserRepositoryTestEmbedded {

    @Autowired
    private UserRepository userRepository;

    /**
     * Before each test method the H2 memory database is initialised with some data.
     */
    @BeforeEach
    void init() {
        User user1 = new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com");
        User user2 = new User(2L, "Nikos", "Nikos91", "Papas", "nikos@gmail.com");

        List<User> listOfUser = new ArrayList<>();
        listOfUser.add(user1);
        listOfUser.add(user2);

        userRepository.saveAll(listOfUser);
    }

    /**
     * After each test method the  H2 memory database is cleared.
     */
    @AfterEach
    public void delete(){
        userRepository.deleteAll();
    }

    /**
     * This method checks if the rest api end point inserts correctly a User entity.
     */
    @Test
    void saveUserTest() {
        User user = new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com");
        User userRes = userRepository.save(user);

        assertThat(user).usingRecursiveComparison().isEqualTo(userRes);
    }

    /**
     * This method checks if all Users entities are retrieved from the database.
     */
    @Test
    void getAllUsersTest() {
        List<User> listRes = userRepository.findAll();

        assertThat(listRes).hasSize(2);
    }

    /**
     * This method checks if a User entity is retrieved from the database by a given Last Name.
     */
    @Test
    void getUserByLastName() {
        User user = userRepository.findUserByLastName("Bardis");

        assertThat(user.getLastName()).isEqualTo("Bardis");
    }

    /**
     * This method checks if a user entity is retrieved from the database by a given ID.
     */
    @Test
    void getUserByUserId() {
        User user = userRepository.findUserByUserId(1L);

        assertThat(user.getLastName()).isEqualTo("Bardis");
    }

}
